package jp.emcom.adv.fx.gws.mnrate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;

import cn.bestwiz.jhf.core.idgenerate.IdGenerateFacade;
import cn.bestwiz.jhf.core.jms.SimpleCallback;
import cn.bestwiz.jhf.core.jms.SimpleReceiver;
import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.bean.RateBandInfo;
import cn.bestwiz.jhf.core.jms.exception.JMSException;
import cn.bestwiz.jhf.core.jms.test.bean.TestCpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.test.bean.TestGwTradeMessageBean;
import cn.bestwiz.jhf.core.util.LogUtil;

/**
 * @author     lubq <lubq@adv.emcom.jp>
 * @copyright  2009,Adv.EMCOM
 */
public class LubqRateSender {

    public LubqRateSender()
    {
    }

    public static void start()
        throws JMSException
    {
//        (new SimpleReceiver("topic/TestManualCPRateTopic")).addCallback(new MNRateReceiver());
//        (new SimpleReceiver("topic/TestTradeFailTimesTopic")).addCallback(new SimpleCallback() {
//
//            public void onMessage(Serializable message)
//            {
//                TestGwTradeMessageBean times = (TestGwTradeMessageBean)message;
//                CpInfo cp = (CpInfo)LubqRateSender.cps.get(times.getCpId());
//                if(cp == null)
//                {
//                    MNRateReceiver.log.warn((new StringBuilder("Not found cp ! cpid:")).append(times.getCpId()).toString());
//                    return;
//                }
//                if(times.getUnknownStatus().equals("0"))
//                {
//                    cp.setFailTimes(times.getCurencyPair(), times.getGwTrageNumber());
//                    MNRateReceiver.log.info((new StringBuilder(String.valueOf(cp.getCpid()))).append(" set fail times :").append(times.getGwTrageNumber()).toString());
//                } else
//                {
//                    cp.setFailTimes(times.getCurencyPair(), -1);
//                    MNRateReceiver.log.info((new StringBuilder(String.valueOf(cp.getCpid()))).append(" set fail times :-1").toString());
//                }
//            }
//
//        });
    	
    	
//    	cps.put("DB", new CpInfo("DB", "gwCounterPartyRateTopic", "queue/gwDbOrderRequestQueue"));
    	
    	
    	
//        cps.put("BC", new CpInfo("BC", "topic/gwCounterPartyBCRateTopic", "queue/gwBcOrderRequestQueue"));
        cps.put("DB", new CpInfo("DB", "topic/gwCounterPartyDBRateTopic", "queue/gwDbOrderRequestQueue"));
//        cps.put("DR", new CpInfo("DR", "topic/gwCounterPartyDRRateTopic", "queue/gwDrOrderRequestQueue"));
        cps.put("GS", new CpInfo("GS", "topic/gwCounterPartyGSRateTopic", "queue/gwGsOrderRequestQueue"));
        cps.put("MOCK", new CpInfo("MOCK", "topic/gwCounterPartyMOCKRateTopic", "queue/gwMockOrderRequestQueue"));
//        cps.put("SA", new CpInfo("SA", "topic/gwCounterPartySARateTopic", "queue/gwSAOrderRequestQueue"));
//        cps.put("CT", new CpInfo("CT", "topic/gwCounterPartyCTRateTopic", "queue/gwCtOrderRequestQueue"));
//        cps.put("HB", new CpInfo("HB", "topic/gwCounterPartyHBRateTopic", null));
//        cps.put("LB", new CpInfo("LB", "topic/gwCounterPartyLBRateTopic", null));
//        cps.put("MC", new CpInfo("MC", "topic/gwCounterPartyMCRateTopic", "queue/gwMcOrderRequestQueue"));
//        cps.put("TRSBST", new CpInfo("TRSBST", "topic/gwCounterPartyTRSBSTRateTopic", null));
    }

    public void onMessage(Serializable message)
    {
        TestCpSpotRateInfo trate = (TestCpSpotRateInfo)message;
        CpSpotRateInfo r = new CpSpotRateInfo();
        r.setCounterPartyId(trate.getCounterPartyId());
        r.setUsualable(trate.isUsualable());
        r.setCurrencyPair(trate.getCurrencyPair());
        r.setMessageTime(trate.getMessageTime());
        r.setInManualStatus(trate.getInManualStatus());
        r.setFxPriceId(trate.getFxPriceId());
        r.setBookingType(1);
        ((RateBandInfo)trate.getAskBandInfoList().get(0)).setPriceId(UUID.randomUUID().toString());
        ((RateBandInfo)trate.getBidBandInfoList().get(0)).setPriceId(UUID.randomUUID().toString());
        r.setAskBandInfoList(trate.getAskBandInfoList());
        r.setBidBandInfoList(trate.getBidBandInfoList());
        CpInfo cp = (CpInfo)cps.get(r.getCounterPartyId());
        if(cp != null)
            try
            {
                cp.getRateSender().sendMessage(r);
                log.info((new StringBuilder(String.valueOf(trate.getCounterPartyId()))).append(" Send ok").toString());
            }
            catch(JMSException e)
            {
                e.printStackTrace();
            }
        else
            log.warn((new StringBuilder("Not found sender for cp ")).append(trate.getCounterPartyId()).toString());
    }

    static final Log log = LogUtil.getLog(MNRateReceiver.class);
    private static final Map cps = new HashMap();


    public static void main(String[] args) throws InterruptedException, JMSException {
		Scanner sc = new Scanner(System.in);
		LubqRateSender.start();
			
		while(sc.hasNext()){
			
			String partyId = sc.next();
			if(!cps.containsKey(partyId)){
				System.out.println("NOT A PARTY ID  .....");
				continue;
			}
			
			Thread.sleep(1000);

			CpSpotRateInfo r = makeCpRateInfo(partyId);
			System.out.println(r);
			CpInfo cp = (CpInfo)cps.get(r.getCounterPartyId());
			cp.getRateSender().sendMessage(r);
		}

	}

//    private static String pid = "DB" ;
    private static String ccp = "USD/JPY";
    private static String askPrice = "120.000";
    private static String bidPrice = "118.000";
    
    public static CpSpotRateInfo makeCpRateInfo(String pid){

        CpSpotRateInfo r = new CpSpotRateInfo();
        
        r.setCounterPartyId(pid);
        r.setUsualable(true);
        r.setCurrencyPair(ccp);
        r.setMessageTime(new Date());
        r.setInManualStatus(1);
        r.setFxPriceId(UUID.randomUUID().toString());
        r.setBookingType(1);
        
        RateBandInfo rbiask = new RateBandInfo();
        RateBandInfo rbibid = new RateBandInfo();
        
        rbiask.setPriceId(UUID.randomUUID().toString());
        rbibid.setPriceId(UUID.randomUUID().toString());

        rbiask.setRate((new BigDecimal(askPrice)).add( new BigDecimal(new Random().nextDouble())).setScale(3, RoundingMode.UP));
        rbibid.setRate((new BigDecimal(bidPrice)).add( new BigDecimal(new Random().nextDouble())).setScale(3, RoundingMode.UP));
        
        r.setAskBandInfoList(rbiask);
        r.setBidBandInfoList(rbibid);
        
        
        return r;
    }
    
    
    public static List<CpSpotRateInfo> readDataBase(String partyId){
    	List<CpSpotRateInfo> oneCpRate
        CpSpotRateInfo r = new CpSpotRateInfo();
        
        r.setCounterPartyId(pid);
        r.setUsualable(true);
        r.setCurrencyPair(ccp);
        r.setMessageTime(new Date());
        r.setInManualStatus(1);
        r.setFxPriceId(UUID.randomUUID().toString());
        r.setBookingType(1);
        
        RateBandInfo rbiask = new RateBandInfo();
        RateBandInfo rbibid = new RateBandInfo();
        
        rbiask.setPriceId(UUID.randomUUID().toString());
        rbibid.setPriceId(UUID.randomUUID().toString());

        rbiask.setRate((new BigDecimal(askPrice)).add( new BigDecimal(new Random().nextDouble())).setScale(3, RoundingMode.UP));
        rbibid.setRate((new BigDecimal(bidPrice)).add( new BigDecimal(new Random().nextDouble())).setScale(3, RoundingMode.UP));
        
        r.setAskBandInfoList(rbiask);
        r.setBidBandInfoList(rbibid);
        
        
        return r;
    }
    
}

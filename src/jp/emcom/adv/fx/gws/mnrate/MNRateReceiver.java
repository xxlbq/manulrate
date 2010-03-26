// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MNRateReceiver.java

package jp.emcom.adv.fx.gws.mnrate;

import cn.bestwiz.jhf.core.jms.*;
import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.bean.RateBandInfo;
import cn.bestwiz.jhf.core.jms.exception.JMSException;
import cn.bestwiz.jhf.core.jms.test.bean.TestCpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.test.bean.TestGwTradeMessageBean;
import cn.bestwiz.jhf.core.util.LogUtil;
import java.io.Serializable;
import java.util.*;
import org.apache.commons.logging.Log;

// Referenced classes of package jp.emcom.adv.fx.gws.mnrate:
//            CpInfo

public class MNRateReceiver
    implements SimpleCallback
{

    public MNRateReceiver()
    {
    }

    public static void start()
        throws JMSException
    {
        (new SimpleReceiver("topic/TestManualCPRateTopic")).addCallback(new MNRateReceiver());
        (new SimpleReceiver("topic/TestTradeFailTimesTopic")).addCallback(new SimpleCallback() {

            public void onMessage(Serializable message)
            {
                TestGwTradeMessageBean times = (TestGwTradeMessageBean)message;
                CpInfo cp = (CpInfo)MNRateReceiver.cps.get(times.getCpId());
                if(cp == null)
                {
                    MNRateReceiver.log.warn((new StringBuilder("Not found cp ! cpid:")).append(times.getCpId()).toString());
                    return;
                }
                if(times.getUnknownStatus().equals("0"))
                {
                    cp.setFailTimes(times.getCurencyPair(), times.getGwTrageNumber());
                    MNRateReceiver.log.info((new StringBuilder(String.valueOf(cp.getCpid()))).append(" set fail times :").append(times.getGwTrageNumber()).toString());
                } else
                {
                    cp.setFailTimes(times.getCurencyPair(), -1);
                    MNRateReceiver.log.info((new StringBuilder(String.valueOf(cp.getCpid()))).append(" set fail times :-1").toString());
                }
            }

        });
        cps.put("BC", new CpInfo("BC", "topic/gwCounterPartyBCRateTopic", "queue/gwBcOrderRequestQueue"));
        cps.put("DB", new CpInfo("DB", "topic/gwCounterPartyDBRateTopic", "queue/gwDbOrderRequestQueue"));
        cps.put("DR", new CpInfo("DR", "topic/gwCounterPartyDRRateTopic", "queue/gwDrOrderRequestQueue"));
        cps.put("GS", new CpInfo("GS", "topic/gwCounterPartyGSRateTopic", "queue/gwGsOrderRequestQueue"));
        cps.put("MOCK", new CpInfo("MOCK", "topic/gwCounterPartyMOCKRateTopic", "queue/gwMockOrderRequestQueue"));
        cps.put("SA", new CpInfo("SA", "topic/gwCounterPartySARateTopic", "queue/gwSAOrderRequestQueue"));
        cps.put("CT", new CpInfo("CT", "topic/gwCounterPartyCTRateTopic", "queue/gwCtOrderRequestQueue"));
        cps.put("HB", new CpInfo("HB", "topic/gwCounterPartyHBRateTopic", null));
        cps.put("LB", new CpInfo("LB", "topic/gwCounterPartyLBRateTopic", null));
        cps.put("MC", new CpInfo("MC", "topic/gwCounterPartyMCRateTopic", "queue/gwMcOrderRequestQueue"));
        cps.put("TRSBST", new CpInfo("TRSBST", "topic/gwCounterPartyTRSBSTRateTopic", null));
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


}

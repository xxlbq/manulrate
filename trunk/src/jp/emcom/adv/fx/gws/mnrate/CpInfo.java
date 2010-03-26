// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MNRateReceiver.java

package jp.emcom.adv.fx.gws.mnrate;

import cn.bestwiz.jhf.core.jms.*;
import cn.bestwiz.jhf.core.jms.bean.GwCpTradeRequestInfo;
import cn.bestwiz.jhf.core.jms.bean.GwCpTradeResponseInfo;
import cn.bestwiz.jhf.core.jms.exception.JMSException;
import cn.bestwiz.jhf.core.service.CoreService;
import cn.bestwiz.jhf.core.service.exception.CoreException;
import cn.bestwiz.jhf.core.util.DateHelper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;

// Referenced classes of package jp.emcom.adv.fx.gws.mnrate:
//            MNRateReceiver

public class CpInfo
{

    public int getFailTimes(String ccp)
    {
        if(failTimesMap.containsKey(ccp))
            return ((Integer)failTimesMap.get(ccp)).intValue();
        else
            return 0;
    }

    public void setFailTimes(String ccp, int i)
    {
        failTimesMap.put(ccp, Integer.valueOf(i));
    }

    public String getCpid()
    {
        return cpid;
    }

    public SimpleSender getRateSender()
    {
        return rateSender;
    }

    public CpInfo(final String id, String rateSendDest, String tradeReceiver)
        throws JMSException
    {
        super();
        failTimesMap = new HashMap();
        cpid = id;
        rateSender = SimpleSender.getInstance(rateSendDest);
        if(tradeReceiver != null)
            (new SimpleReceiver(tradeReceiver)).addCallback(new SimpleCallback() {

                public void onMessage(Serializable message)
                {
                    GwCpTradeRequestInfo req = (GwCpTradeRequestInfo)message;
                    Integer i = (Integer)failTimesMap.get(req.getCurrencyPair());
                    if(i == null || i.intValue() == 0)
                        doSuccess(req);
                    else
                    if(i.intValue() > 0)
                    {
                        doReject(req);
                        setFailTimes(req.getCurrencyPair(), i.intValue() - 1);
                    } else
                    {
                        MNRateReceiver.log.info((new StringBuilder(String.valueOf(id))).append(" ").append(req.getCurrencyPair()).append(" unknow!").toString());
                    }
                }

                private void doReject(GwCpTradeRequestInfo req)
                {
                    GwCpTradeResponseInfo res = new GwCpTradeResponseInfo();
                    res.setCpCoverId(req.getCpCoverId());
                    res.setPriceId(req.getPriceId());
                    res.setCounterpartyId(req.getCounterpartyId());
                    res.setCurrencyPair(req.getCurrencyPair());
                    res.setSide(req.getSide());
                    res.setAmount(req.getAmount());
                    res.setRate(req.getRate());
                    res.setSuccessFlag(false);
                    res.setErrorCode("001");
                    res.setErrorMsg("reject");
                    res.setResponseTime(DateHelper.getTodaysDate());
                    try
                    {
                        CpInfo.response.sendMessage(res);
                        MNRateReceiver.log.info((new StringBuilder(String.valueOf(id))).append(" ").append(req.getCurrencyPair()).append(" reject!").toString());
                    }
                    catch(JMSException e)
                    {
                        e.printStackTrace();
                    }
                }

                private void doSuccess(GwCpTradeRequestInfo req)
                {
                    GwCpTradeResponseInfo res = new GwCpTradeResponseInfo();
                    res.setCpCoverId(req.getCpCoverId());
                    res.setPriceId(req.getPriceId());
                    res.setCounterpartyId(req.getCounterpartyId());
                    res.setCurrencyPair(req.getCurrencyPair());
                    res.setSide(req.getSide());
                    res.setAmount(req.getAmount());
                    res.setRate(req.getRate());
                    res.setSuccessFlag(true);
                    res.setResponseTime(DateHelper.getTodaysDate());
                    res.setCounterAmount(req.getAmount());
                    try
                    {
                        res.setTradeDate((new CoreService()).getFrontDate());
                    }
                    catch(CoreException e1)
                    {
                        e1.printStackTrace();
                    }
                    res.setValueDate("20101010");
                    try
                    {
                        CpInfo.response.sendMessage(res);
                        MNRateReceiver.log.info((new StringBuilder(String.valueOf(id))).append(" ").append(req.getCurrencyPair()).append(" success!").toString());
                    }
                    catch(JMSException e)
                    {
                        e.printStackTrace();
                    }
                }

//                final CpInfo info;
//                private final String id;

            
            {

//            	this.id = id;
//                this.info = ;
            }
            });
    }

    static SimpleSender response = null;
    private String cpid;
    private SimpleSender rateSender;
    private Map failTimesMap;

    static 
    {
        try
        {
            response = SimpleSender.getInstance("topic/gwOrderResponseTopic");
        }
        catch(JMSException e)
        {
            e.printStackTrace();
        }
    }

}

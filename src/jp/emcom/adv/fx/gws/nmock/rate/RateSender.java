// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RateSender.java

package jp.emcom.adv.fx.gws.nmock.rate;

import cn.bestwiz.jhf.core.jms.SimpleSender;
import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.exception.JMSException;
import cn.bestwiz.jhf.core.util.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import jp.emcom.adv.fx.gws.mnrate.NMockStart;
import org.apache.commons.logging.Log;

public class RateSender
{

    public RateSender(String dest, final String cpid)
    {
        try
        {
            sender = SimpleSender.getInstance(dest);
        }
        catch(JMSException e)
        {
            e.printStackTrace();
            log.error("CreateRateSender ERR !", e);
        }
        Thread t = new Thread() {

            public void run()
            {
                try
                {
                    while(true) 
                    {
                        RateSender.log.info((new StringBuilder(String.valueOf(cpid))).append("SendRateCounter:").append(counter).toString());
                        sleep(1000L);
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            final RateSender this$0;
            private final String val$cpid;

            
            {
                this$0 = RateSender.this;
                cpid = s;
                super();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    public static void init(String dest, String cpid)
    {
        map.put(cpid, new RateSender(dest, cpid));
    }

    public void send(CpSpotRateInfo rate)
    {
        try
        {
            sender.sendMessage(rate);
            counter.incrementAndGet();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            log.error("SendRate ERR !", e);
        }
    }

    private static final Log log = LogUtil.getLog(jp/emcom/adv/fx/gws/mnrate/NMockStart);
    private final AtomicLong counter = new AtomicLong(0L);
    private SimpleSender sender;
    public static final Map map = new HashMap();



}

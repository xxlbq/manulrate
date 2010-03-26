// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NMocService.java

package jp.emcom.adv.fx.gws.mnrate;

import java.util.*;
import jp.emcom.adv.fx.gws.nmock.rate.RateSender;
import jp.emcom.adv.fx.gws.nmock.rate.generator.RateGeneraotor;
import jp.emcom.adv.fx.gws.nmock.tools.*;

public class NMocService
{

    public NMocService()
    {
    }

    private static void initRateGenerator(List lst, String cpid)
        throws Exception
    {
        RateCcpConfig c;
        RateGeneraotor generator;
        for(Iterator iterator = lst.iterator(); iterator.hasNext(); trigger.add(generator, c.getInterval()))
        {
            c = (RateCcpConfig)iterator.next();
            generator = (RateGeneraotor)Class.forName(c.getClz()).asSubclass(RateGeneraotor.class).newInstance();
            generator.registerParemeter(c.getCcp(), c.getParameter());
            generator.setcpid(cpid);
        }

    }

    public static void start(String file, String dest, String cpid)
        throws Exception
    {
        RateSender.init(dest, cpid);
        ConfigLoader.initialize(file);
        List lst = ConfigLoader.getConfig();
        initRateGenerator(lst, cpid);
    }

    private static final TimerTrigger trigger = new TimerTrigger(false) {

            
            {
                addListener(new jp.emcom.adv.fx.gws.nmock.tools.TimerTrigger.TimerTriggerListener() {

                    public void notification(RateGeneraotor generator, long time, long interval)
                    {
                        ((RateSender)RateSender.map.get(generator.getcpid())).send(generator.getRate());
                        NMocService.trigger.add(generator, interval);
                    }

                    public volatile void notification(Object obj, long l, long l1)
                    {
                        notification((RateGeneraotor)obj, l, l1);
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                });
            }
    };


}

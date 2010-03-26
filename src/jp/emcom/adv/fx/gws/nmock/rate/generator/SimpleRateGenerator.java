// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleRateGenerator.java

package jp.emcom.adv.fx.gws.nmock.rate.generator;

import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.bean.RateBandInfo;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package jp.emcom.adv.fx.gws.nmock.rate.generator:
//            RateGeneraotor

public class SimpleRateGenerator
    implements RateGeneraotor
{

    public SimpleRateGenerator()
    {
        flag = true;
    }

    public CpSpotRateInfo getRate()
    {
        counter++;
        return makeRate();
    }

    public void registerParemeter(String ccp, Map parm)
    {
        this.ccp = ccp;
        max = new BigDecimal((String)parm.get("maxprice"));
        min = new BigDecimal((String)parm.get("minprice"));
        srped = new BigDecimal((String)parm.get("spred"));
        scale = Integer.parseInt((String)parm.get("scale"));
        currentVal = min;
    }

    private CpSpotRateInfo makeRate()
    {
        if(flag)
        {
            currentVal = currentVal.add(baseScale[scale].multiply(srped));
            if(currentVal.compareTo(max) == 1)
                flag = false;
        } else
        {
            currentVal = currentVal.subtract(baseScale[scale].multiply(srped));
            if(currentVal.compareTo(min) == -1)
                flag = true;
        }
        RateBandInfo askRateBandInfo = new RateBandInfo();
        RateBandInfo bidRateBandInfo = new RateBandInfo();
        askRateBandInfo.setContractCurrency(ccp.substring(0, 3));
        askRateBandInfo.setContractLowerAmount("0");
        askRateBandInfo.setContractUpperAmount(new BigDecimal("3000000"));
        askRateBandInfo.setCounterCurrency(ccp.substring(4, 7));
        askRateBandInfo.setCounterLowerAmount("0");
        askRateBandInfo.setCounterUpperAmount(new BigDecimal("3000000"));
        askRateBandInfo.setPriceId(UUID.randomUUID().toString());
        askRateBandInfo.setRate(currentVal.add(baseScale[scale].multiply(srped)));
        askRateBandInfo.setTradable(true);
        askRateBandInfo.setValueDate("29991010");
        bidRateBandInfo.setContractCurrency(ccp.substring(0, 3));
        bidRateBandInfo.setContractLowerAmount("0");
        bidRateBandInfo.setContractUpperAmount(new BigDecimal("3000000"));
        bidRateBandInfo.setCounterCurrency(ccp.substring(4, 7));
        bidRateBandInfo.setCounterLowerAmount("0");
        bidRateBandInfo.setCounterUpperAmount(new BigDecimal("3000000"));
        bidRateBandInfo.setPriceId(UUID.randomUUID().toString());
        bidRateBandInfo.setRate(currentVal);
        bidRateBandInfo.setTradable(true);
        bidRateBandInfo.setValueDate("29991010");
        CpSpotRateInfo r = new CpSpotRateInfo();
        r.setCounterPartyId(cpid);
        r.setUsualable(true);
        r.setCurrencyPair(ccp);
        r.setMessageTime(new Date());
        r.setUsualable(true);
        r.setInManualStatus(0);
        r.setFxPriceId((new StringBuilder(String.valueOf(cpid))).append("-RatePriceId-").append((new DecimalFormat("00000")).format(c.getAndIncrement())).toString());
        r.setBookingType(1);
        r.setAskBandInfoList(askRateBandInfo);
        r.setBidBandInfoList(bidRateBandInfo);
        return r;
    }

    public int getCounter()
    {
        return counter;
    }

    public void setcpid(String cpid)
    {
        this.cpid = cpid;
    }

    public String getcpid()
    {
        return cpid;
    }

    private String ccp;
    private int counter;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal srped;
    private int scale;
    private BigDecimal currentVal;
    private boolean flag;
    private static final AtomicInteger c = new AtomicInteger(0);
    private static final BigDecimal baseScale[] = {
        new BigDecimal("1"), new BigDecimal("0.1"), new BigDecimal("0.01"), new BigDecimal("0.001"), new BigDecimal("0.0001"), new BigDecimal("0.00001"), new BigDecimal("0.000001"), new BigDecimal("0.0000001"), new BigDecimal("0.00000001"), new BigDecimal("0.000000001"), 
        new BigDecimal("0.00000000001"), new BigDecimal("0.000000000001")
    };
    private String cpid;

}

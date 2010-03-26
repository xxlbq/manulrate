// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RateGeneraotor.java

package jp.emcom.adv.fx.gws.nmock.rate.generator;

import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import java.util.Map;

public interface RateGeneraotor
{

    public abstract String getcpid();

    public abstract void setcpid(String s);

    public abstract int getCounter();

    public abstract CpSpotRateInfo getRate();

    public abstract void registerParemeter(String s, Map map);
}

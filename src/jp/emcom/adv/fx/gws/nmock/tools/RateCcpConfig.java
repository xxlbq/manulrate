// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RateCcpConfig.java

package jp.emcom.adv.fx.gws.nmock.tools;

import java.util.Map;

public class RateCcpConfig
{

    public RateCcpConfig()
    {
    }

    public String getCcp()
    {
        return ccp;
    }

    public void setCcp(String ccp)
    {
        this.ccp = ccp;
    }

    public String getClz()
    {
        return clz;
    }

    public void setClz(String clz)
    {
        this.clz = clz;
    }

    public long getInterval()
    {
        return interval;
    }

    public void setInterval(long interval)
    {
        this.interval = interval;
    }

    public Map getParameter()
    {
        return parameter;
    }

    public void setParameter(Map parameter)
    {
        this.parameter = parameter;
    }

    private String ccp;
    private String clz;
    private long interval;
    private Map parameter;
}

// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NMockStart.java

package jp.emcom.adv.fx.gws.mnrate;

import cn.bestwiz.jhf.core.dao.util.DbSessionFactory;

// Referenced classes of package jp.emcom.adv.fx.gws.mnrate:
//            MNRateReceiver

public class NMockStart
{

    public NMockStart()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        DbSessionFactory.beginTransaction(100);
        DbSessionFactory.commitTransaction(100);
        DbSessionFactory.beginTransaction(300);
        DbSessionFactory.commitTransaction(300);
        MNRateReceiver.start();
    }
}

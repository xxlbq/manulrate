// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigLoader.java

package jp.emcom.adv.fx.gws.nmock.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;

public class ConfigLoader
{
    private static final class NMock
    {

        public List config;

        private NMock()
        {
            config = new ArrayList();
        }
    }


    public ConfigLoader()
    {
    }

    public static void initialize(String file)
        throws FileNotFoundException
    {
        root = (NMock)JAXB.unmarshal(new FileInputStream(file), jp/emcom/adv/fx/gws/nmock/tools/ConfigLoader$NMock);
    }

    public static final List getConfig()
    {
        return root.config;
    }

    private static NMock root;
}

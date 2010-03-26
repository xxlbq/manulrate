// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimerTrigger.java

package jp.emcom.adv.fx.gws.nmock.tools;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class TimerTrigger
{
    private final class InterBean
        implements Delayed
    {

        public long getDelay(TimeUnit unit)
        {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public int compareTo(Delayed o)
        {
            return (int)(time - ((InterBean)o).time);
        }

        public int hashCode()
        {
            return obj.hashCode();
        }

        public boolean equals(Object bean)
        {
            if(this == bean)
                return true;
            if(bean == null)
                return false;
            if(getClass() != bean.getClass())
            {
                return false;
            } else
            {
                InterBean other = (InterBean)bean;
                return other.obj.equals(obj);
            }
        }

        public volatile int compareTo(Object obj1)
        {
            return compareTo((Delayed)obj1);
        }

        Object obj;
        long time;
        long interval;
        final TimerTrigger this$0;

        InterBean(Object obj, long time)
        {
            this$0 = TimerTrigger.this;
            super();
            this.obj = obj;
            interval = time;
            this.time = time + System.currentTimeMillis();
        }
    }

    public static interface TimerTriggerListener
    {

        public abstract void notification(Object obj, long l, long l1);
    }


    public TimerTrigger()
    {
        this(true);
    }

    public TimerTrigger(boolean isDaemon)
    {
        queue = new DelayQueue();
        listenerLst = new CopyOnWriteArrayList();
        t = new Thread(new Runnable() {

            public void run()
            {
                do
                    try
                    {
                        InterBean bean = (InterBean)queue.take();
                        TimerTriggerListener listerner;
                        for(Iterator iterator = listenerLst.iterator(); iterator.hasNext(); listerner.notification(bean.obj, bean.time, bean.interval))
                            listerner = (TimerTriggerListener)iterator.next();

                    }
                    catch(Exception dx)
                    {
                        dx.printStackTrace();
                    }
                while(true);
            }

            final TimerTrigger this$0;

            
            {
                this$0 = TimerTrigger.this;
                super();
            }
        });
        t.setDaemon(isDaemon);
    }

    public void addListener(TimerTriggerListener listener)
    {
        if(listener == null)
            throw new NullPointerException();
        synchronized(t)
        {
            if(t.getState() == Thread.State.NEW)
                t.start();
        }
        listenerLst.add(listener);
    }

    public void removeListener(TimerTriggerListener listener)
    {
        if(listener == null)
        {
            throw new NullPointerException();
        } else
        {
            listenerLst.remove(listener);
            return;
        }
    }

    public void add(Object obj, long delay)
    {
        queue.offer(new InterBean(obj, delay));
    }

    public void add(Object obj, long delay, TimeUnit unit)
    {
        queue.offer(new InterBean(obj, TimeUnit.MILLISECONDS.convert(delay, unit)));
    }

    public boolean cancel(Object obj)
    {
        return queue.remove(new InterBean(obj, 0L));
    }

    private DelayQueue queue;
    private List listenerLst;
    private Thread t;


}

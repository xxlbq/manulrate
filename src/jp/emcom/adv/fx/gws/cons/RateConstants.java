package jp.emcom.adv.fx.gws.cons;

import cn.bestwiz.jhf.core.jms.exception.JMSException;
import jp.emcom.adv.fx.gws.mnrate.CpInfo;

public class RateConstants {
	
	public static final String PROPERTY_FULL_PATH = "E:\\java\\eclipse-3.3-bestwiz-wp\\lmforever\\conf";
	public static final String COMMON_PROPERTY_NAME = "commonOrder.properties";

	
	
	
	
	public static CpInfo switchGwDes(String parityId) throws JMSException{
		
		if(parityId.equals("DB")){
			return new CpInfo("DB", "topic/gwCounterPartyDBRateTopic", "queue/gwDbOrderRequestQueue");
		}else if(parityId.equals("GS")){
			return new CpInfo("GS", "topic/gwCounterPartyGSRateTopic", "queue/gwGsOrderRequestQueue");
		}else if(parityId.equals("MOCK")){
			return new CpInfo("MOCK", "topic/gwCounterPartyMOCKRateTopic", "queue/gwMockOrderRequestQueue");
		}
		System.out.println("NOT A CP CODE :" + parityId);
		return null;
	}
	
	
}

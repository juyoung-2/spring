package org.joonzis.DI_3_scope;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		// 1. spring 컨테이너 구동
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("applicationContext3.xml");
		
		// 2. spring 컨테이너로부터 필요한 객체 요청
		TV ltv = (LgTV)ctx.getBean("ltv");
		TV ltv2 = (LgTV)ctx.getBean("ltv");
		ltv.powerOn();
		ltv.volumeUp();
		ltv.volumeDown();
		ltv.powerOff();
		
		System.out.println("-----------");
		
		TV stv = (SamsungTV)ctx.getBean("stv");
		TV stv2 = (SamsungTV)ctx.getBean("stv");
		stv.powerOn();
		stv.volumeUp();
		stv.volumeDown();
		stv.powerOff();
		
		System.out.println(ltv == ltv2 ? "같은 객체" : "다른 객체");
		System.out.println(stv == stv2 ? "같은 객체" : "다른 객체");
		
		ctx.close();
		
		
	}
}

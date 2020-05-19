package com.springbook.study;
import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 생성된 객체 getBean으로 가져옴
		CollectionBean_List listBean = (CollectionBean_List) factory.getBean("CollectionBean_List");
		List<String> addressList = listBean.getAddressList();
		
		for(String address : addressList) {
			System.out.println("주소: "+address);
		}
		
		CollectionBean_Map mapBean = (CollectionBean_Map) factory.getBean("CollectionBean_Map");
		Map<String, String> addressMap = mapBean.getAddress();
		
		System.out.println("고길동: "+addressMap.get("고길동"));
		System.out.println("마이콜: "+addressMap.get("마이콜"));
		
	}
}
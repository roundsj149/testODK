package com.springbook.study;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUnser {

	public static void main(String[] args) {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//이름이 tv인 객체 요청 및 반환
		TV Tv = (TV)factory.getBean("tv");

		Tv.turnOn();
		Tv.volumeUp();
		Tv.volumeDown();
		Tv.turnOff();
		
		TV Tv2 = (TV)factory.getBean("samsungTv");
		
		Tv2.turnOn();
		Tv2.volumeUp();
		Tv2.volumeDown();
		Tv2.turnOff();
		
		factory.close();
	}
}
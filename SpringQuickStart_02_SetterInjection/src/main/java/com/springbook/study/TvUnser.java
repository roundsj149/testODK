package com.springbook.study;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUnser {

	public static void main(String[] args) {
		// 스프링 설정 파일 로딩하여 컨테이너 구동 -> bean등록된 SamsungTv객체 생성
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
//		BeanFactory beanFactory = new BeanFactory();
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Samsung or LG: ");
//		String tvName = sc.next();
		
		//이름이 tv인 객체 요청 및 반환
		TV Tv = (TV)factory.getBean("stv");	// 이름이 tv인 클래스(applicationContext.xml에 등록되어있음
//		TV Tv2 = (TV)factory.getBean("ltv");	// 빈 설정에서 기본 scope가 singleton임. 즉,ㅏㅑㅏㅑ 객체 한 번만 생성되어 메모리에 유지됨
//		TV Tv3 = (TV)factory.getBean("ltv");	

		Tv.turnOn();
		Tv.volumeUp();
		Tv.volumeDown();
		Tv.turnOff();
		
		factory.close();
	}
}
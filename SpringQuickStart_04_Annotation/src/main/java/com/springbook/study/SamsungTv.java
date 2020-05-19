package com.springbook.study;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class SamsungTv implements TV{
	
	@Resource(name="sony")	// Resource: Autowired(변수의 타입을 기준으로 객체를 검색하여 의존성 주입 처리)와 달리 객체의 이름을 이용하여 의존성 주입 처리
	private Speaker speaker;

	public SamsungTv() {
		System.out.println("기본생성자 호출: SamsungTv객체 생성");
	}

	@Override
	public void turnOn() {
		System.out.println("삼성TV 켜기");
	}

	@Override
	public void turnOff() {
		System.out.println("삼성TV 끄기");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();

	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}
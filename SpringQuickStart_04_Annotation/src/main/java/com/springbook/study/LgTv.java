package com.springbook.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
	public class LgTv implements TV {
	
	@Autowired	// Autowired: 변수의 타입을 기준으로 객체를 검색하여 의존성 주입 처리
	/* Speaker 타입의 객체가 두 개 이상일 경우 둘 다 메모리에 생성되어 있으므로 어떤 객체를 의존성 주입할지 모르므로 에러가 발생함
	       따라서 스피커 객체의 이름(sony, apple(SonySpeaker, AppleSpeaker 클래스의 component에 적어준 이름) 중 하나를 지정하여 Qualifier에 적어줌으로써 처리 */
	@Qualifier("apple")
	private Speaker speaker;
	
	public LgTv() {
		System.out.println("기본생성자 호출: LgTv객체 생성");
	}
	@Override
	public void turnOn() {
		
		System.out.println("LG TV 켜기");
	}

	@Override
	public void turnOff() {
		
		System.out.println("LG TV 끄기");
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
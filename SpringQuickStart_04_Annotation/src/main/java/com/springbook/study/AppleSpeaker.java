package com.springbook.study;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {
	
	public AppleSpeaker() {
		System.out.println("AppleSpeaker 기본 생성자 호출: 객체 생성");
	}
	
	public void volumeUp() {
		
		System.out.println("AppleSpeaker의 볼륨을 높입니다.");
	}

	@Override
	public void volumeDown() {

		System.out.println("AppleSpeaker의 볼륨을 낮춥니다.");
	}
}

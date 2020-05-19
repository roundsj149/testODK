package com.springbook.study;

public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("SonySpeaker 기본 생성자 호출: 객체 생성");
	}
	
	public void volumeUp() {
		
		System.out.println("SonySpeaker의 볼륨을 높입니다.");
	}
	
	public void volumeDown() {
		
		System.out.println("SonySpeaker의 볼륨을 낮춥니다.");
	}
}

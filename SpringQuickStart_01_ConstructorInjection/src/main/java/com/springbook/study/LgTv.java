package com.springbook.study;

public class LgTv implements TV {

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
		
		System.out.println("LG TV 볼륨 높이기");
	}

	@Override
	public void volumeDown() {
		
		System.out.println("LG TV 볼륨 낮추기");
	}
}
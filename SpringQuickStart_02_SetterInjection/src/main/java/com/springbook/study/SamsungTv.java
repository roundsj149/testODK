package com.springbook.study;

public class SamsungTv implements TV{

//	private SonySpeaker speaker;
	private Speaker speaker;
	private int realPrice;
	private int salePrice;
	
	public SamsungTv() {
		System.out.println("기본생성자 호출: SamsungTv객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("setSpeaker() 호출");
		this.speaker = speaker;
	}
	
	public void setRealPrice(int realPrice) {
		this.realPrice = realPrice;
		System.out.println("setRealPrice() 호출");
	}
	
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public void turnOn() {
		System.out.println("삼성TV 켜기 - 정가: "+realPrice+"할인가: "+salePrice);
	}

	@Override
	public void turnOff() {
		System.out.println("삼성TV 끄기");
	}

	@Override
	public void volumeUp() {
//		speaker = new SonySpeaker();
		speaker.volumeUp();
//		System.out.println("삼성TV 볼륨 높이기");

	}

	@Override
	public void volumeDown() {
//		speaker = new SonySpeaker();
		speaker.volumeDown();
//		System.out.println("삼성TV 볼륨 낮추기");

	}
}
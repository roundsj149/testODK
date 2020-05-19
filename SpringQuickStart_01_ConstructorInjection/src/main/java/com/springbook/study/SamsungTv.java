package com.springbook.study;

public class SamsungTv implements TV{

//	private SonySpeaker speaker;
	private Speaker speaker;
	private int realPrice;
	private int salePrice;
	
	public SamsungTv() {
		System.out.println("기본생성자 호출: SamsungTv객체 생성");
	}
	
	public SamsungTv(Speaker speaker) {
		
		System.out.println("SamsungTv: 매개변수가 있는 생성자(2) 호출");
		this.speaker = speaker;
		
	}
	
	public SamsungTv(Speaker speaker, int realPrice, int salePrice) {	// xml파일의 index에 따라 바뀜
		
		System.out.println("SamsungTv: 매개변수가 두 개 있는 생성자(3) 호출");
		this.speaker = speaker;
		this.realPrice = realPrice;
		this.salePrice = salePrice;
		
	}
	
	public void initMethod() {
		System.out.println("객체 초기화 작업: applicationContext.xml의 빈 설정에 init-method속성 추가");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제 전 처리해야할 로직: applicationContext.xml의 빈 설정에 destroy-method속성 추가");
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
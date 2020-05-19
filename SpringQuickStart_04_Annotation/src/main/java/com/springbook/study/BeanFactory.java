package com.springbook.study;

public class BeanFactory {

	public BeanFactory() {
		
	}
	
	public TV getBean(String beanName) {
		
		if(beanName.equals("Samsung")) {
			
			return new SamsungTv();
			
		} else if(beanName.equals("LG")) {
			
			return new LgTv();
			
		}
		return null;
	}
}
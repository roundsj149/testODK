package com.springbook.study;

import java.util.*;

public class CollectionBean_Map {
	
	Map<String, String> map;

	public CollectionBean_Map() {
		System.out.println("CollectionBean_Map 생성자 호출");
	}
	
	public void setMapAddressList(Map<String, String> map) {
		
		this.map = map;
	}
	
	public Map<String, String> getAddress() {
		
		return map;
	}
}

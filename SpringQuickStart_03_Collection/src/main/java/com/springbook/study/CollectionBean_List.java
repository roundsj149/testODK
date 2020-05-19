package com.springbook.study;

import java.util.*;

public class CollectionBean_List {
	
	private List<String> addressList;
	
	public CollectionBean_List() {
		System.out.println("collectionBean_List 생성자 호출");
	}
	
	public void setAddressList(List<String> addressList) {
		
		this.addressList = addressList;
	}
	
	public List<String> getAddressList() {
		
		return addressList;
	}
}

package com.ja.freeboard.util;

import java.security.MessageDigest;

// String이 들어오면 String이 나오게
public class FBMessageDigest {

	public static String digest(String value) {

		String hashCode = null;
		
		// 비밀번호 해싱
		try {

			StringBuilder sb = new StringBuilder(); // 문자열 쌓기 위한 변수 생성 - 문자열 그냥 연산하면 안됨!(메모리 낭비)

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1"); // 문자 압축 - SHA-1 방식으로 인코딩 하겠다
			messageDigest.reset();
			messageDigest.update((value+"@Fj4#a0Y").getBytes()); // 바이트로 변환 - 보안성 높이기 위해 뒤에 아무 문자열 더해줌
			byte[] chars = messageDigest.digest(); // 문자열에 해당하는 해싱 값이 바이트로 나옴

			// 문자열로 만들어주기
			for (int i = 0; i < chars.length; i++) {

				String temp = Integer.toHexString(chars[i] & 0xff);
				// hashCode += temp; // 문자열 연산 시 메모리 계속 새로 생성됨(같은 메모리에 뒤에 붙는 형태가 아님) -> 속도 느려짐

				if (temp.length() == 1) { // 예외처리: 문자열 붙일 때 한 자리이면 앞에 0 붙여주어 자릿수 맞춰줌(항상 똑같은 크기의 문자열 만들기 위해)
					sb.append("0");
				}
				sb.append(temp); // 자료구조 - 문자열 쌓기
			}

			hashCode = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashCode;
	}
}
package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
	
	static int CURR_RANK = 1;	// 현재 순위 정보
	
/*
 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데,
 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하시오
 */
	
	public static void main(String[] args) {
		List<DispalyCharacter> disCharList = new ArrayList<DispalyCharacter>();
		disCharList.add(new DispalyCharacter("김민강"));
		disCharList.add(new DispalyCharacter("이현수"));
		disCharList.add(new DispalyCharacter("권기혁"));
		disCharList.add(new DispalyCharacter("이영준"));
		
		for (Thread th : disCharList) {
			th.start();
		}
		
		for (Thread th : disCharList) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 정렬하기
		Collections.sort(disCharList);	// 정렬하기
		
		System.out.println("경기 끝...");
		System.out.println("---------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("=====================");
		System.out.println("순위\t : \t 이름");
		System.out.println("---------------------");
		
		for (DispalyCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t : \t" + dc.getName());
		}
	}
}

// 알파벳 대문자를 출력하기 위한 스레드
class DispalyCharacter extends Thread implements Comparable<DispalyCharacter>{
	private String name;
	private int rank;
	
	public DispalyCharacter(String name) {
		super(name);
		this.name = name;
	}
	
	

	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}



	@Override
	public void run() {
		for (char ch = 'A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			try {
				Thread.sleep((int)(Math.random() * 301 + 200));	// 200 ~ 500 사이의 난수
				
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		setRank(T11DisplayCharacterTest.CURR_RANK++);
		
		System.out.println(name + " 출력 끝...");
	}
	
	/**
	 * 순위의 오름차순으로 정렬
	 */
	public int compareTo(DispalyCharacter dc) {
		return new Integer(this.rank).compareTo(dc.getRank());
	}
}
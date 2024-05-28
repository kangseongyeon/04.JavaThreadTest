package kr.or.ddit.basic;

public class T04ThreadTest {
/*
 * 1~20억까지의 합계를 구하는데 걸린 시간 체크하기
 * 
 * 단독으로 (스레드 1개) 합계를 구하는 경우와 여러 개의 스레드로 나누어 합계를 구했을 때의 시간 확인
 */
	
	public static void main(String[] args) {
		// 단독으로 처리할 때...
		SumThread sTh = new SumThread(1L, 2000000000L);
		
		long startTime = System.currentTimeMillis();
		
		sTh.start();
		
		try {
			sTh.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리 했을 때의 처리 시간 및 합계 : " + (endTime - startTime) + "(ms)");
		
		System.out.println();
		System.out.println("---------------------------------------------");
		
		// 여러 스레드가 협력해서 처리 했을 때...
		SumThread[] sumThs = new SumThread[] {
				new SumThread(		   1L,  500000000L),
				new SumThread( 500000001L, 1000000000L),
				new SumThread(1000000001L, 1500000000L),
				new SumThread(1500000001L, 2000000000L)
		};
		
		
		startTime = System.currentTimeMillis();
		
		for (Thread th : sumThs) {
			th.start();
		}
		for (Thread th : sumThs) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력 처리 했을 때의 처리 시간 : " + (endTime - startTime) + "(ms)");
	}
}

class SumThread extends Thread {
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for (long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "까지의 합계 : " + sum);
	}
}
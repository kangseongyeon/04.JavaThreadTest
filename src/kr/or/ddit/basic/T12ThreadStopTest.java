package kr.or.ddit.basic;

/**
 * 스레드 종료 예제
 * @author 6
 *
 */
public class T12ThreadStopTest {
	public static void main(String[] args) {
		/*
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 스레드 종료시키기...
		// th1.stop();
		
		th1.setStoped(true);
		*/
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();	// 인터럽트 걸기...
	}
}



class ThreadStopEx1 extends Thread {
	private boolean isStoped;
	
	public boolean isStoped() {
		return isStoped;
	}

	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}

	
	@Override
	public void run() {
		while(!isStoped) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
	}
}

// interrupt()메서드를 이용하여 스레드를 종료시키는 방법
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		/*
		// 방법1 => sleep()이나 join() 메서드 등을 사용했을 때 interrupt() 메서드를 호출하면
		// 		   InterruptedException이 발생한다. 이 예외를 이용하는 방법
		
		try {
			while (true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1);
			}
		} catch(InterruptedException ex) {}
		
		*/
		
		// 방법2 => interrupt() 메서드가 호출되었는지 검사하기
		while (true) {
			System.out.println("스레드 처리 중...");
			
			/*
			// 검사방법1 => 스레드의 인스턴스 메서드를 이용하는 방법
			if (this.isInterrupted()) {
				System.out.println("인스턴스 메서드 isInterrupted() 호출됨");
				break;
			}
			*/
			
			// 검사방법2 => 스레드의 정적 메서드를 이용하는 방법
			if(Thread.interrupted()) {
				System.out.println("정적메서드 interrupted() 호출됨");
				break;
			}
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
	}
}
package javaSe.netORio.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * �ܵ������
 */
class Man implements Runnable {

	private PipedOutputStream pos = new PipedOutputStream();
	
	public  PipedOutputStream getPos(){
		return pos;
	}

	public void run() {
		try {
			pos.write("����Ҫ�����š�\n���ȣ�Ҫ�е�Ǯ�ƣ����õ��������ײ�\n��Σ�Ҫ�е��Ĳɣ����˼��е�С��ݡ�\n���Ҳ������Ҫ�ģ������̬�Ȼ��������ū�š�\n�����������㶼��ҪŬ����".getBytes());
		} catch (IOException e) {

		} finally {
			if (pos != null) {
				try {
					pos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/**
 * �ܵ�������,��������
 */
class Woman implements Runnable {

	private PipedInputStream pis = new PipedInputStream();

	
	public PipedInputStream getPis(){
		return  pis;
	}
	public void run() {
		try {
			byte[] bys = new byte[1024];
			int len = 0;

			while ((len = pis.read(bys)) != -1) {
				System.out.println(new String(bys, 0, len));
			}

		} catch (IOException e) {

		} finally {
			if (pis != null) {
				try {
					pis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/**
 * �ܵ������������߳�֮�佻������
 * 
 * Man�̶߳� Woman�߳�˵,******,Woman�߳� ���ܵ�����Ϣ����ӡ����
 */
public class PipedStreamDemo {
	public static void main(String[] args) throws IOException {
		/**
		 * �����̶߳���,����Ҫ����,�ܵ�
		 */
		
		Man man = new Man();
		Woman woman   = new Woman();
		
		/*
		 * �����ܵ�����
		 */
		man.getPos().connect(woman.getPis());
		
//		woman.getPis().connect(man.getPos());
		
		new Thread(man).start();
		new Thread(woman).start();
		
	}
}

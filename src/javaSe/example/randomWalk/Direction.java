package javaSe.example.randomWalk;

/**
 * ��������һ�����������4ȡ��õ��ĸ���λ�����ֱ�ʾ����ʾ��������ѡ��
 * @author liuyuchen
 *
 */
public class Direction {
	/**
	 * ����һ��1��100��һ�������(ԭ������100��4�ı����������������)
	 */
	int rand = (int)(Math.random()*100 + 1);
	/**
	 * �õ�һ��1��4����������
	 * 1����
	 * 2����
	 * 3����
	 * 4����
	 * ���ط���ȡֵ
	 */
	public int direction() {
		return (rand % 4 +1);
	}
}

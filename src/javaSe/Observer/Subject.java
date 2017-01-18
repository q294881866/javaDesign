package Observer;

public interface Subject {
	/* ���ӹ۲��� */
	void add(Observer observer);

	/* ɾ���۲��� */
	void del(Observer observer);

	/* ֪ͨ���еĹ۲��� */
	void notifyObservers();

	/* ����Ĳ��� */
	void operation();
}

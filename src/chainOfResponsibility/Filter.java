package chainOfResponsibility;

public interface Filter {
	/**
	 * ������ģʽ
	 * Ӧ����web����Ĺ�����
	 * ��������ip
	 * �����ַ���gbk utf8
	 * ���Ϸ�������
	 * @param request
	 * @param response
	 * @param chain
	 */
	void doFilter(Request request, Response response, FilterChain chain);
}

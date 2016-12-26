package chainOfResponsibility;

public interface Filter {
	/**
	 * 责任链模式
	 * 应用于web方面的过滤器
	 * 例如拦截ip
	 * 拦截字符：gbk utf8
	 * 不合法的链接
	 * @param request
	 * @param response
	 * @param chain
	 */
	void doFilter(Request request, Response response, FilterChain chain);
}

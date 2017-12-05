package javaSe.example.randomWalk;

/**
 * 采用生成一个随机数，和4取余得到四个方位的数字表示来表示随机方向的选择
 * @author liuyuchen
 *
 */
public class Direction {
	/**
	 * 生成一个1到100的一个随机数(原因在于100是4的倍数，这样概率相等)
	 */
	int rand = (int)(Math.random()*100 + 1);
	/**
	 * 得到一个1到4的数代表方向
	 * 1：东
	 * 2：南
	 * 3：西
	 * 4：北
	 * 返回方向取值
	 */
	public int direction() {
		return (rand % 4 +1);
	}
}

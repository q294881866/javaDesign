package someQuestion.randomWalk;
/**
 * 下一步运算
 * 通过调用方向函数，作出下一步的运算
 * @author liuyuchen
 *
 */
public class NextStep {
	int[] point = new int[2];
	int x, y;
	int direction;
	public NextStep(int[] point1, int direction) {
		x = point1[0];
		y = point1[1];
		this.direction= direction;
	}
	/**
	 * 传入当前点的坐标，返回下一步点的坐标
	 * @return
	 */
	public int[] nextStep() {
		int step = 5;					//步伐大小
		
		switch(direction) {
			case 1:	x += step;	break;
			case 2:	y -= step;	break;
			case 3:	x -= step;	break;
			case 4:	y += step;	break;
		}
		point[0] = x;
		point[1] = y;
		return point;
	}
}

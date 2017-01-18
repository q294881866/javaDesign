package someQuestion;

import java.util.Arrays;

public class KuaiSan {

	Integer[] num = new Integer[3]; // 用来存产生的号码

	KuaiSan(Integer... owner ) {

		this.num = owner;
		System.out.print("本期号码为：");
		for (int i : num)
			System.out.print(i);
		System.out.println();
	}

	// 产生随机号码
	private void randomSan() {
		for (int i = 0; i < 3; i++)
			num[i] = (int) ((Math.random() * 300 % 6) + 1);
		Arrays.sort(num);
	}

	/**
	 * 核对号码，是否中奖
	 * 
	 * @param a
	 *            用来表示六大类的中奖方式
	 * @param b
	 *            用来表示每个大类的中奖方式下的小类别
	 * @param number
	 *            需要核实的下注号码
	 */
	void check(int a, int b, Integer[] number) {

		Arrays.sort(number); // 对下注号码排序
		// 输出下注号码
		System.out.print("下注号码为：");
		for (int i : number)
			System.out.print(i);
		System.out.println();

		int numSum = num[0] + num[1] + num[2]; // 开奖号码和
		int numberSum = number[0] + number[1] + number[2]; // 用户号码和
		switch (a) {
		// 选择三个数的和值为中奖标准
		case 1:
			switch (b) {
			// 和值相同中奖
			case 1:
				if (numberSum == numSum)
					System.out.println("恭喜你，(三数和)中奖啦！");
				else
					System.out.println("很遗憾，(三数和)你没中！");
				break;
			// 和值单双相同中奖
			case 2:
				if ((numberSum % 2) == (numSum % 2))
					System.out.println("恭喜你，(三数和单双)中奖啦！");
				else
					System.out.println("很遗憾，(三数和单双)你没中！");
				break;
			// 和值大小中奖
			case 3:
				if ((numberSum < 11 && numSum < 11)
						|| (numberSum > 10 && numSum > 10))
					System.out.println("恭喜你，(三数和大小)中奖啦！");
				else
					System.out.println("很遗憾，(三数和大小)你没中！");
				break;
			}
			break;
		// 三同号中奖方式
		case 2:
			switch (b) {
			// 只要开奖号码为三个同号，就中奖
			case 1:
				if (num[0] == num[2]) // 排序后如果第一个和最后一个相等，则表示是三个同号
					System.out.println("恭喜你，(三同号通选)中奖啦！");
				else
					System.out.println("很遗憾，(三同号通选)你没中！");
				break;
			// 开奖号为三同号，且和你买的三同号一样
			case 2:
				if (((num[0] == num[2]) && (number[0] == number[2]))
						&& (num[1] == number[1]))
					System.out.println("恭喜你，(三同号单选)中奖啦！");
				else
					System.out.println("很遗憾，(三同号单选)你没中！");
				break;
			}
			break;
		// 二同号中奖方式
		case 3:
			switch (b) {
			// 如果是两连号，则开奖号和下注号的中间数字必定相等
			case 1:
				if ((number[1] == num[1])
						&& ((num[1] == num[0]) || (num[1] == num[2])))
					System.out.println("恭喜你，(二同号复选)中奖啦！");
				else
					System.out.println("很遗憾，(二同号复选)你没中！");
				break;
			// 二同号单选
			case 2:
				if ((number[1] == num[1])
						&& ((number[0] == num[0]) || (number[2] == num[2])))
					System.out.println("恭喜你，(二同号单选)中奖啦！");
				else
					System.out.println("很遗憾，(二同号单选)你没中！");
				break;
			}
			break;
		// 三不同号中奖方式
		case 4:
			if ((num[0] != num[1]) && (num[1] != num[2])) // 开奖号码都不同
				if (((num[0] == number[0]) && (num[1] == number[1]))
						&& (num[2] == number[2])) // 下注号码与开奖号码一样
					System.out.println("恭喜你，(三不同号)中奖啦！");
				else
					System.out.println("很遗憾，(三不同)你没中！");
			break;
		// 二不同号中奖方式
		case 5:
			if (((number[0] == num[0]) && ((number[1] == num[1]) || (number[1] == num[2])))
					|| ((number[0] == num[1]) && (number[1] == num[2])))
				System.out.println("恭喜你，(二不同号)中奖啦！");
			else
				System.out.println("很遗憾，(二不同号)你没中！");
			break;
		// 三连号
		case 6:
			int num1 = 100 * num[0] + 10 * num[1] + num[2];
			if (((num1 == 123 || num1 == 234) || num1 == 345) || num1 == 456)
				System.out.println("恭喜你，(三连号通选)中奖啦！");
			else
				System.out.println("很遗憾，(三连号通选)你没中！");
			break;
		default:
			System.out.println("Input Error");
			break;
		}
	}

	public static void main(String[] args) {
		Integer[] number = { 1, 3, 2 };
		new KuaiSan().check(6, 1, number);
	}

}

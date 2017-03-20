package javaSe.example;

import java.util.Arrays;

public class KuaiSan {

	 /**用来存产生的号码*/
	Integer[] jackpot = new Integer[3];

	KuaiSan(Integer... owner) {
		this.jackpot = owner;
		System.out.print("本期号码为：");
		for (int i : jackpot)
			System.out.print(i);
		System.out.println();
	}

	/**
	 * 产生随机号码
	 */
	private void randomSan() {
		for (int i = 0; i < 3; i++)
			jackpot[i] = (int) ((Math.random() * 300 % 6) + 1);
		Arrays.sort(jackpot);
	}

	/**
	 * 核对号码，是否中奖
	 * 
	 * @param a
	 *            用来表示六大类的中奖方式
	 * @param b
	 *            用来表示每个大类的中奖方式下的小类别
	 * @param number
	 *            要核实的下注号码
	 */
	void check(int a, int b, Integer[] number) {

		Arrays.sort(number); 
		System.out.print("下注号码为：");
		for (int i : number)
			System.out.print(i);
		System.out.println();

		int numSum = jackpot[0] + jackpot[1] + jackpot[2]; // 中奖号码和
		int numberSum = number[0] + number[1] + number[2]; // 用户号码和
		switch (a) {
		// 选择三个数的和值为中奖标准
		case 1:
			switch (b) {
			case 1:// 和值相同中奖
				if (numberSum == numSum)
					System.out.println("恭喜你，(三数和)中奖啦！");
				else
					System.out.println("很遗憾，(三数和)你没中！");
				break;
			case 2:// 和值单双相同中奖
				if ((numberSum % 2) == (numSum % 2))
					System.out.println("恭喜你，(三数和单双)中奖啦！");
				else
					System.out.println("很遗憾，(三数和单双)你没中！");
				break;
			case 3:// 和值大小中奖
				if ((numberSum < 11 && numSum < 11) || (numberSum > 10 && numSum > 10))
					System.out.println("恭喜你，(三数和大小)中奖啦！");
				else
					System.out.println("很遗憾，(三数和大小)你没中！");
				break;
			}
			break;
		case 2:// 三同号中奖方式
			switch (b) {
			// 只要中奖号码为三个同号，就中奖
			case 1:
				if (jackpot[0] == jackpot[2]) 
					System.out.println("恭喜你，(三同号)中奖啦！");
				else
					System.out.println("很遗憾，(三同号)你没中！");
				break;
			// 中奖号为三同号，且和你买的三同号一样
			case 2:
				if (((jackpot[0] == jackpot[2]) && (number[0] == number[2])) && (jackpot[1] == number[1]))
					System.out.println("恭喜你，(三同号)中奖啦！");
				else
					System.out.println("很遗憾，(三同号)你没中！");
				break;
			}
			break;
		case 3:// 二同号中奖方式
			switch (b) {
			case 1:// 如果是两连号，则中奖号和下注号的中间数字必定相等
				if ((number[1] == jackpot[1]) && ((jackpot[1] == jackpot[0]) || (jackpot[1] == jackpot[2])))
					System.out.println("恭喜你，(二同号复等)中奖啦！");
				else
					System.out.println("很遗憾，(二同号复等)你没中！");
				break;
			case 2:// 二同号单双
				if ((number[1] == jackpot[1]) && ((number[0] == jackpot[0]) || (number[2] == jackpot[2])))
					System.out.println("恭喜你，(二同号单双)中奖啦！");
				else
					System.out.println("很遗憾，(二同号单双)你没中！");
				break;
			}
			break;
		case 4:// 三不同号中奖方式
			if ((jackpot[0] != jackpot[1]) && (jackpot[1] != jackpot[2])) // 等奖号码都不同
				if (((jackpot[0] == number[0]) && (jackpot[1] == number[1])) && (jackpot[2] == number[2])) // 下注号码与开奖号码一等
					System.out.println("恭喜你，(三不同号)中奖啦！");
				else
					System.out.println("很遗憾，(三不等)你没中！");
			break;
		case 5:// 二不同号中奖方式
			if (((number[0] == jackpot[0]) && ((number[1] == jackpot[1]) || (number[1] == jackpot[2])))
					|| ((number[0] == jackpot[1]) && (number[1] == jackpot[2])))
				System.out.println("恭喜你，(二不同号)中奖啦！");
			else
				System.out.println("很遗憾，(二不同号)你没中！");
			break;
		case 6:// 三连号
			int num1 = 100 * jackpot[0] + 10 * jackpot[1] + jackpot[2];
			if (((num1 == 123 || num1 == 234) || num1 == 345) || num1 == 456)
				System.out.println("恭喜你，(三连号)中奖啦！");
			else
				System.out.println("很遗憾，(三连号)你没中！");
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

package javaSe;

import java.util.Scanner;

public class Juzhen {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("输入行m=");
		int m = input.nextInt();
		System.out.print("输入列n=");
		int n = input.nextInt();
		int[][] ss = new int[m][n];
		System.out.println("没转置前矩阵：");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.println("输入数字");
				ss[i][j] = input.nextInt();
				if (j == m - 1) {
					System.out.println(ss[i][j] + " ");
				} else
					System.out.print(ss[i][j] + " ");
			}
			
		}
		
		System.out.println("转置后矩阵：");
		for (int i1 = 0; i1 < n; i1++) {
			for (int j1 = 0; j1 < m; j1++) {
				if (j1 == 2) {
					System.out.println(ss[j1][i1] + " ");
				} else {
					System.out.print(ss[j1][i1] + " ");
				}
			}
		}
	}
}

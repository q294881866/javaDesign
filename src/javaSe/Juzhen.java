package javaSe;

import java.util.Scanner;

public class Juzhen {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("������m=");
		int m = input.nextInt();
		System.out.print("������n=");
		int n = input.nextInt();
		int[][] ss = new int[m][n];
		System.out.println("ûת��ǰ����");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.println("��������");
				ss[i][j] = input.nextInt();
				if (j == m - 1) {
					System.out.println(ss[i][j] + " ");
				} else
					System.out.print(ss[i][j] + " ");
			}
			
		}
		
		System.out.println("ת�ú����");
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

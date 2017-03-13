package javaSe.basic.dataStructure.matrix;

import java.io.*;
import java.util.*;

class Matrix // 定义矩阵结构
{
	public int[][] m ;
	public Matrix() {
		m = new int[32][32];
	}
	public Matrix(int n) {
		m = new int[n][n];
	}
}

public class StrassenMatrix {
	public int IfIsEven(int n)
	{// 判断输入矩阵阶数是否为2^k
		int a = 0, temp = n;
		while (temp % 2 == 0) {
			if (temp % 2 == 0)
				temp /= 2;
			else
				a = 1;
		}
		if (temp == 1)
			a = 0;
		return a;
	}

	public void Divide(Matrix d, Matrix d11, Matrix d12, Matrix d21,
			Matrix d22, int n)
	{// 分解矩阵
		int i, j;
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++) {
				d11.m[i][j] = d.m[i][j];
				d12.m[i][j] = d.m[i][j + n];
				d21.m[i][j] = d.m[i + n][j];
				d22.m[i][j] = d.m[i + n][j + n];
			}
	}

	public Matrix Merge(Matrix a11, Matrix a12, Matrix a21, Matrix a22, int n)
	{// 合并矩阵
		int i, j;
		Matrix a = new Matrix(n);
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++) {
				a.m[i][j] = a11.m[i][j];
				a.m[i][j + n] = a12.m[i][j];
				a.m[i + n][j] = a21.m[i][j];
				a.m[i + n][j + n] = a22.m[i][j];
			}
		return a;
	}

	public Matrix TwoMatrixMultiply(Matrix x, Matrix y)
	{ // 阶数为2的矩阵乘法
		int m1, m2, m3, m4, m5, m6, m7;
		Matrix z = new Matrix();

		m1 = (y.m[1][2] - y.m[2][2]) * x.m[1][1];
		m2 = y.m[2][2] * (x.m[1][1] + x.m[1][2]);
		m3 = (x.m[2][1] + x.m[2][2]) * y.m[1][1];
		m4 = x.m[2][2] * (y.m[2][1] - y.m[1][1]);
		m5 = (x.m[1][1] + x.m[2][2]) * (y.m[1][1] + y.m[2][2]);
		m6 = (x.m[1][2] - x.m[2][2]) * (y.m[2][1] + y.m[2][2]);
		m7 = (x.m[1][1] - x.m[2][1]) * (y.m[1][1] + y.m[1][2]);
		z.m[1][1] = m5 + m4 - m2 + m6;
		z.m[1][2] = m1 + m2;
		z.m[2][1] = m3 + m4;
		z.m[2][2] = m5 + m1 - m3 - m7;
		return z;
	}

	public Matrix MatrixPlus(Matrix f, Matrix g, int n) 
	{// 矩阵加法
		int i, j;
		Matrix h = new Matrix();
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++)
				h.m[i][j] = f.m[i][j] + g.m[i][j];
		return h;
	}

	public Matrix MatrixMinus(Matrix f, Matrix g, int n) 
	{// 矩阵减法方法
		int i, j;
		Matrix h = new Matrix();
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++)
				h.m[i][j] = f.m[i][j] - g.m[i][j];
		return h;
	}

	public Matrix MatrixMultiply(Matrix a, Matrix b, int n) 
	{// 矩阵乘法方法
		int k;
		Matrix a11, a12, a21, a22;
		a11 = new Matrix(n);
		a12 = new Matrix(n);
		a21 = new Matrix(n);
		a22 = new Matrix(n);
		Matrix b11, b12, b21, b22;
		b11 = new Matrix(n);
		b12 = new Matrix(n);
		b21 = new Matrix(n);
		b22 = new Matrix(n);
		Matrix c11, c12, c21, c22, c;
		c11 = new Matrix(n);
		c12 = new Matrix(n);
		c21 = new Matrix(n);
		c22 = new Matrix(n);
		c = new Matrix(n);
		Matrix m1, m2, m3, m4, m5, m6, m7;
		k = n;
		if (k == 2) {
			c = TwoMatrixMultiply(a, b);
			return c;
		} else {
			k = n / 2;
			/*
			 * 拆分A、B、C矩阵
			 */
			Divide(a, a11, a12, a21, a22, k); 
			Divide(b, b11, b12, b21, b22, k);
			Divide(c, c11, c12, c21, c22, k);

			m1 = MatrixMultiply(a11, MatrixMinus(b12, b22, k), k);
			m2 = MatrixMultiply(MatrixPlus(a11, a12, k), b22, k);
			m3 = MatrixMultiply(MatrixPlus(a21, a22, k), b11, k);
			m4 = MatrixMultiply(a22, MatrixMinus(b21, b11, k), k);
			m5 = MatrixMultiply(MatrixPlus(a11, a22, k),
					MatrixPlus(b11, b22, k), k);
			m6 = MatrixMultiply(MatrixMinus(a12, a22, k),
					MatrixPlus(b21, b22, k), k);
			m7 = MatrixMultiply(MatrixMinus(a11, a21, k),
					MatrixPlus(b11, b12, k), k);
			c11 = MatrixPlus(MatrixMinus(MatrixPlus(m5, m4, k), m2, k), m6, k);
			c12 = MatrixPlus(m1, m2, k);
			c21 = MatrixPlus(m3, m4, k);
			c22 = MatrixMinus(MatrixMinus(MatrixPlus(m5, m1, k), m3, k), m7, k);

			// 合并C矩阵
			c = Merge(c11, c12, c21, c22, k); 
			return c;
		}
	}

	public Matrix GetMatrix(Matrix x, int n) {
		x = new Matrix(n);
		SimpleMatrix.createMatrixByRadom(x.m);
		return x;
	}

	public Matrix UsualMatrixMultiply(Matrix A, Matrix B, Matrix C, int n) {
		int i, j, t, k;
		for (i = 1; i <= n; i++)
			for (j = 1; j <= n; j++) {
				for (k = 1, t = 0; k <= n; k++)
					t += A.m[i][k] * B.m[k][j];
				C.m[i][j] = t;
			}
		return C;
	}

	public static void main(String[] args) throws IOException {
		StrassenMatrix instance = new StrassenMatrix();
		int  n;
		// Matrix A, B, C, D;
		Matrix A, B, C;
		Scanner in = new Scanner(System.in);
		System.out.print("输入矩阵的阶数: ");
		n = in.nextInt();
		A = new Matrix(n);
		B = new Matrix(n);
		C = new Matrix(n);
		// D = new matrix();
		if (instance.IfIsEven(n) == 0) {
			System.out.println("矩阵A:");
			A = instance.GetMatrix(A, n);
			SimpleMatrix.printResult(A.m);
			System.out.println("矩阵B:");
			B = instance.GetMatrix(B, n);
			SimpleMatrix.printResult(B.m);
			if (n == 1)
				C.m[1][1] = A.m[1][1] * B.m[1][1]; // 矩阵阶数为1时的特殊处理
			else {
				long startTime = new Date().getTime();
				C = instance.MatrixMultiply(A, B, n);
				long finishTime = System.currentTimeMillis();
				System.out.println("计算完成,用时" + (finishTime - startTime) + "毫秒");
			}
			System.out.println("Strassen矩阵C为:");
			SimpleMatrix.printResult(C.m);
			/*
			 * D = instance.UsualMatrixMultiply(A, B, D, n);
			 * System.out.println("普通乘法矩阵D为:"); for (i = 1; i <= n; i++) { for
			 * (j = 1; j <= n; j++) System.out.print(D.m[i][j] + " ");
			 * System.out.println(); }
			 */
		} else
			System.out.println("输入的阶数不是2的N次方");
	}
}

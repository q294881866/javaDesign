package javaSe.basic.dataStructure.base;

import java.util.LinkedList;
import java.util.List;

/**
 * ƽ�������<br>
 * ǰ�к�������
 */
public class BinTree {

	private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 ,9};
	/** ���ڴ洢���ڵ� */	private List<TreeNode> nodeList = null;
	/** Ĭ�Ϲ��췽�� */	public BinTree() {	this(null);	}

	public BinTree(int[] array) {
		if (null == array) {// ����Ϊ�գ���Ĭ�ϵ�����
			array = this.array;
		}
		// 1.��ʼ�� ����ڵ���ӵ�����
		nodeList = new LinkedList<TreeNode>();
		for (int i = 0; i < array.length; i++) {
			nodeList.add(new TreeNode(array[i]));
		}

		// 2.���������� ���ڵ���:7/2 =3
		for (int i = 0; i < array.length / 2 ; i++) {
			// ����
			nodeList.get(i).left = nodeList.get(i * 2 + 1);
			// �Һ���
			if ((i * 2 + 2)<array.length) {
				// ���һ�����ڵ����û���Һ���
				nodeList.get(i).right = nodeList.get(i * 2 + 2);
			}
		}
	}

	/**
	 * �ȸ������<br>
	 * ��������
	 */
	public static void preorderTraversal(TreeNode node) {
		if (node == null)
			return;
		System.out.print(node.data + " ");// �ȴ�ӡ���ڵ�
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}

	/**
	 * �и������<br>
	 * �󡢸�����
	 */
	public static void inorderTraversal(TreeNode node) {
		if (node == null)
			return;
		inorderTraversal(node.left);
		System.out.print(node.data + " ");// �м��ӡ���ڵ�
		inorderTraversal(node.right);
	}

	/**
	 * ��������<br>
	 * ���ҡ���
	 */
	public static void subsequentTraversal(TreeNode node) {
		if (node == null)
			return;
		subsequentTraversal(node.left);
		subsequentTraversal(node.right);
		System.out.print(node.data + " ");// ����ӡ���ڵ�
	}

	public static void main(String[] args) {
		BinTree binTree = new BinTree();
		// nodeList�е�0����������ֵ��Ϊ���ڵ�
		TreeNode root = binTree.nodeList.get(0);

		System.out.println("�ȸ��������");
		preorderTraversal(root);
		System.out.println();

		System.out.println("�и��������");
		inorderTraversal(root);
		System.out.println();

		System.out.println("����������");
		subsequentTraversal(root);
	}

}

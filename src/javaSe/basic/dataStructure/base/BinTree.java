package javaSe.basic.dataStructure.base;

import java.util.LinkedList;
import java.util.List;

/**
 * 平衡二叉树<br>
 * 前中后根序遍历
 */
public class BinTree {

	private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 ,9};
	/** 用于存储树节点 */	private List<TreeNode> nodeList = null;
	/** 默认构造方法 */	public BinTree() {	this(null);	}

	public BinTree(int[] array) {
		if (null == array) {// 数组为空，用默认的例子
			array = this.array;
		}
		// 1.初始化 数组节点添加到集合
		nodeList = new LinkedList<TreeNode>();
		for (int i = 0; i < array.length; i++) {
			nodeList.add(new TreeNode(array[i]));
		}

		// 2.构建二叉树 父节点数:7/2 =3
		for (int i = 0; i < array.length / 2 ; i++) {
			// 左孩子
			nodeList.get(i).left = nodeList.get(i * 2 + 1);
			// 右孩子
			if ((i * 2 + 2)<array.length) {
				// 最后一个父节点可能没有右孩子
				nodeList.get(i).right = nodeList.get(i * 2 + 2);
			}
		}
	}

	/**
	 * 先根序遍历<br>
	 * 根、左、右
	 */
	public static void preorderTraversal(TreeNode node) {
		if (node == null)
			return;
		System.out.print(node.data + " ");// 先打印根节点
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}

	/**
	 * 中根序遍历<br>
	 * 左、根、右
	 */
	public static void inorderTraversal(TreeNode node) {
		if (node == null)
			return;
		inorderTraversal(node.left);
		System.out.print(node.data + " ");// 中间打印根节点
		inorderTraversal(node.right);
	}

	/**
	 * 后根序遍历<br>
	 * 左、右、根
	 */
	public static void subsequentTraversal(TreeNode node) {
		if (node == null)
			return;
		subsequentTraversal(node.left);
		subsequentTraversal(node.right);
		System.out.print(node.data + " ");// 最后打印根节点
	}

	public static void main(String[] args) {
		BinTree binTree = new BinTree();
		// nodeList中第0个索引处的值即为根节点
		TreeNode root = binTree.nodeList.get(0);

		System.out.println("先根序遍历：");
		preorderTraversal(root);
		System.out.println();

		System.out.println("中根序遍历：");
		inorderTraversal(root);
		System.out.println();

		System.out.println("后根序遍历：");
		subsequentTraversal(root);
	}

}

package javase.basic.dataStructure.base;

public class TreeNode implements Comparable<TreeNode> {

	public TreeNode right;//右孩子
	public TreeNode left;//左孩子
	public int data;//数据 ：这里的数据可以是其他类型

	public TreeNode(TreeNode right, TreeNode left, int data) {
		super();
		this.right = right;
		this.left = left;
		this.data = data;
	}
	public TreeNode(int newData) {
		this.data = newData;
	}

	public int compareTo(TreeNode o) {
		return this.data-o.data;
	}
}

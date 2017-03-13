package javaSe.basic.dataStructure.base;

public class TreeNode implements Comparable<TreeNode> {

	public TreeNode right;//�Һ���
	public TreeNode left;//����
	public int data;//���� ����������ݿ�������������

	public TreeNode(TreeNode right, TreeNode left, int data) {
		super();
		this.right = right;
		this.left = left;
		this.data = data;
	}
	public TreeNode(int newData) {
		this.data = newData;
	}

	@Override
	public int compareTo(TreeNode o) {
		return this.data-o.data;
	}
}

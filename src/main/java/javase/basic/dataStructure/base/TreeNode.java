package javase.basic.dataStructure.base;

import lombok.Data;

/**
 * @author peipengfei
 */
@Data
public class TreeNode implements Comparable<TreeNode> {

    /**
     * 右孩子
     */
    public TreeNode right;
    /**
     * 左孩子
     */
    public TreeNode left;
    /**
     * 数据 ：这里的数据可以是其他类型
     */
    public int data;

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
        return this.data - o.data;
    }
}

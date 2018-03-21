package counter.attack.Interview.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Create by zhang on 2018/3/20
 * 二叉树的各种遍历法
 */
public class Tree {
    /**
     * 递归中序遍历
     * @param root
     */
    public void inOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        inOrder(root.left);
        System.out.println(root.value);
        inOrder(root.right);
    }

    /**
     * 非递归方式中序遍历
     * @param root
     */
    public void nonRecursiveInOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        Stack<Integer> stack = new Stack<>();
        BinaryTree.TreeNode treeNode = root;
        while (!stack.isEmpty() || treeNode != null) {
            if (treeNode != null) {
                stack.push(treeNode.value);
                treeNode = treeNode.left;//指针指向左子树
            }else {//没有左子树
                System.out.println(stack.pop());//出栈
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 递归前序遍历
     * @param root
     */
    public void preOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 非递归前序遍历
     * @param root
     */
    public void nonRecursivePreOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        BinaryTree.TreeNode treeNode = root;
        // 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
        // 所以，只要不同时满足这两点，都需要进入循环
        while (!stack.isEmpty() || treeNode != null) {
            if (treeNode != null) {
                System.out.println(treeNode.value);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }else {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 递归后序遍历
     * @param root
     */
    public void postOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.value);
    }

    /**
     * 非递归后序遍历
     * @param root
     */
    public void notRecursivePostOrder(BinaryTree.TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

    }
}

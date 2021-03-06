package company.facebook;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;
/**
 * 15分钟coding判断一个tree是否是BST,时间比较短就没有用Morris遍历写，写了个简单的递归实现，用递归的时候可以包装一个result class,
class Result{
                boolean isBST; // 以此为root是否是BST
                int val; //本输出点的值 Note: 只track这个value行吗，我的方案是记录min, max
}
，然后和他讲了一下morris怎么做
 */
public class ValidateBST {
	boolean validateBSTInOrderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		int prev = Integer.MIN_VALUE;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			
			if (node.val <= prev) {
				return false;
			}
			
			cur = node.right;
			prev = node.val;
		}
		
		return true;
	}
	
	// Recursion. This is easier
    public boolean isValidBST(TreeNode root) {
        return helper(root);
    }
    
    TreeNode pre = null; // Keep track of previous node
    boolean helper(TreeNode node) {
        if (node == null) {
            return true;
        }
        
        boolean left = helper(node.left);
        
        if (pre != null && pre.val >= node.val) {
            return false;
        }
        
        pre = node;
        boolean right = helper(node.right);
        
        return left && right;
    }
    
	boolean validateBST(TreeNode root) {
		ResultType rt = dfs(root);
		return rt.isBST;
	}
	
	ResultType dfs(TreeNode root) {
		if (root == null) {
			return new ResultType(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
		}
		
		ResultType left = dfs(root.left);
		if (!left.isBST) {
			return new ResultType(-1, -1, false);
		}
		
		ResultType right = dfs(root.right);
		if (!right.isBST) {
			return new ResultType(-1, -1, false);
		}
		
		if (root.val < left.max || root.val > right.min) {
			return new ResultType(-1, -1, false);
		}
		
		return new ResultType(Math.min(root.val, left.min), Math.max(root.val, right.max), true);
	}
	
	class ResultType {
		int min;
		int max;
		boolean isBST;
		
		ResultType(int min, int max, boolean isBST) {
			this.min = min;
			this.max = max;
			this.isBST = isBST;
		}
	}
}


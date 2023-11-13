package work;

public class TreeNode <T>{
	private T data;
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	
	public TreeNode(T nodeData)
	{
		data = nodeData;
		leftChild = null;
		rightChild = null;
	}
	
	public TreeNode(TreeNode<T> node)
	{
		data = node.data;
		rightChild = node.rightChild;
		leftChild = node.leftChild;
	}
	
	public T getData()
	{
		return data;
	}

}

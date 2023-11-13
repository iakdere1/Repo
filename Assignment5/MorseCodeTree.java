package work;
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	private TreeNode<String> root;

	public MorseCodeTree()
	{
		root = new TreeNode<String>("");
		buildTree();
	}
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
		
	}

	@Override
	public void insert(String code, String result) {
		addNode(root, code, result);
		
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() == 1)
		{
			if (code.charAt(0) == '.')
			{
				root.leftChild = new TreeNode<String>(letter);
			}
			else if (code.equals("-"))
			{
				root.rightChild = new TreeNode<String>(letter);
			}
		}
		else
		{
			if (code.charAt(0) == '.')
			{
				addNode(root.leftChild, code.substring(1), letter);			
			}
			else if (code.charAt(0) == '-')
			{
				addNode(root.rightChild, code.substring(1), letter);
			}
		}
		
	}

	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length() == 1)
		{
			if (code.charAt(0) == '.')
			{
				return root.leftChild.getData();
			}
			else
			{
				return root.rightChild.getData();
			}
		}
		else
		{
			if (code.charAt(0) != '.')
			{
				return fetchNode(root.rightChild, code.substring(1));
			}
			else
			{
				return fetchNode(root.leftChild, code.substring(1));
			}
		}
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
		
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> newArrayList = new ArrayList<String>();
		LNRoutputTraversal(root, newArrayList);
		return newArrayList;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root != null)
		{
			LNRoutputTraversal(root.leftChild, list);
			list.add(root.getData());
			LNRoutputTraversal(root.rightChild,list);
		}
		else
		{
			return;
		}
		
	}
	
	
	
	

}

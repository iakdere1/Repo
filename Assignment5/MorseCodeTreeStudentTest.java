package work;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeTreeStudentTest {
MorseCodeTree morseCodeTree;
TreeNode<String> freshNode = new TreeNode<>("blank");

	@BeforeEach
	void setUp() throws Exception {
		morseCodeTree = new MorseCodeTree();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		morseCodeTree = null;
	}

	@Test
	void getRootTest() {
		assertTrue(morseCodeTree.getRoot().getData().equals(""));
		freshNode.leftChild = morseCodeTree.getRoot().leftChild;
		morseCodeTree.setRoot(freshNode);
	}
	
	@Test
	public void fetchTest() {
		assertTrue(morseCodeTree.fetch(".").equals("e"));
		assertTrue(morseCodeTree.fetch("-").equals("t"));
		assertTrue(morseCodeTree.fetch("..").equals("i"));
		assertTrue(morseCodeTree.fetch(".-").equals("a"));
		assertTrue(morseCodeTree.fetch("-.").equals("n"));
	}
	
	@Test
	public void insertTest() {
		morseCodeTree.insert(".", "A");
		morseCodeTree.insert(".-", "C");
		assertEquals("A",morseCodeTree.getRoot().leftChild.getData());
		assertEquals("C", morseCodeTree.getRoot().leftChild.rightChild.getData());
	}
	
	@Test
	public void toArrayListTest() {
		assertTrue(morseCodeTree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]"));
		morseCodeTree.insert("----", "GH");
	}

}

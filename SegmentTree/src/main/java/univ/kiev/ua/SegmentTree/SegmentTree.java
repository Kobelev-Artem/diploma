package univ.kiev.ua.SegmentTree;
import java.util.List;

public class SegmentTree {

	private Node node;
//	private int leftPos;
//	private int rightPos;

	public SegmentTree() {}
	
	//---BUILD TREE---
	public static SegmentTree build(List<Long> values){
		SegmentTree tree = new SegmentTree();
		Segment segment = new Segment(1, values.size());
		tree.setNode(tree.build(values, segment));
		
		return tree;
	}

	protected Node build(List<Long> values, Segment segment) { // int leftPos, int rightPos) {
		if (segment.isSingle()) {
			return buildTreeLeaf(values, segment.getLeft());
		}

		return buildTreeWithSubtrees(values, segment);
	}

	private Node buildTreeLeaf(List<Long> values, int leftPos) {
		Node leaf = new Node()
				.setValue(values.get(leftPos - 1))
				.setLeft(null)
				.setRight(null)
				.setLeftPos(leftPos)
				.setRightPos(leftPos);
		return leaf;
	}

	private Node buildTreeWithSubtrees(List<Long> values, Segment segment) {
		int middlePos = segment.getMiddle();

		Node leftTree = build(values, new Segment(segment.getLeft(), middlePos));
		Node rightTree = build(values, new Segment(middlePos + 1, segment.getRight()));

		Node tree = new Node()
				.setLeft(leftTree)
				.setRight(rightTree)
				.setValue(leftTree.getValue() + rightTree.getValue())
				.setLeftPos(leftTree.getLeftPos())
				.setRightPos(rightTree.getRightPos());

		return tree;
	}

	//---COUNT SUM---
	public long sum(Segment segment) {
//		this.leftPos = leftPos;
//		this.rightPos = rightPos;
		return sum(node, segment);
	}

	private long sum(Node node, Segment segment) {
		int leftPos = checkLeftPosition(segment.getLeft(), node.getLeftPos());
		segment = segment.setLeft(leftPos);
		int rightPos = checkRightPosition(segment.getRight(), node.getRightPos());
		segment = segment.setRight(rightPos);
		if (segment.isLeftBiggerThenRight())
			return 0;

		if (node.isMatchToSegment(segment))
			return node.getValue();

		return sumValuesOfSubtrees(node, segment);
	}

	private int checkLeftPosition(int leftPos, int treeLeftPos) {
		return Math.max(leftPos, treeLeftPos);
	}

	private int checkRightPosition(int rightPos, int treeRightPos) {
		return Math.min(rightPos, treeRightPos);
	}

	private long sumValuesOfSubtrees(Node node, Segment segment) {
		long leftSum = sum(node.getLeft(), segment);
		long rightSum = sum(node.getRight(), segment);
		return leftSum + rightSum;
	}

	//---UPDATE TREE---
	public void addToLeaf(int pos, long value) {
		updateLeaf(node, pos, value);
	}

	private void updateLeaf(Node node, int pos, long value) {
		if (node.isLeaf() && node.getLeftPos() == pos) {
			node.setValue(node.getValue() + value);
			return;
		}

		updateSubtree(node, pos, value);

		node.updateValue();
	}
	
	private void updateSubtree(Node node, int pos, long value) {
		Node subtree = node.getSubtreeWhichContainsPos(pos);
		updateLeaf(subtree, pos, value);
	}
	
	//---OTHER METHODS---
	public Node getLeafByPos(int pos){
		return node.getLeafByPos(pos);
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node tree) {
		this.node = tree;
	}
	
}

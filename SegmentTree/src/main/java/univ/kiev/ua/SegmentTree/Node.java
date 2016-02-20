package univ.kiev.ua.SegmentTree;

public class Node {

	private Node left;
	private Node right;
	private Node subTree;
	private long value;
	private long toAdd;
	private int leftPos;
	private int rightPos;
	
	private SegmentTree subtree;
	
	
	public void updateValue() {
		value = left.getValue() + right.getValue();
	}
	
	public boolean isLeaf(){
		return leftPos == rightPos;
	}
	
	public boolean isMatchToSegment(Segment segment){
		return leftPos == segment.getLeft() && rightPos == segment.getRight();
	}
	
	public Node getSubtreeWhichContainsPos(int pos) {
		if (positionInLeftSubtree(pos))
			return left;
		return right;
	}

	private boolean positionInLeftSubtree(int pos) {
		return pos <= left.getRightPos();
	}

	public Node getLeft() {
		return left;
	}

	public Node setLeft(Node left) {
		this.left = left;
		return this;
	}

	public Node getRight() {
		return right;
	}

	public Node setRight(Node right) {
		this.right = right;
		return this;
	}

	public long getValue() {
		return value;
	}

	public Node setValue(long value) {
		this.value = value;
		return this;
	}

	public int getLeftPos() {
		return leftPos;
	}

	public Node setLeftPos(int leftPos) {
		this.leftPos = leftPos;
		return this;
	}

	public int getRightPos() {
		return rightPos;
	}

	public Node setRightPos(int rightPos) {
		this.rightPos = rightPos;
		return this;
	}

	public long getToAdd() {
		return toAdd;
	}

	public void setToAdd(long toAdd) {
		this.toAdd = toAdd;
	}

	public SegmentTree getSubtree() {
		return subtree;
	}

	public void setSubtree(SegmentTree subtree) {
		this.subtree = subtree;
	}

	public Node getSubTree() {
		return subTree;
	}

	public void setSubTree(Node subTree) {
		this.subTree = subTree;
	}
	
}

package univ.kiev.ua.SegmentTree;

public final class Node {

	private Node left;
	private Node right;
//	private Node subTree;
	private long value;
//	private long toAdd;
	private int leftPos;
	private int rightPos;
	
	private SegmentTree subTree;
	
	
	public void updateValue() {
		value = left.getValue() + right.getValue();
	}
	
	public boolean isLeaf(){
		return leftPos == rightPos;
	}
	
	public boolean isMatchToSegment(Segment segment){
		return leftPos == segment.getLeft() && rightPos == segment.getRight();
	}
	
	public boolean isNodeContainsPos(int pos){
		return (pos >= leftPos && pos <= rightPos);
	}
	
	public Node getSubtreeWhichContainsPos(int pos) {
		if (!isNodeContainsPos(pos))
			throw new IllegalArgumentException("Position is not in the current tree segment" +
					"\nLeft position = " + leftPos + "\nRight position = " + rightPos +
					"\nRequested position = " + pos);
		if (positionInLeftSubtree(pos))
			return left;
		return right;
	}
	
	private boolean positionInLeftSubtree(int pos) {
		return pos <= left.getRightPos();
	}
	
	public Node getLeafByPos(int pos){
		if (!isNodeContainsPos(pos))
			throw new IllegalArgumentException("Position is not in the current tree segment" +
					"\nLeft position = " + leftPos + "\nRight position = " + rightPos +
					"\nRequested position = " + pos);
		return getLeafByPos(this, pos);
	}
	
	private Node getLeafByPos(Node node, int pos){
		if (node.isLeaf()){
			if (node.getLeftPos() == pos)
				return node;
			else throw new IllegalStateException("Поиск по дереву по позиции "
					+ "нашел листок, не соответствующий заданой позиции." +
					"\nRequested position = " + pos +
					"\n Found position = " + node.getLeftPos());
		}
		return getLeafByPos(node.getSubtreeWhichContainsPos(pos), pos);
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

//	public long getToAdd() {
//		return toAdd;
//	}
//
//	public void setToAdd(long toAdd) {
//		this.toAdd = toAdd;
//	}

	public SegmentTree getSubTree() {
		return subTree;
	}

	public Node setSubTree(SegmentTree subTree) {
		this.subTree = subTree;
		return this;
	}

//	public Node getSubTree() {
//		return subTree;
//	}
//
//	public Node setSubTree(Node subTree) {
//		this.subTree = subTree;
//		return this;
//	}
	
}

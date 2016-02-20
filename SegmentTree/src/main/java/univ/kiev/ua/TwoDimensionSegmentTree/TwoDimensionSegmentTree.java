package univ.kiev.ua.TwoDimensionSegmentTree;

import java.util.List;

import univ.kiev.ua.SegmentTree.Node;
import univ.kiev.ua.SegmentTree.Segment;
import univ.kiev.ua.SegmentTree.SegmentTree;

public class TwoDimensionSegmentTree extends SegmentTree{
	
//	private int leftPosX; 
//	private int rightPosX; 
//	private int leftPosY; 
//	private int rightPosY;
	
	
	public TwoDimensionSegmentTree(List<Long> values) {
		super();
	}
	
	public long sum(Segment segmentX, Segment segmentY) {
//		this.leftPosX = leftPosX;
//		this.leftPosY = leftPosY;
//		this.rightPosX = rightPosX;
//		this.rightPosY = rightPosY;
		return sum(getNode(), segmentX, segmentY);
	}
	
	private long sum(Node node, Segment segmentX, Segment segmentY){
		int leftPosX = checkLeftPosition(segmentX.getLeft(), node.getLeftPos());
		segmentX = segmentX.setLeft(leftPosX);
		int rightPosX = checkRightPosition(segmentX.getRight(), node.getRightPos());
		segmentX = segmentX.setRight(rightPosX);
		if (segmentX.isLeftBiggerThenRight())
			return 0;

		if (node.isMatchToSegment(segmentX)){
			if (node.getSubTree() != null)
				return node.getSubtree().sum(segmentY);
			return node.getValue();
		}
		
		return sumValuesOfSubtrees(node, segmentX, segmentY);
	}

	private int checkLeftPosition(int leftPos, int treeLeftPos) {
		return Math.max(leftPos, treeLeftPos);
	}

	private int checkRightPosition(int rightPos, int treeRightPos) {
		return Math.min(rightPos, treeRightPos);
	}

	private long sumValuesOfSubtrees(Node tree, Segment segmentX, Segment segmentY) {
		long leftSum = sum(tree.getLeft(), segmentX, segmentY);
		long rightSum = sum(tree.getRight(), segmentX, segmentY);
		return leftSum + rightSum;
	}

}

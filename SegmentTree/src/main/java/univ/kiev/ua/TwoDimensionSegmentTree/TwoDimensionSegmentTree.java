package univ.kiev.ua.TwoDimensionSegmentTree;

import java.util.List;

import univ.kiev.ua.SegmentTree.Node;
import univ.kiev.ua.SegmentTree.Segment;
import univ.kiev.ua.SegmentTree.SegmentTree;

public class TwoDimensionSegmentTree extends SegmentTree{
	
	public TwoDimensionSegmentTree() {
//		super();
	}
	
	//---BUILD TREE---
	public static TwoDimensionSegmentTree build2(List<List<Long>> values){
		TwoDimensionSegmentTree tree = new TwoDimensionSegmentTree();
		Segment segmentX = new Segment(1, values.size());
//		Segment segmentY = new Segment(1, values.get(0).size());
		tree.setNode(tree.buildX(values, segmentX));
		
		return tree;
	}
	
	private Node buildX(List<List<Long>> values, Segment segmentX) {
		if (segmentX.isSingle()){
			Segment segmentY = new Segment(1, values.get(segmentX.getLeft() - 1).size());
			Node nodeY = build(values.get(segmentX.getLeft() - 1), segmentY);
			
			SegmentTree subTree = new SegmentTree();
			subTree.setNode(nodeY);
			
			Node leafX = new Node()
					.setSubTree(subTree)
					.setLeft(null)
					.setRight(null)
					.setLeftPos(segmentX.getLeft())
					.setRightPos(segmentX.getRight());
			return leafX;
		}
		
		int middle = segmentX.getMiddle();
		Node leftTreeX = buildX(values, new Segment(segmentX.getLeft(), middle));
		Node rightTreeX = buildX(values, new Segment(middle + 1, segmentX.getRight()));
		
		Segment segmentY = new Segment(1, values.get(segmentX.getLeft() - 1).size());
		Node nodeY = buildY(leftTreeX, rightTreeX, segmentY);
		
		SegmentTree subTree = new SegmentTree();
		subTree.setNode(nodeY);

		Node tree = new Node()
				.setLeft(leftTreeX)
				.setRight(rightTreeX)
				.setSubTree(subTree)
				.setLeftPos(leftTreeX.getLeftPos())
				.setRightPos(rightTreeX.getRightPos());
		
		return tree;
	}
	
	private Node buildY(Node leftTreeX, Node rightTreeX, Segment segmentY) {
		if (segmentY.isSingle()) {
			return buildYLeaf(leftTreeX, rightTreeX, segmentY.getLeft());
		}
		
		int middlePos = segmentY.getMiddle();
		Node leftTreeY = buildY(leftTreeX, rightTreeX, new Segment(segmentY.getLeft(), middlePos));
		Node rightTreeY = buildY(leftTreeX, rightTreeX, new Segment(middlePos + 1, segmentY.getRight()));
		
		Node treeY = new Node()
				.setLeft(leftTreeY)
				.setRight(rightTreeY)
				.setValue(leftTreeY.getValue() + rightTreeY.getValue())
				.setLeftPos(leftTreeY.getLeftPos())
				.setRightPos(rightTreeY.getRightPos());

		return treeY;
	}

	private Node buildYLeaf(Node leftTree, Node rightTree, int pos) {
		Node leaf = new Node()
				.setValue(leftTree.getSubTree().getLeafByPos(pos).getValue() +
						  rightTree.getSubTree().getLeafByPos(pos).getValue() )
				.setLeft(null)
				.setRight(null)
				.setLeftPos(pos)
				.setRightPos(pos);
		return leaf;
	}
	
	//---COUNT SUM---
	public long sum(Segment segmentX, Segment segmentY) {
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
				return node.getSubTree().sum(segmentY);
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
	
	//---UPDATE TREE---
	public void add(int posX, int posY, long value){
		add(getNode(), posX, posY, value);
	}
	
	private void add(Node node, int posX, int posY, long value){
		if (node.isLeaf() && node.getLeftPos() == posX){
			node.getSubTree().addToLeaf(posY, value);
			return;
		}
		
		Node subtree = node.getSubtreeWhichContainsPos(posX);
		add(subtree, posX, posY, value);
		
		updateSubtree(node.getLeft(), node.getRight(), node.getSubTree().getNode(), posY);
	}
	
	private void updateSubtree(Node leftTree, Node rightTree, Node node, int posY){
		if (node.isLeaf()){
			node.setValue(leftTree.getSubTree().getLeafByPos(posY).getValue() +
						  rightTree.getSubTree().getLeafByPos(posY).getValue());
			return;
		}
		
		Node subTree = node.getSubtreeWhichContainsPos(posY);
		updateSubtree(leftTree, rightTree, subTree, posY);
		
		node.updateValue();
	}

}

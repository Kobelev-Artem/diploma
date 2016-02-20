package univ.kiev.ua.SegmentTree;

public class Segment {
	
	private int left;
	private int right;
	
	
	public Segment(int left, int right){
		this.left = left;
		this.right = right;
	}
	
	public boolean isSingle(){
		return left == right;
	}
	
	public boolean isLeftBiggerThenRight(){
		return left > right;
	}
	
	public int getMiddle(){
		return (left + right) / 2;
	}
	
	public int getLeft() {
		return left;
	}
	
	public Segment setLeft(int left) {
		return new Segment(left, right);
	}
	
	public int getRight() {
		return right;
	}
	public Segment setRight(int right) {
		return new Segment(left, right);
	}

}

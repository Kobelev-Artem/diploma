package univ.kiev.ua.SegmentTree;

import java.util.List;

public class SegmentTreeWithGroupModification extends SegmentTree{
	
	public SegmentTreeWithGroupModification(List<Long> values) {
		super();
	}
	
	public void addToSegment(int L, int R, int value){
		addToSegment(getNode(), L, R, value);
	}
	
	private void addToSegment(Node tree, int L, int R, int value) {
		// Обрежем отрезок [L; R] таким образом, чтобы он входил в отрезок
		// [tree->LeftPos; tree->RightPos],
		// которому соответсвтует вершина дерева tree. Если полученный отрезок
		// окажется пустым (L < R),
		// то выходим из процедуры.

		if (L < tree.getLeftPos())
			L = tree.getLeftPos();
		if (R > tree.getRightPos())
			R = tree.getRightPos();
		if (L > R)
			return;

		// Проведем проталкивание, если add не равно нулю.

		if (tree.getToAdd() != 0L) {
			if (tree.getLeft() != null) {
				tree.getLeft().setToAdd(tree.getLeft().getToAdd() + tree.getToAdd());
				tree.getLeft().setValue(tree.getLeft().getValue()
						+ tree.getToAdd() * (tree.getLeft().getRightPos() - tree.getLeft().getLeftPos() + 1));
			}
			if (tree.getRight() != null) {
				tree.getRight().setToAdd(tree.getRight().getToAdd() + tree.getToAdd());
				tree.getRight().setValue(tree.getRight().getValue()
						+ tree.getToAdd() * (tree.getRight().getRightPos() - tree.getRight().getLeftPos() + 1));
			}
			tree.setToAdd(0L);
		}

		// Если корень дерева tree соответствует отрезку [L..R], то изменяем
		// данные в нем

		if ((tree.getLeftPos() == L) && (tree.getRightPos() == R)) {
			tree.setToAdd(tree.getToAdd() + value);
			tree.setValue(tree.getValue() + value * (tree.getRightPos() - tree.getLeftPos() + 1));
			return;
		}

		// Рекурсивно модифицируем левое и правое поддерево.

		addToSegment(tree.getLeft(), L, R, value);
		addToSegment(tree.getRight(), L, R, value);
		tree.setValue(tree.getLeft().getValue() + tree.getRight().getValue());
	}
	
	@Override
	public long sum(Segment segment){
		return 0;
	}

}

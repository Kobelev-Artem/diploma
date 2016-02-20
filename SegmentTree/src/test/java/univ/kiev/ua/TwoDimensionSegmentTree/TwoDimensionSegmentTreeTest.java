package univ.kiev.ua.TwoDimensionSegmentTree;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import univ.kiev.ua.SegmentTree.Segment;
import univ.kiev.ua.SegmentTree.SegmentTree;

public class TwoDimensionSegmentTreeTest {
	
	private List<List<Long>> values;
	
	private TwoDimensionSegmentTree tree;
	
	@Test
	public void should(){
		values = new ArrayList<List<Long>>();
		for (int i = 1; i <= 9; i++){
			List<Long> v = new ArrayList<>();
			for (int j = 1; j <= 9; j++){
				v.add(1L);
			}
			values.add(v);
		}
		tree = TwoDimensionSegmentTree.build2(values);
		
		assertThat(tree.sum(new Segment(1, 9), new Segment(1, 9)), equalTo(81L));
		assertThat(tree.sum(new Segment(5, 5), new Segment(5, 5)), equalTo(1L));
		assertThat(tree.sum(new Segment(2, 5), new Segment(2, 7)), equalTo(24L));
		assertThat(tree.sum(new Segment(9, 9), new Segment(4, 7)), equalTo(4L));
		assertThat(tree.sum(new Segment(8, 9), new Segment(2, 2)), equalTo(2L));
		assertThat(tree.sum(new Segment(4, 9), new Segment(2, 5)), equalTo(24L));
	}
	
	@Test
	public void testWithModification(){
		values = new ArrayList<List<Long>>();
		for (int i = 1; i <= 9; i++){
			List<Long> v = new ArrayList<>();
			for (int j = 1; j <= 9; j++){
				v.add(1L);
			}
			values.add(v);
		}
		tree = TwoDimensionSegmentTree.build2(values);
		
		assertThat(tree.sum(new Segment(1, 9), new Segment(1, 9)), equalTo(81L));
		tree.add(9, 9, 4);
		assertThat(tree.sum(new Segment(9, 9), new Segment(9, 9)), equalTo(5L));
		tree.add(3, 5, 2);
		assertThat(tree.sum(new Segment(2, 7), new Segment(3, 7)), equalTo(32L));
	}

}

package univ.kiev.ua.SegmentTree;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SegmentTreeTest {
	
	private List<Long> values;
	
	private SegmentTree tree;
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void shouldAddToLeaf(){
		values = new ArrayList<Long>(3);
		for (int i = 1; i <= 3; i++){
			values.add((long)i);
		}
		tree = SegmentTree.build(values);
		
		assertThat(tree.sum(new Segment(1, 3)), equalTo(6L));
		tree.addToLeaf(3, -1L);
		assertThat(tree.sum(new Segment(1, 3)), equalTo(5L));
	}

	@Test
	public void shouldAddToLeaf2(){
		values = new ArrayList<Long>(21);
		for (int i = 1; i <= 21; i++){
			values.add((long)1);
		}
		tree = SegmentTree.build(values);
		
		assertThat(tree.sum(new Segment(1, 21)), equalTo(21L));
		assertThat(tree.sum(new Segment(17, 17)), equalTo(1L));
		assertThat(tree.sum(new Segment(16, 21)), equalTo(6L));
		assertThat(tree.sum(new Segment(17, 17)), equalTo(1L));
		assertThat(tree.sum(new Segment(16, 17)), equalTo(2L));
		assertThat(tree.sum(new Segment(14, 16)), equalTo(3L));
		tree.addToLeaf(19, 5L);
		assertThat(tree.sum(new Segment(18, 21)), equalTo(9L));
	}

}

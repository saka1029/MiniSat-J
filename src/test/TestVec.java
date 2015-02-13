package test;

import static org.junit.Assert.*;
import jp.saka1029.minisatj.mtl.Vec;

import org.junit.Test;

public class TestVec {

	@Test
	public void testImax() {
		assertEquals(20, Vec.imax(10, 20));
		assertEquals(21, Vec.imax(11, 21));
	}

	@Test
	public void testVec() {
		Vec<Integer> v = new Vec<Integer>();
		assertEquals(0, v.capacity());
		assertEquals(0, v.size());
	}

	@Test
	public void testVecInt() {
		Vec<Integer> v = new Vec<Integer>(10);
		assertEquals(10, v.size());
		assertTrue(v.capacity() >= v.size());
		assertEquals(null, v.get(0));
		assertEquals(null, v.get(9));
	}

	@Test
	public void testVecIntT() {
		Vec<String> v = new Vec<String>(10, "NULL");
		assertEquals(10, v.size());
		assertTrue(v.capacity() >= v.size());
		assertEquals("NULL", v.get(0));
		assertEquals("NULL", v.get(9));
	}

	@Test
	public void testSize() {
		Vec<Integer> v = new Vec<Integer>(10);
		assertEquals(10, v.size());
	}

	@Test
	public void testShrink() {
		Vec<Integer> v = new Vec<Integer>(10);
		v.shrink(5);
		assertEquals(5, v.size());
	}

	@Test
	public void testShrink_() {
		Vec<Integer> v = new Vec<Integer>(10);
		v.shrink(5);
		assertEquals(5, v.size());
	}

	@Test
	public void testCapacity() {
		for (int i = 1; i < 100; ++i) {
			Vec<Integer> v = new Vec<Integer>(i);
//			System.out.printf("i=%d capacity()=%d%n", i, v.capacity());
			assertTrue(v.capacity() >= i);
		}
	}

	@Test
	public void testPush() {
		Vec<Integer> v = new Vec<>();
		v.push();
		assertEquals(1, v.size());
		assertEquals(null, v.get(0));
	}

	@Test
	public void testPushT() {
		Vec<Integer> v = new Vec<>();
		v.push(3);
		assertEquals(1, v.size());
		assertEquals(3, (int)v.get(0));
	}

	@Test
	public void testPop() {
		Vec<Integer> v = new Vec<>(3);
		v.pop();
		assertEquals(2, v.size());
		v.pop();
		v.pop();
		try {
			v.pop();
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testLast() {
		Vec<Integer> v = new Vec<>();
		v.push(0);
		v.push(1);
		v.push(2);
		assertEquals(2, (int)v.last());
		v.shrink(3);
		try {
			v.last();
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testGet() {
		Vec<Integer> v = new Vec<>();
		v.push(0);
		assertEquals(0, (int)v.get(0));
		try {
			v.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testSet() {
		Vec<Integer> v = new Vec<>(1);
		v.set(0, 3);
		assertEquals(3, (int)v.get(0));
		try {
			v.set(1, 4);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testCopyTo() {
		Vec<Integer> v = new Vec<>();
		v.push(0); v.push(1);
		Vec<Integer> c = new Vec<>();
		v.copyTo(c);
		assertEquals(2, c.size());
		assertEquals(0, (int)c.get(0));
		assertEquals(1, (int)c.get(1));
		assertEquals(2, v.size());
		assertEquals(0, (int)v.get(0));
		assertEquals(1, (int)v.get(1));
	}

	@Test
	public void testMoveTo() {
		Vec<Integer> v = new Vec<>();
		v.push(0); v.push(1);
		Vec<Integer> c = new Vec<>();
		v.moveTo(c);
		assertEquals(2, c.size());
		assertEquals(0, (int)c.get(0));
		assertEquals(1, (int)c.get(1));
		assertEquals(0, v.size());
		assertEquals(0, v.capacity());
	}

	@Test
	public void testCapacityInt() {
		for (int i = 1; i < 100; ++i) {
			Vec<Integer> v = new Vec<Integer>(10);
			v.growTo(i);
//			System.out.printf("i=%d capacity()=%d%n", i, v.capacity());
			assertTrue(v.capacity() >= i);
		}
	}

	@Test
	public void testGrowToIntT() {
		Vec<Integer> v = new Vec<>();
		v.growTo(3, Integer.valueOf(3));
		assertEquals(3, v.size());
		assertEquals(3, (int)v.get(0));
		assertEquals(3, (int)v.get(2));
	}

	@Test
	public void testGrowToInt() {
		Vec<Integer> v = new Vec<>();
		v.growTo(3);
		assertEquals(3, v.size());
		assertEquals(null, v.get(0));
		assertEquals(null, v.get(2));
	}

	@Test
	public void testClearBoolean() {
		Vec<Integer> v = new Vec<>(3);
		int cap = v.capacity();
		v.clear(false);
		assertEquals(0, v.size());
		assertEquals(cap, v.capacity());
		Vec<Integer> w = new Vec<>(3);
		w.clear(true);
		assertEquals(0, w.size());
		assertEquals(0, w.capacity());
	}

	@Test
	public void testClear() {
		Vec<Integer> v = new Vec<>(3);
		int cap = v.capacity();
		v.clear();
		assertEquals(0, v.size());
		assertEquals(cap, v.capacity());
	}

}

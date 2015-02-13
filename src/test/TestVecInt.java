package test;

import static org.junit.Assert.*;
import jp.saka1029.minisatj.mtl.VecInt;

import org.junit.Test;

public class TestVecInt {

	@Test
	public void testImax() {
		assertEquals(20, VecInt.imax(10, 20));
		assertEquals(21, VecInt.imax(11, 21));
	}

	@Test
	public void testVec() {
		VecInt v = new VecInt();
		assertEquals(0, v.capacity());
		assertEquals(0, v.size());
	}

	@Test
	public void testVecInt() {
		VecInt v = new VecInt(10);
		assertEquals(10, v.size());
		assertTrue(v.capacity() >= v.size());
		assertEquals(0, v.get(0));
		assertEquals(0, v.get(9));
	}

	@Test
	public void testVecIntT() {
		VecInt v = new VecInt(10, -3);
		assertEquals(10, v.size());
		assertTrue(v.capacity() >= v.size());
		assertEquals(-3, v.get(0));
		assertEquals(-3, v.get(9));
	}

	@Test
	public void testSize() {
		VecInt v = new VecInt(10);
		assertEquals(10, v.size());
	}

	@Test
	public void testShrink() {
		VecInt v = new VecInt(10);
		v.shrink(5);
		assertEquals(5, v.size());
	}

	@Test
	public void testShrink_() {
		VecInt v = new VecInt(10);
		v.shrink(5);
		assertEquals(5, v.size());
	}

	@Test
	public void testCapacity() {
		for (int i = 1; i < 100; ++i) {
			VecInt v = new VecInt(i);
//			System.out.printf("i=%d capacity()=%d%n", i, v.capacity());
			assertTrue(v.capacity() >= i);
		}
	}

	@Test
	public void testPush() {
		VecInt v = new VecInt();
		v.push();
		assertEquals(1, v.size());
		assertEquals(0, v.get(0));
	}

	@Test
	public void testPushT() {
		VecInt v = new VecInt();
		v.push(3);
		assertEquals(1, v.size());
		assertEquals(3, (int)v.get(0));
	}

	@Test
	public void testPop() {
		VecInt v = new VecInt(3);
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
		VecInt v = new VecInt();
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
		VecInt v = new VecInt();
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
		VecInt v = new VecInt(1);
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
		VecInt v = new VecInt();
		v.push(0); v.push(1);
		VecInt c = new VecInt();
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
		VecInt v = new VecInt();
		v.push(0); v.push(1);
		VecInt c = new VecInt();
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
			VecInt v = new VecInt(10);
			v.growTo(i);
//			System.out.printf("i=%d capacity()=%d%n", i, v.capacity());
			assertTrue(v.capacity() >= i);
		}
	}

	@Test
	public void testGrowToIntT() {
		VecInt v = new VecInt();
		v.growTo(3, Integer.valueOf(3));
		assertEquals(3, v.size());
		assertEquals(3, (int)v.get(0));
		assertEquals(3, (int)v.get(2));
	}

	@Test
	public void testGrowToInt() {
		VecInt v = new VecInt();
		v.growTo(3);
		assertEquals(3, v.size());
		assertEquals(0, v.get(0));
		assertEquals(0, v.get(2));
	}

	@Test
	public void testClearBoolean() {
		VecInt v = new VecInt(3);
		int cap = v.capacity();
		v.clear(false);
		assertEquals(0, v.size());
		assertEquals(cap, v.capacity());
		VecInt w = new VecInt(3);
		w.clear(true);
		assertEquals(0, w.size());
		assertEquals(0, w.capacity());
	}

	@Test
	public void testClear() {
		VecInt v = new VecInt(3);
		int cap = v.capacity();
		v.clear();
		assertEquals(0, v.size());
		assertEquals(cap, v.capacity());
	}

}

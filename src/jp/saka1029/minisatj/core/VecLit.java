package jp.saka1029.minisatj.core;

import jp.saka1029.minisatj.mtl.VecIntObject;

public class VecLit extends VecIntObject<Lit> {

	public VecLit() { super(); }
	public VecLit(int size) { super(size); }
	public VecLit(int size, Lit pad) { super(size, pad); }
	
	@Override public Lit create() { return Lit.UNDEF; }
	@Override public Lit create(int value) { return Lit.valueOf(value); }
	@Override public int value(Lit object) { return object.value(); }
	@Override public int compare(int a, int b) { return a - b; }
	@Override public String toString(int value) { return Lit.valueOf(value).toString(); }

}

package minisatj.core;

import minisatj.mtl.VecIntObject;

public class VecLbool extends VecIntObject<Lbool> {

	public VecLbool() { super(); }
	public VecLbool(int size) { super(size); }
	public VecLbool(int size, Lbool pad) { super(size, pad); }

	@Override public Lbool create() { return Lbool.UNDEF; }
	@Override public Lbool create(int value) { return Lbool.valueOf(value); }
	@Override public int value(Lbool object) { return object.ordinal(); }
	@Override public int compare(int a, int b) { return a - b; }
	@Override public String toString(int value) { return Lbool.valueOf(value).toString(); }
}

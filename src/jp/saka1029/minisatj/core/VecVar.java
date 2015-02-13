package jp.saka1029.minisatj.core;

import jp.saka1029.minisatj.mtl.VecIntObject;

public class VecVar extends VecIntObject<Var> {

	public VecVar() { super(); }
	public VecVar(int size) { super(size); }
	public VecVar(int size, Var pad) { super(size, pad); }

	@Override public Var create() { return Var.UNDEF; } 
	@Override public Var create(int value) { return Var.valueOf(value); }
	@Override public int value(Var object) { return object.value(); }
	@Override public int compare(int a, int b) { return a - b; }
	@Override public String toString(int value) { return "Var" + value; }

}

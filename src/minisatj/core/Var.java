package minisatj.core;

import java.util.ArrayList;
import java.util.List;

////=================================================================================================
//// Variables, literals, lifted booleans, clauses:
//
//
//// NOTE! Variables are just integers. No abstraction here. They should be chosen from 0..N,
//// so that they can be used as array indices.
//
//typedef int Var;
//#define var_Undef (-1)
public class Var {

    private static final int UNDEF_VALUE = -1;
	public static final Var UNDEF = new Var(UNDEF_VALUE);
	private static final List<Var> cache = new ArrayList<>();
	private int value;
	
	private Var(int value) { this.value = value; }
	
	public int value() { return value; }
	
	public static Var valueOf(int value) {
	    if (value == UNDEF_VALUE) return UNDEF;
	    for (int i = cache.size(); i <= value; ++i)
	        cache.add(new Var(i));
	    return cache.get(value);
	}
	
//inline  int  toInt     (Var v)              { return v; } 
//	public static int toInt(Var v) { return v.value; }
	
	@Override
	public String toString() {
		return "Var" + value;
	}

}

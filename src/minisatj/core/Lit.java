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
	// see class Var.
//#define var_Undef (-1)
	// see Var.var_Undef.
//
//
//struct Lit {
public class Lit implements Comparable<Lit> {
    private static final int UNDEF_VALUE = -2;
    private static final int ERROR_VALUE = -1;
	private static final List<Lit> cache = new ArrayList<>();
//    int     x;
	private int x;
//
//    // Use this as a constructor:
//    friend Lit mkLit(Var var, bool sign = false);
//
//    bool operator == (Lit p) const { return x == p.x; }
	// Lit instances can be comparable with "==" or "!="
//	@Override
//	public boolean equals(Object obj) {
//		if (!(obj instanceof Lit))
//			return false;
//		Lit o = (Lit)obj;
//		return x == o.x;
//	}
//	@Override
//	public int hashCode() {
//		return x;
//	}
//    bool operator != (Lit p) const { return x != p.x; }
//    bool operator <  (Lit p) const { return x < p.x;  } // '<' makes p, ~p adjacent in the ordering.
	@Override public int compareTo(Lit o) { return x - o.x; }
//};
//
//
//inline  Lit  mkLit     (Var var, bool sign) { Lit p; p.x = var + var + (int)sign; return p; }
	private Lit(int x) {
		this.x = x;
	}
	static Lit valueOf(int x) {
	    switch (x) {
	        case UNDEF_VALUE: return UNDEF;
	        case ERROR_VALUE: return ERROR;
	    }
//	    if (x < 0) throw new IllegalArgumentException("x");
	    for (int i = cache.size(); i <= x; ++i)
	        cache.add(new Lit(i));
		return cache.get(x);
//	    for (int i = cache.size(); i <= x; ++i)
//	        cache.add(null);
//	    Lit r = cache.get(x);
//	    if (r == null) cache.set(x, r = new Lit(x));
//	    return r;
	}
	public static Lit valueOf(int var, boolean sign) {
	    if (var < 0) throw new IllegalArgumentException("var");
	    return valueOf(var + var + (sign ? 1 : 0));
	}
//inline  Lit  operator ~(Lit p)              { Lit q; q.x = p.x ^ 1; return q; }
	public Lit not() {
		return Lit.valueOf(x ^ 1);
	}
//inline  Lit  operator ^(Lit p, bool b)      { Lit q; q.x = p.x ^ (unsigned int)b; return q; }
	public Lit xor(boolean b) {
		return Lit.valueOf(x ^ (b ? 1 : 0));
	}
//inline  bool sign      (Lit p)              { return p.x & 1; }
	public boolean sign() { return (x & 1) == 1; }
//inline  int  var       (Lit p)              { return p.x >>> 1; }
	public int var() { return x >>> 1; }
//	public static int var(Lit p) { return p.x >> 1; }
//
//// Mapping Literals to and from compact integers suitable for array indexing:
//inline  int  toInt     (Var v)              { return v; } 
	// see class Var
//inline  int  toInt     (Lit p)              { return p.x; } 
	int value() { return x; }
//	public static int toInt(Lit p) { return p.x; }
//inline  Lit  toLit     (int i)              { Lit p; p.x = i; return p; } 
//	public static Lit toLit(int i) { return Lit.valueOf(i); }
//
////const Lit lit_Undef = mkLit(var_Undef, false);  // }- Useful special constants.
////const Lit lit_Error = mkLit(var_Undef, true );  // }
//
//const Lit lit_Undef = { -2 };  // }- Useful special constants.
	public static final Lit UNDEF = new Lit(UNDEF_VALUE);
//const Lit lit_Error = { -1 };  // }
	public static final Lit ERROR = new Lit(ERROR_VALUE);
	
	@Override
	public String toString() {
	    switch (x) {
	        case UNDEF_VALUE: return "UNDEF";
	        case ERROR_VALUE: return "ERROR";
	    }
		return String.format("Lit(%d, %s)",
			x >>> 1, (x & 1) == 1 ? true : false);
	}
}
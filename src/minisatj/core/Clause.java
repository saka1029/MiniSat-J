package minisatj.core;

////=================================================================================================
//// Clause -- a simple class for representing a clause:
//
//class Clause;
//typedef RegionAllocator<uint32_t>::Ref CRef;
//
//class Clause {
public class Clause extends VecLit {
//    struct {
//        unsigned mark      : 2;
	private byte mark;
//        unsigned learnt    : 1;
	private boolean learnt;
//        unsigned has_extra : 1;
	private boolean has_extra;
//        unsigned reloced   : 1;
//        unsigned size      : 27; }                            header;
//    union { Lit lit; float act; uint32_t abs; CRef rel; } data[0];
	private float act;
	private int abs;
//
//    friend class ClauseAllocator;
//
//    // NOTE: This constructor cannot be used directly (doesn't allocate enough memory).
//    template<class V>
//    Clause(const V& ps, bool use_extra, bool learnt) {
//        header.mark      = 0;
//        header.learnt    = learnt;
//        header.has_extra = use_extra;
//        header.reloced   = 0;
//        header.size      = ps.size();
//
//        for (int i = 0; i < ps.size(); i++) 
//            data[i].lit = ps[i];
//
//        if (header.has_extra){
//            if (header.learnt)
//                data[header.size].act = 0; 
//            else 
//                calcAbstraction(); }
//    }
	public Clause(VecLit ps, boolean use_extra, boolean learnt) {
		this.mark = 0;
		this.learnt = learnt;
		this.has_extra = use_extra;
		for (int i = 0; i < ps.size(); ++i)
			push(ps.get(i));
		if (has_extra)
			if (learnt)
				act = 0;
			else
				calcAbstraction();
	}
//
//public:
//    void calcAbstraction() {
//        assert(header.has_extra);
//        uint32_t abstraction = 0;
//        for (int i = 0; i < size(); i++)
//            abstraction |= 1 << (var(data[i].lit) & 31);
//        data[header.size].abs = abstraction;  }
	public void calcAbstraction() {
		if (!has_extra)
			throw new IllegalStateException("has_extra is false");
		int abstraction = 0;
		for (int i = 0; i < size(); ++i)
			abstraction |= 1 << (get(i).var() & 31);
		abs = abstraction;
	}
//
//
//    int          size        ()      const   { return header.size; }
	// defined in super class
//    void         shrink      (int i)         { assert(i <= size()); if (header.has_extra) data[header.size-i] = data[header.size]; header.size -= i; }
//	public void shrink(int i) {
//		if (i <= 0 || i > size())
//			throw new IllegalArgumentException("i");
//		int newSize = size() - i;
//		while (size() > newSize)
//			pop();
//	}
//    void         pop         ()              { shrink(1); }
	// defined in super class
//    bool         learnt      ()      const   { return header.learnt; }
	public boolean learnt() { return learnt; }
//    bool         has_extra   ()      const   { return header.has_extra; }
	public boolean has_extra() { return has_extra; }
//    uint32_t     mark        ()      const   { return header.mark; }
	public int mark() { return mark; }
//    void         mark        (uint32_t m)    { header.mark = m; }
	public void mark(int m) { mark = (byte)m; }
//    const Lit&   last        ()      const   { return data[header.size-1].lit; }
	// defined in super class
//
//    bool         reloced     ()      const   { return header.reloced; }
	public boolean reloced() { return false; }
//    CRef         relocation  ()      const   { return data[0].rel; }
	public Clause relocation() { throw new UnsupportedOperationException(); }
//    void         relocate    (CRef c)        { header.reloced = 1; data[0].rel = c; }
	public void relocate(Clause c) { throw new UnsupportedOperationException(); }
//
//    // NOTE: somewhat unsafe to change the clause in-place! Must manually call 'calcAbstraction' afterwards for
//    //       subsumption operations to behave correctly.
//    Lit&         operator [] (int i)         { return data[i].lit; }
	// use get(int index) in super class
//    Lit          operator [] (int i) const   { return data[i].lit; }
	// use get(int index) in super class
//    operator const Lit* (void) const         { return (Lit*)data; }
	// unsupported
//
//    float&       activity    ()              { assert(header.has_extra); return data[header.size].act; }
	public float activity() {
		if (!has_extra) throw new IllegalStateException("has_extra is false");
		return act;
	}
	public float activity(double value) { return act = (float)value; }
//    uint32_t     abstraction () const        { assert(header.has_extra); return data[header.size].abs; }
	public int abstraction() {
		if (!has_extra) throw new IllegalStateException("has_extra is false");
		return abs;
	}
//
//    Lit          subsumes    (const Clause& other) const;
//    void         strengthen  (Lit p);
//};

///*_________________________________________________________________________________________________
//|
//|  subsumes : (other : const Clause&)  ->  Lit
//|  
//|  Description:
//|       Checks if clause subsumes 'other', and at the same time, if it can be used to simplify 'other'
//|       by subsumption resolution.
//|  
//|    Result:
//|       lit_Error  - No subsumption or simplification
//|       lit_Undef  - Clause subsumes 'other'
//|       p          - The literal p can be deleted from 'other'
//|________________________________________________________________________________________________@*/
//inline Lit Clause::subsumes(const Clause& other) const
//{
//    //if (other.size() < size() || (extra.abst & ~other.extra.abst) != 0)
//    //if (other.size() < size() || (!learnt() && !other.learnt() && (extra.abst & ~other.extra.abst) != 0))
//    assert(!header.learnt);   assert(!other.header.learnt);
//    assert(header.has_extra); assert(other.header.has_extra);
//    if (other.header.size < header.size || (data[header.size].abs & ~other.data[other.header.size].abs) != 0)
//        return lit_Error;
//
//    Lit        ret = lit_Undef;
//    const Lit* c   = (const Lit*)(*this);
//    const Lit* d   = (const Lit*)other;
//
//    for (unsigned i = 0; i < header.size; i++) {
//        // search for c[i] or ~c[i]
//        for (unsigned j = 0; j < other.header.size; j++)
//            if (c[i] == d[j])
//                goto ok;
//            else if (ret == lit_Undef && c[i] == ~d[j]){
//                ret = c[i];
//                goto ok;
//            }
//
//        // did not find it
//        return lit_Error;
//    ok:;
//    }
//
//    return ret;
//}
	public Lit subsumes(Clause other) {
	    if (learnt || other.learnt)
	    	throw new IllegalStateException("learnt is true");
	    if (!has_extra || other.has_extra)
	    	throw new IllegalStateException("has_extra is false");
	    if (other.size() < size() || (abs & ~other.abs) != 0)
	        return Lit.ERROR;
	    Lit ret = Lit.UNDEF;
	    L: for (int i = 0; i < size(); i++) {
	    	Lit cc = this.get(i);
	        // search for c[i] or ~c[i]
	        for (int j = 0; j < other.size(); j++) {
	        	Lit dd = other.get(j);
	            if (cc.equals(dd))
	                continue L;
	            else if (ret.equals(Lit.UNDEF) && cc.equals(dd.not())) {
	                ret = cc;
	                continue L;
	            }
	        }
	        // did not find it
	        return Lit.ERROR;
	    }
	
	    return ret;
	}
//
//inline void Clause::strengthen(Lit p)
//{
//    remove(*this, p);
//    calcAbstraction();
//}
	public void strengthen(Lit p) {
	    remove(p);
	    calcAbstraction();
	}
	
	public static final Clause CRef_Undef = new Clause(new VecLit(), false, false);

}

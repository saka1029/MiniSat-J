package minisatj.core;

////=================================================================================================
//// Lifted booleans:
////
//// NOTE: this implementation is optimized for the case when comparisons between values are mostly
////       between one variable and one constant. Some care had to be taken to make sure that gcc 
////       does enough constant propagation to produce sensible code, and this appears to be somewhat
////       fragile unfortunately.
//
//#define l_True  (lbool((uint8_t)0)) // gcc does not do constant propagation if these are real constants.
//#define l_False (lbool((uint8_t)1))
//#define l_Undef (lbool((uint8_t)2))
//
//class lbool {
public enum Lbool {
    TRUE, FALSE, UNDEF;
//    uint8_t value;
//
//public:
//    explicit lbool(uint8_t v) : value(v) { }
	static Lbool valueOf(int value) {
	    switch (value) {
	        case 0: return TRUE;
	        case 1: return FALSE;
	        case 2: return UNDEF;
	        default:
	            throw new IllegalArgumentException("value");
	    }
	}
//
//    lbool()       : value(0) { }
//    explicit lbool(bool x) : value(!x) { }
	public static Lbool valueOf(boolean x) { return x ? TRUE : FALSE; }
//
//    bool  operator == (lbool b) const { return ((b.value&2) & (value&2)) | (!(b.value&2)&(value == b.value)); }
	// [C++ bool specification]
	// An rvalue of type bool can be converted to an rvalue of type int,
	// with false becoming zero and true becoming one.
//    bool  operator != (lbool b) const { return !(*this == b); }
//    lbool operator ^  (bool  b) const { return lbool((uint8_t)(value^(uint8_t)b)); }
    private static final Lbool[][] XOR = {
        {FALSE, TRUE},
        {TRUE,  FALSE},
        {UNDEF, UNDEF},
    };
    public Lbool xor(boolean o) { return XOR[this.ordinal()][o ? 0 : 1]; }
//
//    lbool operator && (lbool b) const { 
//        uint8_t sel = (this->value << 1) | (b.value << 3);
//        uint8_t v   = (0xF7F755F4 >> sel) & 3;
//        return lbool(v); }
    private static final Lbool[][] AND = {
        {TRUE,  FALSE, UNDEF},
        {FALSE, FALSE, FALSE},
        {UNDEF, FALSE, UNDEF},
    };
    public Lbool and(Lbool o) { return AND[this.ordinal()][o.ordinal()]; }
//
//    lbool operator || (lbool b) const {
//        uint8_t sel = (this->value << 1) | (b.value << 3);
//        uint8_t v   = (0xFCFCF400 >> sel) & 3;
//        return lbool(v); }
    private static final Lbool[][] OR = {
        {TRUE, TRUE,  TRUE},
        {TRUE, FALSE, UNDEF},
        {TRUE, UNDEF, UNDEF},
    };
    public Lbool or(Lbool o) { return OR[this.ordinal()][o.ordinal()]; }
//
//    friend int   toInt  (lbool l);
//    friend lbool toLbool(int   v);
//};
//inline int   toInt  (lbool l) { return l.value; }
//inline lbool toLbool(int   v) { return lbool((uint8_t)v);  }
    
}
package minisatj.core;

//    // Helper structures:
//    //
//    struct VarData { CRef reason; int level; };
class VarData {
	Clause reason;
	int level;
	private VarData(Clause reason, int level) {
		this.reason = reason;
		this.level = level;
	}
//    static inline VarData mkVarData(CRef cr, int l){ VarData d = {cr, l}; return d; }
	public static VarData mkVarData(Clause cr, int l) {
		return new VarData(cr, l);
	}
}

package minisatj.core;

import minisatj.mtl.VecDouble;

//    struct VarOrderLt {
class VarOrderLt {
//        const vec<double>&  activity;
	public final VecDouble activity;
//        bool operator () (Var x, Var y) const { return activity[x] > activity[y]; }
	public boolean call(int x, int y) { return activity.get(x) > activity.get(y); }
//        VarOrderLt(const vec<double>&  act) : activity(act) { }
	public VarOrderLt(VecDouble act) { activity = act; }
//    };
}

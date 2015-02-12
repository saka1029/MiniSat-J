package minisatj.core;

//    struct Watcher {
class Watcher {
//        CRef cref;
	Clause cref;
//        Lit  blocker;
	Lit blocker;
//        Watcher(CRef cr, Lit p) : cref(cr), blocker(p) {}
	public Watcher(Clause cr, Lit p) {
		this.cref = cr;
		this.blocker = p;
	}
//        bool operator==(const Watcher& w) const { return cref == w.cref; }
	@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Watcher))
				return false;
			Watcher o = (Watcher)obj;
			// Clause.operator== is not defined;
			return cref.equals(o.cref);
		}
//        bool operator!=(const Watcher& w) const { return cref != w.cref; }
//    };
	@Override
	public String toString() {
		return String.format("Watcher(%s, %s)", cref, blocker);
	}
}

package minisatj.core;

import minisatj.mtl.Vec;
import minisatj.mtl.VecInt;

//template<class Idx, class Vec, class Deleted>
// actual reference is OccLists<Lit, vec<Watcher>, WacherDeleted>
//class OccLists
public class OccLists {
//{
//    vec<Vec>  occs;
	private Vec<Vec<Watcher>> occs = new Vec<>();
//    vec<char> dirty;
	private VecInt dirty = new VecInt();
//    vec<Idx>  dirties;
	private VecLit dirties = new VecLit();
//    Deleted   deleted;
//
// public:
//    OccLists(const Deleted& d) : deleted(d) {}
	public OccLists() {
	}
//    
//    void  init      (const Idx& idx){ occs.growTo(toInt(idx)+1); dirty.growTo(toInt(idx)+1, 0); }
	public void init(Lit idx) {
		int size = idx.value() + 1;
		for (int i = occs.size(); i < size; ++i)
			occs.push(new Vec<Watcher>());
//		occs.growTo(Lit.toInt(idx) + 1);
		dirty.growTo(size, 0);
	}
//    // Vec&  operator[](const Idx& idx){ return occs[toInt(idx)]; }
//    Vec&  operator[](const Idx& idx){ return occs[toInt(idx)]; }
	public Vec<Watcher> get(Lit idx) { return occs.get(idx.value()); }
//    Vec&  lookup    (const Idx& idx){ if (dirty[toInt(idx)]) clean(idx); return occs[toInt(idx)]; }
	public Vec<Watcher> set(Lit idx, Vec<Watcher> element) { return occs.set(idx.value(), element); }
	public Vec<Watcher> lookup(Lit idx) {
		if (dirty.get(idx.value()) != 0)
			clean(idx);
		return occs.get(idx.value());

	}
//
//    void  cleanAll  ();
//    void  clean     (const Idx& idx);
//    void  smudge    (const Idx& idx){
//        if (dirty[toInt(idx)] == 0){
//            dirty[toInt(idx)] = 1;
//            dirties.push(idx);
//        }
//    }
    public void  smudge(Lit idx){
    	if (dirty.get(idx.value()) == 0) {
    		dirty.set(idx.value(), 1);
    		dirties.push(idx);
    	}
    }
//
//    void  clear(bool free = true){
//        occs   .clear(free);
//        dirty  .clear(free);
//        dirties.clear(free);
//    }
   public void  clear(){
        occs.clear();
        dirty.clear();
        dirties.clear();
    }
//};
//
//
//template<class Idx, class Vec, class Deleted>
//void OccLists<Idx,Vec,Deleted>::cleanAll()
//{
//    for (int i = 0; i < dirties.size(); i++)
//        // Dirties may contain duplicates so check here if a variable is already cleaned:
//        if (dirty[toInt(dirties[i])])
//            clean(dirties[i]);
//    dirties.clear();
//}
	public void cleanAll() {
	    for (int i = 0; i < dirties.size(); i++)
	        // Dirties may contain duplicates so check here if a variable is already cleaned:
	        if (dirty.get(dirties.get(i).value()) != 0)
	            clean(dirties.get(i));
	    dirties.clear();
	}
//
//
//template<class Idx, class Vec, class Deleted>
//void OccLists<Idx,Vec,Deleted>::clean(const Idx& idx)
//{
//    Vec& vec = occs[toInt(idx)];
//    int  i, j;
//    for (i = j = 0; i < vec.size(); i++)
//        if (!deleted(vec[i]))
//            vec[j++] = vec[i];
//    vec.shrink(i - j);
//    dirty[toInt(idx)] = 0;
//}
	/**
	 * from WatcherDeleted::oprator()(const Watcher& w)
	 */
	private boolean deleted(Watcher w) {
		return w.cref.mark() == 1;
	}

	public void clean(Lit idx) {
		Vec<Watcher> vec = occs.get(idx.value());
	    int  i, j;
	    for (i = j = 0; i < vec.size(); i++)
	        if (!deleted(vec.get(i)))
	        	vec.set(j++, vec.get(i));
	    vec.shrink(i - j);
	    dirty.set(idx.value(), 0);
	}
	
	@Override
	public String toString() {
		return String.format("OccLists(occs=%s, dirty=%s, dirties=%s)", occs, dirty, dirties);
	}

}

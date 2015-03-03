package jp.saka1029.minisatj.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;

import jp.saka1029.minisatj.mtl.Vec;
import jp.saka1029.minisatj.mtl.VecBool;
import jp.saka1029.minisatj.mtl.VecDouble;
import jp.saka1029.minisatj.mtl.VecInt;

///****************************************************************************************[Solver.h]
//Copyright (c) 2003-2006, Niklas Een, Niklas Sorensson
//Copyright (c) 2007-2010, Niklas Sorensson
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//associated documentation files (the "Software"), to deal in the Software without restriction,
//including without limitation the rights to use, copy, modify, merge, publish, distribute,
//sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or
//substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
//OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//**************************************************************************************************/
//
//#ifndef Minisat_Solver_h
//#define Minisat_Solver_h
//
//#include "mtl/Vec.h"
//#include "mtl/Heap.h"
//#include "mtl/Alg.h"
//#include "utils/Options.h"
//#include "core/SolverTypes.h"
//
//
//namespace Minisat {
//
////=================================================================================================
//// Solver -- the main class:
//
//class Solver {
public class Solver {
	static void printf(String format, Object... args) {
		System.out.printf(format + "%n", args);
	}

//public:
//
//    // Constructor/Destructor:
//    //
//    Solver();
//    virtual ~Solver();
//
//    // Problem specification:
//    //
//    Var     newVar    (bool polarity = true, bool dvar = true); // Add a new variable with parameters specifying variable mode.
//
//    bool    addClause (const vec<Lit>& ps);                     // Add a clause to the solver. 
//    bool    addEmptyClause();                                   // Add the empty clause, making the solver contradictory.
//    bool    addClause (Lit p);                                  // Add a unit clause to the solver. 
//    bool    addClause (Lit p, Lit q);                           // Add a binary clause to the solver. 
//    bool    addClause (Lit p, Lit q, Lit r);                    // Add a ternary clause to the solver. 
//    bool    addClause_(      vec<Lit>& ps);                     // Add a clause to the solver without making superflous internal copy. Will
//                                                                // change the passed vector 'ps'.
//
//    // Solving:
//    //
//    bool    simplify     ();                        // Removes already satisfied clauses.
//    bool    solve        (const vec<Lit>& assumps); // Search for a model that respects a given set of assumptions.
//    lbool   solveLimited (const vec<Lit>& assumps); // Search for a model that respects a given set of assumptions (With resource constraints).
//    bool    solve        ();                        // Search without assumptions.
//    bool    solve        (Lit p);                   // Search for a model that respects a single assumption.
//    bool    solve        (Lit p, Lit q);            // Search for a model that respects two assumptions.
//    bool    solve        (Lit p, Lit q, Lit r);     // Search for a model that respects three assumptions.
//    bool    okay         () const;                  // FALSE means solver is in a conflicting state
//
//    void    toDimacs     (FILE* f, const vec<Lit>& assumps);            // Write CNF to file in DIMACS-format.
//    void    toDimacs     (const char *file, const vec<Lit>& assumps);
//    void    toDimacs     (FILE* f, Clause& c, vec<Var>& map, Var& max);
//
//    // Convenience versions of 'toDimacs()':
//    void    toDimacs     (const char* file);
//    void    toDimacs     (const char* file, Lit p);
//    void    toDimacs     (const char* file, Lit p, Lit q);
//    void    toDimacs     (const char* file, Lit p, Lit q, Lit r);
//    
//    // Variable mode:
//    // 
//    void    setPolarity    (Var v, bool b); // Declare which polarity the decision heuristic should use for a variable. Requires mode 'polarity_user'.
//    void    setDecisionVar (Var v, bool b); // Declare if a variable should be eligible for selection in the decision heuristic.
//
//    // Read state:
//    //
//    lbool   value      (Var x) const;       // The current value of a variable.
//    lbool   value      (Lit p) const;       // The current value of a literal.
//    lbool   modelValue (Var x) const;       // The value of a variable in the last model. The last call to solve must have been satisfiable.
//    lbool   modelValue (Lit p) const;       // The value of a literal in the last model. The last call to solve must have been satisfiable.
//    int     nAssigns   ()      const;       // The current number of assigned literals.
//    int     nClauses   ()      const;       // The current number of original clauses.
//    int     nLearnts   ()      const;       // The current number of learnt clauses.
//    int     nVars      ()      const;       // The current number of variables.
//    int     nFreeVars  ()      const;
//
//    // Resource contraints:
//    //
//    void    setConfBudget(int64_t x);
//    void    setPropBudget(int64_t x);
//    void    budgetOff();
//    void    interrupt();          // Trigger a (potentially asynchronous) interruption of the solver.
//    void    clearInterrupt();     // Clear interrupt indicator flag.
//
//    // Memory managment:
//    //
//    virtual void garbageCollect();
//    void    checkGarbage(double gf);
//    void    checkGarbage();
//
//    // Extra results: (read-only member variable)
//    //
//    vec<lbool> model;             // If problem is satisfiable, this vector contains the model (if any).
	public VecLbool model = new VecLbool();
//    vec<Lit>   conflict;          // If problem is unsatisfiable (possibly under assumptions),
//                                  // this vector represent the final conflict clause expressed in the assumptions.
	public VecLit conflict = new VecLit();
//
//    // Mode of operation:
//    //
//    int       verbosity;
	public int verbosity;
//    double    var_decay;
	public double var_decay;
//    double    clause_decay;
	public double clause_decay;
//    double    random_var_freq;
	public double random_var_freq;
//    double    random_seed;
	public double random_seed;
//    bool      luby_restart;
	public boolean luby_restart;
//    int       ccmin_mode;         // Controls conflict clause minimization (0=none, 1=basic, 2=deep).
	public int ccmin_mode;         // Controls conflict clause minimization (0=none, 1=basic, 2=deep).
//    int       phase_saving;       // Controls the level of phase saving (0=none, 1=limited, 2=full).
	public int phase_saving;       // Controls the level of phase saving (0=none, 1=limited, 2=full).
//    bool      rnd_pol;            // Use random polarities for branching heuristics.
	public boolean rnd_pol;            // Use random polarities for branching heuristics.
//    bool      rnd_init_act;       // Initialize variable activities with a small random value.
	public boolean rnd_init_act;       // Initialize variable activities with a small random value.
//    double    garbage_frac;       // The fraction of wasted memory allowed before a garbage collection is triggered.
	public double garbage_frac;       // The fraction of wasted memory allowed before a garbage collection is triggered.
//
//    int       restart_first;      // The initial restart limit.                                                                (default 100)
	public int restart_first;      // The initial restart limit.                                                                (default 100)
//    double    restart_inc;        // The factor with which the restart limit is multiplied in each restart.                    (default 1.5)
	public double restart_inc;        // The factor with which the restart limit is multiplied in each restart.                    (default 1.5)
//    double    learntsize_factor;  // The intitial limit for learnt clauses is a factor of the original clauses.                (default 1 / 3)
	public double learntsize_factor;  // The intitial limit for learnt clauses is a factor of the original clauses.                (default 1 / 3)
//    double    learntsize_inc;     // The limit for learnt clauses is multiplied with this factor each restart.                 (default 1.1)
	public double learntsize_inc;     // The limit for learnt clauses is multiplied with this factor each restart.                 (default 1.1)
//
//    int       learntsize_adjust_start_confl;
	public int learntsize_adjust_start_confl;
//    double    learntsize_adjust_inc;
	public double learntsize_adjust_inc;
//
//    // Statistics: (read-only member variable)
//    //
//    uint64_t solves, starts, decisions, rnd_decisions, propagations, conflicts;
	public long solves, starts, decisions, rnd_decisions, propagations, conflicts;
//    uint64_t dec_vars, clauses_literals, learnts_literals, max_literals, tot_literals;
	public long dec_vars, clauses_literals, learnts_literals, max_literals, tot_literals;
//
//protected:
//
//    // Helper structures:
//    //
//    struct VarData { CRef reason; int level; };
	// see VarData class.
//    static inline VarData mkVarData(CRef cr, int l){ VarData d = {cr, l}; return d; }
	// see VarData.mkVarData().
//
//    struct Watcher {
//        CRef cref;
//        Lit  blocker;
//        Watcher(CRef cr, Lit p) : cref(cr), blocker(p) {}
//        bool operator==(const Watcher& w) const { return cref == w.cref; }
//        bool operator!=(const Watcher& w) const { return cref != w.cref; }
//    };
	// see Watcher class
//
//    struct WatcherDeleted
//    {
//        const ClauseAllocator& ca;
//        WatcherDeleted(const ClauseAllocator& _ca) : ca(_ca) {}
//        bool operator()(const Watcher& w) const { return ca[w.cref].mark() == 1; }
//    };
	// see WatcherDeleted class
//
//    struct VarOrderLt {
//        const vec<double>&  activity;
//        bool operator () (Var x, Var y) const { return activity[x] > activity[y]; }
//        VarOrderLt(const vec<double>&  act) : activity(act) { }
//    };
	// see VarOrderLt class
//
//    // Solver state:
//    //
//    bool                ok;               // If FALSE, the constraints are already unsatisfiable. No part of the solver state may be used!
	protected boolean ok;               // If FALSE, the constraints are already unsatisfiable. No part of the solver state may be used!
//    vec<CRef>           clauses;          // List of problem clauses.
	protected Vec<Clause> clauses = new Vec<>();          // List of problem clauses.
	public Vec<Clause> clauses() { return clauses; }
//    vec<CRef>           learnts;          // List of learnt clauses.
	protected Vec<Clause> learnts = new Vec<>();          // List of learnt clauses.
//    double              cla_inc;          // Amount to bump next clause with.
	protected double cla_inc;          // Amount to bump next clause with.
//    vec<double>         activity;         // A heuristic measurement of the activity of a variable.
	protected VecDouble activity = new VecDouble();         // A heuristic measurement of the activity of a variable.
//    double              var_inc;          // Amount to bump next variable with.
	protected double var_inc;
//    OccLists<Lit, vec<Watcher>, WatcherDeleted>
//                        watches;          // 'watches[lit]' is a list of constraints watching 'lit' (will go there if literal becomes true).
	protected OccLists watches;          // 'watches[lit]' is a list of constraints watching 'lit' (will go there if literal becomes true).
//    vec<lbool>          assigns;          // The current assignments.
	protected VecLbool assigns = new VecLbool();          // The current assignments.
//    vec<char>           polarity;         // The preferred polarity of each variable.
	protected VecBool polarity = new VecBool();         // The preferred polarity of each variable.
//    vec<char>           decision;         // Declares if a variable is eligible for selection in the decision heuristic.
	protected VecBool decision = new VecBool();         // Declares if a variable is eligible for selection in the decision heuristic.
//    vec<Lit>            trail;            // Assignment stack; stores all assigments made in the order they were made.
	protected VecLit trail = new VecLit();            // Assignment stack; stores all assigments made in the order they were made.
//    vec<int>            trail_lim;        // Separator indices for different decision levels in 'trail'.
	protected VecInt trail_lim = new VecInt();        // Separator indices for different decision levels in 'trail'.
//    vec<VarData>        vardata;          // Stores reason and level for each variable.
	protected Vec<VarData> vardata = new Vec<>();          // Stores reason and level for each variable.
//    int                 qhead;            // Head of queue (as index into the trail -- no more explicit propagation queue in MiniSat).
	protected int qhead;            // Head of queue (as index into the trail -- no more explicit propagation queue in MiniSat).
//    int                 simpDB_assigns;   // Number of top-level assignments since last execution of 'simplify()'.
	protected int simpDB_assigns;   // Number of top-level assignments since last execution of 'simplify()'.
//    int64_t             simpDB_props;     // Remaining number of propagations that must be made before next execution of 'simplify()'.
	protected long simpDB_props;     // Remaining number of propagations that must be made before next execution of 'simplify()'.
//    vec<Lit>            assumptions;      // Current set of assumptions provided to solve by the user.
	protected VecLit assumptions = new VecLit();      // Current set of assumptions provided to solve by the user.
//    Heap<VarOrderLt>    order_heap;       // A priority queue of variables ordered with respect to the variable activity.
	protected HeapVarOrderLt order_heap;       // A priority queue of variables ordered with respect to the variable activity.
//    double              progress_estimate;// Set by 'search()'.
	protected double progress_estimate;// Set by 'search()'.
//    bool                remove_satisfied; // Indicates whether possibly inefficient linear scan for satisfied clauses should be performed in 'simplify'.
	protected boolean remove_satisfied; // Indicates whether possibly inefficient linear scan for satisfied clauses should be performed in 'simplify'.
//
//    ClauseAllocator     ca;
	// not used.
//
//    // Temporaries (to reduce allocation overhead). Each variable is prefixed by the method in which it is
//    // used, exept 'seen' wich is used in several places.
//    //
//    vec<char>           seen;
	protected VecBool seen = new VecBool();
//    vec<Lit>            analyze_stack;
	protected VecLit analyze_stack = new VecLit();
//    vec<Lit>            analyze_toclear;
	protected VecLit analyze_toclear = new VecLit();
//    vec<Lit>            add_tmp;
	protected VecLit add_tmp = new VecLit();
//
//    double              max_learnts;
	protected double max_learnts;
//    double              learntsize_adjust_confl;
	protected double learntsize_adjust_confl;
//    int                 learntsize_adjust_cnt;
	protected int learntsize_adjust_cnt;
//
//    // Resource contraints:
//    //
//    int64_t             conflict_budget;    // -1 means no budget.
	protected long conflict_budget;    // -1 means no budget.
//    int64_t             propagation_budget; // -1 means no budget.
	protected long propagation_budget; // -1 means no budget.
//    bool                asynch_interrupt;
	protected boolean asynch_interrupt;
//
//    // Main internal methods:
//    //
//    void     insertVarOrder   (Var x);                                                 // Insert a variable in the decision order priority queue.
//    Lit      pickBranchLit    ();                                                      // Return the next decision variable.
//    void     newDecisionLevel ();                                                      // Begins a new decision level.
//    void     uncheckedEnqueue (Lit p, CRef from = CRef_Undef);                         // Enqueue a literal. Assumes value of literal is undefined.
//    bool     enqueue          (Lit p, CRef from = CRef_Undef);                         // Test if fact 'p' contradicts current state, enqueue otherwise.
//    CRef     propagate        ();                                                      // Perform unit propagation. Returns possibly conflicting clause.
//    void     cancelUntil      (int level);                                             // Backtrack until a certain level.
//    void     analyze          (CRef confl, vec<Lit>& out_learnt, int& out_btlevel);    // (bt = backtrack)
//    void     analyzeFinal     (Lit p, vec<Lit>& out_conflict);                         // COULD THIS BE IMPLEMENTED BY THE ORDINARIY "analyze" BY SOME REASONABLE GENERALIZATION?
//    bool     litRedundant     (Lit p, uint32_t abstract_levels);                       // (helper method for 'analyze()')
//    lbool    search           (int nof_conflicts);                                     // Search for a given number of conflicts.
//    lbool    solve_           ();                                                      // Main solve method (assumptions given in 'assumptions').
//    void     reduceDB         ();                                                      // Reduce the set of learnt clauses.
//    void     removeSatisfied  (vec<CRef>& cs);                                         // Shrink 'cs' to contain only non-satisfied clauses.
//    void     rebuildOrderHeap ();
//
//    // Maintaining Variable/Clause activity:
//    //
//    void     varDecayActivity ();                      // Decay all variables with the specified factor. Implemented by increasing the 'bump' value instead.
//    void     varBumpActivity  (Var v, double inc);     // Increase a variable with the current 'bump' value.
//    void     varBumpActivity  (Var v);                 // Increase a variable with the current 'bump' value.
//    void     claDecayActivity ();                      // Decay all clauses with the specified factor. Implemented by increasing the 'bump' value instead.
//    void     claBumpActivity  (Clause& c);             // Increase a clause with the current 'bump' value.
//
//    // Operations on clauses:
//    //
//    void     attachClause     (CRef cr);               // Attach a clause to watcher lists.
//    void     detachClause     (CRef cr, bool strict = false); // Detach a clause to watcher lists.
//    void     removeClause     (CRef cr);               // Detach and free a clause.
//    bool     locked           (const Clause& c) const; // Returns TRUE if a clause is a reason for some implication in the current state.
//    bool     satisfied        (const Clause& c) const; // Returns TRUE if a clause is satisfied in the current state.
//
//    void     relocAll         (ClauseAllocator& to);
//
//    // Misc:
//    //
//    int      decisionLevel    ()      const; // Gives the current decisionlevel.
//    uint32_t abstractLevel    (Var x) const; // Used to represent an abstraction of sets of decision levels.
//    CRef     reason           (Var x) const;
//    int      level            (Var x) const;
//    double   progressEstimate ()      const; // DELETE THIS ?? IT'S NOT VERY USEFUL ...
//    bool     withinBudget     ()      const;
//
//    // Static helpers:
//    //
//
//    // Returns a random float 0 <= x < 1. Seed must never be 0.
//    static inline double drand(double& seed) {
//        seed *= 1389796;
//        int q = (int)(seed / 2147483647);
//        seed -= (double)q * 2147483647;
//        return seed / 2147483647; }
    protected double drand() {
        random_seed *= 1389796;
        int q = (int)(random_seed / 2147483647);
        random_seed -= (double)q * 2147483647;
        return random_seed / 2147483647;
    }
//
//    // Returns a random integer 0 <= x < size. Seed must never be 0.
//    static inline int irand(double& seed, int size) {
//        return (int)(drand(seed) * size); }
    protected int irand(int size) {
        return (int)(drand() * size);
    }
//};
//
//
////=================================================================================================
//// Implementation of inline methods:
//
//inline CRef Solver::reason(Var x) const { return vardata[x].reason; }
    Clause reason(Var x) { return vardata.get(x.value()).reason; }
//inline int  Solver::level (Var x) const { return vardata[x].level; }
    int level(int x) { return vardata.get(x).level; }
//
//inline void Solver::insertVarOrder(Var x) {
//    if (!order_heap.inHeap(x) && decision[x]) order_heap.insert(x); }
    void insertVarOrder(Var x) {
	    if (!order_heap.inHeap(x.value()) && decision.get(x.value())) order_heap.insert(x.value());
	}
//
//inline void Solver::varDecayActivity() { var_inc *= (1 / var_decay); }
    void varDecayActivity() { var_inc *= (1 / var_decay); }
//inline void Solver::varBumpActivity(Var v) { varBumpActivity(v, var_inc); }
    void varBumpActivity(Var v) { varBumpActivity(v, var_inc); }
//inline void Solver::varBumpActivity(Var v, double inc) {
//    if ( (activity[v] += inc) > 1e100 ) {
//        // Rescale:
//        for (int i = 0; i < nVars(); i++)
//            activity[i] *= 1e-100;
//        var_inc *= 1e-100; }
//
//    // Update order_heap with respect to new activity:
//    if (order_heap.inHeap(v))
//        order_heap.decrease(v); }
    void varBumpActivity(Var v, double inc) {
    	int i = v.value();
    	if (activity.set(i, activity.get(i) + inc) > 1e100) {
	        // Rescale:
	        for (int j = 0; j < nVars(); j++)
	        	activity.set(j, activity.get(j) * 1e-100);
	        var_inc *= 1e-100;
	    }
	    // Update order_heap with respect to new activity:
	    if (order_heap.inHeap(i))
	        order_heap.decrease(i);
	}
//
//inline void Solver::claDecayActivity() { cla_inc *= (1 / clause_decay); }
    void claDecayActivity() { cla_inc *= (1 / clause_decay); }
//inline void Solver::claBumpActivity (Clause& c) {
//        if ( (c.activity() += cla_inc) > 1e20 ) {
//            // Rescale:
//            for (int i = 0; i < learnts.size(); i++)
//                ca[learnts[i]].activity() *= 1e-20;
//            cla_inc *= 1e-20; } }
	void claBumpActivity (Clause c) {
		if (c.activity((c.activity() + cla_inc)) > 1e20) {
            // Rescale:
            for (int i = 0; i < learnts.size(); i++) {
            	Clause cl = learnts.get(i);
            	cl.activity(cl.activity() * 1e-20);
            }
            cla_inc *= 1e-20;
        }
	}
//
//inline void Solver::checkGarbage(void){ return checkGarbage(garbage_frac); }
	void checkGarbage(){
		// do nothing
	}
//inline void Solver::checkGarbage(double gf){
//    if (ca.wasted() > ca.size() * gf)
//        garbageCollect(); }
//
//// NOTE: enqueue does not set the ok flag! (only public methods do)
//inline bool     Solver::enqueue         (Lit p, CRef from)      { return value(p) != l_Undef ? value(p) != l_False : (uncheckedEnqueue(p, from), true); }
	boolean enqueue(Lit p, Clause from) {
		if (value(p) != Lbool.UNDEF)
			return value(p) != Lbool.FALSE;
		else {
			uncheckedEnqueue(p, from);
			return true;
		}
	}
//inline bool     Solver::addClause       (const vec<Lit>& ps)    { ps.copyTo(add_tmp); return addClause_(add_tmp); }
//	public boolean addClause(VecLit ps)    { ps.copyTo(add_tmp); return addClause_(add_tmp); }
	public boolean addClause(VecLit ps)    { return addClause_(ps); }
//inline bool     Solver::addEmptyClause  ()                      { add_tmp.clear(); return addClause_(add_tmp); }
	boolean addEmptyClause() { add_tmp.clear(); return addClause_(add_tmp); }
//inline bool     Solver::addClause       (Lit p)                 { add_tmp.clear(); add_tmp.push(p); return addClause_(add_tmp); }
	boolean addClause(Lit p) { add_tmp.clear(); add_tmp.push(p); return addClause_(add_tmp); }
//inline bool     Solver::addClause       (Lit p, Lit q)          { add_tmp.clear(); add_tmp.push(p); add_tmp.push(q); return addClause_(add_tmp); }
	boolean addClause(Lit p, Lit q) { add_tmp.clear(); add_tmp.push(p); add_tmp.push(q); return addClause_(add_tmp); }
//inline bool     Solver::addClause       (Lit p, Lit q, Lit r)   { add_tmp.clear(); add_tmp.push(p); add_tmp.push(q); add_tmp.push(r); return addClause_(add_tmp); }
	boolean addClause(Lit p, Lit q, Lit r)   { add_tmp.clear(); add_tmp.push(p); add_tmp.push(q); add_tmp.push(r); return addClause_(add_tmp); }
//inline bool     Solver::locked          (const Clause& c) const { return value(c[0]) == l_True && reason(var(c[0])) != CRef_Undef && ca.lea(reason(var(c[0]))) == &c; }
	public boolean addClause(int... vars) {
		add_tmp.clear();
		for (int var : vars) {
			if (var == 0)
				throw new IllegalArgumentException("all vars must not be zero");
			add_tmp.push(Lit.valueOf(Math.abs(var) - 1, var < 0));
		}
		return addClause_(add_tmp);
	}

	boolean locked(Clause c) {
//        return value(c[0]) == l_True && reason(var(c[0])) != CRef_Undef && ca.lea(reason(var(c[0]))) == &c;
	    Clause rc = reason(Var.valueOf(c.get(0).var()));
        return value(c.get(0)) == Lbool.TRUE && rc != Clause.CRef_Undef && rc.equals(c);
	}
//inline void     Solver::newDecisionLevel()                      { trail_lim.push(trail.size()); }
	void newDecisionLevel() { trail_lim.push(trail.size()); }
//
//inline int      Solver::decisionLevel ()      const   { return trail_lim.size(); }
	int decisionLevel() { return trail_lim.size(); }
//inline uint32_t Solver::abstractLevel (Var x) const   { return 1 << (level(x) & 31); }
	int abstractLevel(int x)    { return 1 << (level(x) & 31); }
//inline lbool    Solver::value         (Var x) const   { return assigns[x]; }
	Lbool value(Var x) { return assigns.get(x.value()); }
//inline lbool    Solver::value         (Lit p) const   { return assigns[var(p)] ^ sign(p); }
	Lbool value(Lit p) { return assigns.get(p.var()).xor(p.sign()); }
//	Lbool value(Lit p) { int x = p.value(); return assigns.get(x >>> 1).xor((x & 1) == 1); }
//inline lbool    Solver::modelValue    (Var x) const   { return model[x]; }
	Lbool modelValue(Var x) { return model.get(x.value()); }
//inline lbool    Solver::modelValue    (Lit p) const   { return model[var(p)] ^ sign(p); }
	Lbool modelValue(Lit p) { return model.get(p.var()).xor(p.sign()); }
//inline int      Solver::nAssigns      ()      const   { return trail.size(); }
	int nAssigns() { return trail.size(); }
//inline int      Solver::nClauses      ()      const   { return clauses.size(); }
	public int nClauses() { return clauses.size(); }
//inline int      Solver::nLearnts      ()      const   { return learnts.size(); }
	int nLearnts() { return learnts.size(); }
//inline int      Solver::nVars         ()      const   { return vardata.size(); }
	public int nVars() { return vardata.size(); }
//inline int      Solver::nFreeVars     ()      const   { return (int)dec_vars - (trail_lim.size() == 0 ? trail.size() : trail_lim[0]); }
	int nFreeVars() { return (int)dec_vars - (trail_lim.size() == 0 ? trail.size() : trail_lim.get(0)); }
//inline void     Solver::setPolarity   (Var v, bool b) { polarity[v] = b; }
	void setPolarity(Var v, boolean b) { polarity.set(v.value(), b); }
//inline void     Solver::setDecisionVar(Var v, bool b) 
//{ 
//    if      ( b && !decision[v]) dec_vars++;
//    else if (!b &&  decision[v]) dec_vars--;
//
//    decision[v] = b;
//    insertVarOrder(v);
//}
	void setDecisionVar(Var v, boolean b) { 
		int i = v.value();
	    if (b && !decision.get(i)) dec_vars++;
	    else if (!b && decision.get(i)) dec_vars--;
	    decision.set(i, b);
	    insertVarOrder(v);
	}
//inline void     Solver::setConfBudget(int64_t x){ conflict_budget    = conflicts    + x; }
	void setConfBudget(long x){ conflict_budget = conflicts + x; }
//inline void     Solver::setPropBudget(int64_t x){ propagation_budget = propagations + x; }
	void setPropBudget(long x){ propagation_budget = propagations + x; }
//inline void     Solver::interrupt(){ asynch_interrupt = true; }
	void interrupt() { asynch_interrupt = true; }
//inline void     Solver::clearInterrupt(){ asynch_interrupt = false; }
	void clearInterrupt(){ asynch_interrupt = false; }
//inline void     Solver::budgetOff(){ conflict_budget = propagation_budget = -1; }
	void budgetOff() { conflict_budget = propagation_budget = -1; }
//inline bool     Solver::withinBudget() const {
//    return !asynch_interrupt &&
//           (conflict_budget    < 0 || conflicts < (uint64_t)conflict_budget) &&
//           (propagation_budget < 0 || propagations < (uint64_t)propagation_budget); }
	boolean withinBudget() {
	    return !asynch_interrupt &&
	           (conflict_budget    < 0 || conflicts < conflict_budget) &&
	           (propagation_budget < 0 || propagations < propagation_budget);
	}
//
//// FIXME: after the introduction of asynchronous interrruptions the solve-versions that return a
//// pure bool do not give a safe interface. Either interrupts must be possible to turn off here, or
//// all calls to solve must return an 'lbool'. I'm not yet sure which I prefer.
//inline bool     Solver::solve         ()                    { budgetOff(); assumptions.clear(); return solve_() == l_True; }
	public boolean solve() { budgetOff(); assumptions.clear(); return solve_() == Lbool.TRUE; }
//inline bool     Solver::solve         (Lit p)               { budgetOff(); assumptions.clear(); assumptions.push(p); return solve_() == l_True; }
	boolean solve(Lit p) { budgetOff(); assumptions.clear(); assumptions.push(p); return solve_() == Lbool.TRUE; }
//inline bool     Solver::solve         (Lit p, Lit q)        { budgetOff(); assumptions.clear(); assumptions.push(p); assumptions.push(q); return solve_() == l_True; }
	boolean solve(Lit p, Lit q) { budgetOff(); assumptions.clear(); assumptions.push(p); assumptions.push(q); return solve_() == Lbool.TRUE; }
//inline bool     Solver::solve         (Lit p, Lit q, Lit r) { budgetOff(); assumptions.clear(); assumptions.push(p); assumptions.push(q); assumptions.push(r); return solve_() == l_True; }
	boolean solve(Lit p, Lit q, Lit r) { budgetOff(); assumptions.clear(); assumptions.push(p); assumptions.push(q); assumptions.push(r); return solve_() == Lbool.TRUE; }
//inline bool     Solver::solve         (const vec<Lit>& assumps){ budgetOff(); assumps.copyTo(assumptions); return solve_() == l_True; }
	boolean solve(VecLit assumps) { budgetOff(); assumps.copyTo(assumptions); return solve_() == Lbool.TRUE; }
//inline lbool    Solver::solveLimited  (const vec<Lit>& assumps){ assumps.copyTo(assumptions); return solve_(); }
	Lbool solveLimited(VecLit assumps){ assumps.copyTo(assumptions); return solve_(); }
//inline bool     Solver::okay          ()      const   { return ok; }
	boolean okay() { return ok; }
//
//inline void     Solver::toDimacs     (const char* file){ vec<Lit> as; toDimacs(file, as); }
	public void toDimacs(File file) throws IOException{ VecLit as = new VecLit(); toDimacs(file, as); }
//inline void     Solver::toDimacs     (const char* file, Lit p){ vec<Lit> as; as.push(p); toDimacs(file, as); }
	void toDimacs(File file, Lit p) throws IOException{ VecLit as = new VecLit(); as.push(p); toDimacs(file, as); }
//inline void     Solver::toDimacs     (const char* file, Lit p, Lit q){ vec<Lit> as; as.push(p); as.push(q); toDimacs(file, as); }
	void toDimacs(File file, Lit p, Lit q) throws IOException{ VecLit as = new VecLit(); as.push(p); as.push(q); toDimacs(file, as); }
//inline void     Solver::toDimacs     (const char* file, Lit p, Lit q, Lit r){ vec<Lit> as; as.push(p); as.push(q); as.push(r); toDimacs(file, as); }
	void toDimacs(File file, Lit p, Lit q, Lit r) throws IOException { VecLit as = new VecLit(); as.push(p); as.push(q); as.push(r); toDimacs(file, as); }
//
//
////=================================================================================================
//// Debug etc:
//
//
////=================================================================================================
//}
//
//#endif

///***************************************************************************************[Solver.cc]
//Copyright (c) 2003-2006, Niklas Een, Niklas Sorensson
//Copyright (c) 2007-2010, Niklas Sorensson
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//associated documentation files (the "Software"), to deal in the Software without restriction,
//including without limitation the rights to use, copy, modify, merge, publish, distribute,
//sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or
//substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
//OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//**************************************************************************************************/
//
//#include <math.h>
//
//#include "mtl/Sort.h"
//#include "core/Solver.h"
//
//using namespace Minisat;
//
//
////=================================================================================================
//// Default options value:
	public static final int VERBOSITY_DEFAULT = 0;
    public static final double VAR_DECAY_DEFAULT = 0.95;
    public static final double CLAUSE_DECAY_DEFAULT = 0.999;
    public static final double RANDOM_VAR_FREQ_DEFAULT = 0;
    public static final double RANDOM_SEED_DEFAULT = 91648253;
    public static final int CCMIN_MODE_DEFAULT = 2;
    public static final int PHASE_SAVING_DEFAULT = 2;
    public static final boolean RND_INIT_ACT_DEFAULT = false;
    public static final boolean LUBY_RESTART_DEFAULT = true;
    public static final int RESTART_FIRST_DEFAULT = 100;
    public static final double RESTART_INC_DEFAULT = 2;
    public static final double GARBAGE_FRAC_DEFAULT = 0.20;
////=================================================================================================
//// Constructor/Destructor:
//
//
//Solver::Solver() :
public Solver() {
//
//    // Parameters (user settable):
//    //
//    verbosity        (0)
	verbosity = VERBOSITY_DEFAULT;
//  , var_decay        (opt_var_decay)
	var_decay = VAR_DECAY_DEFAULT;
//  , clause_decay     (opt_clause_decay)
	clause_decay = CLAUSE_DECAY_DEFAULT;
//  , random_var_freq  (opt_random_var_freq)
	random_var_freq = RANDOM_VAR_FREQ_DEFAULT;
//  , random_seed      (opt_random_seed)
	random_seed = RANDOM_SEED_DEFAULT;
//  , luby_restart     (opt_luby_restart)
	luby_restart = LUBY_RESTART_DEFAULT;
//  , ccmin_mode       (opt_ccmin_mode)
	ccmin_mode = CCMIN_MODE_DEFAULT;
//  , phase_saving     (opt_phase_saving)
	phase_saving = PHASE_SAVING_DEFAULT;
//  , rnd_pol          (false)
	rnd_pol = false;
//  , rnd_init_act     (opt_rnd_init_act)
	rnd_init_act = RND_INIT_ACT_DEFAULT;
//  , garbage_frac     (opt_garbage_frac)
	garbage_frac = GARBAGE_FRAC_DEFAULT;
//  , restart_first    (opt_restart_first)
	restart_first = RESTART_FIRST_DEFAULT;
//  , restart_inc      (opt_restart_inc)
	restart_inc = RESTART_INC_DEFAULT;
//
//    // Parameters (the rest):
//    //
//  , learntsize_factor((double)1/(double)3), learntsize_inc(1.1)
	learntsize_factor = (double)1/(double)3;
	learntsize_inc = 1.1;
//
//    // Parameters (experimental):
//    //
//  , learntsize_adjust_start_confl (100)
	learntsize_adjust_start_confl = 100;
//  , learntsize_adjust_inc         (1.5)
	learntsize_adjust_inc = 1.5;
//
//    // Statistics: (formerly in 'SolverStats')
//    //
//  , solves(0), starts(0), decisions(0), rnd_decisions(0), propagations(0), conflicts(0)
//  , dec_vars(0), clauses_literals(0), learnts_literals(0), max_literals(0), tot_literals(0)
//
//  , ok                 (true)
	ok = true;
//  , cla_inc            (1)
	cla_inc = 1;
//  , var_inc            (1)
	var_inc = 1;
//  , watches            (WatcherDeleted(ca))
	watches = new OccLists();
//  , qhead              (0)
	qhead = 0;
//  , simpDB_assigns     (-1)
	simpDB_assigns = -1;
//  , simpDB_props       (0)
	simpDB_props = 0;
//  , order_heap         (VarOrderLt(activity))
	order_heap = new HeapVarOrderLt(new VarOrderLt(activity));
//  , progress_estimate  (0)
	progress_estimate = 0;
//  , remove_satisfied   (true)
	remove_satisfied = true;
//
//    // Resource constraints:
//    //
//  , conflict_budget    (-1)
	conflict_budget = -1;
//  , propagation_budget (-1)
	propagation_budget = -1;
//  , asynch_interrupt   (false)
	asynch_interrupt = false;
//{}
}
//
//
//Solver::~Solver()
//{
//}
//
//
////=================================================================================================
//// Minor methods:
//
//
//// Creates a new SAT variable in the solver. If 'decision' is cleared, variable will not be
//// used as a decision variable (NOTE! This has effects on the meaning of a SATISFIABLE result).
////
//Var Solver::newVar(bool sign, bool dvar)
//{
//    int v = nVars();
//    watches  .init(mkLit(v, false));
//    watches  .init(mkLit(v, true ));
//    assigns  .push(l_Undef);
//    vardata  .push(mkVarData(CRef_Undef, 0));
//    //activity .push(0);
//    activity .push(rnd_init_act ? drand(random_seed) * 0.00001 : 0);
//    seen     .push(0);
//    polarity .push(sign);
//    decision .push();
//    trail    .capacity(v+1);
//    setDecisionVar(v, dvar);
//    return v;
//}
public Var newVar(boolean sign, boolean dvar) {
    int v = nVars();
    Var vv = Var.valueOf(v);
    watches  .init(Lit.valueOf(v, false));
    watches  .init(Lit.valueOf(v, true ));
    assigns  .push(Lbool.UNDEF);
    vardata  .push(VarData.mkVarData(Clause.CRef_Undef, 0));
    //activity .push(0);
    activity .push(rnd_init_act ? drand() * 0.00001 : 0.0);
    seen     .push(false);
    polarity .push(sign);
    decision .push();
    trail    .capacity(v+1);
    setDecisionVar(vv, dvar);
    return vv;
}
public Var newVar(boolean sign) { return newVar(sign, true); }
public Var newVar() { return newVar(true, true); }
//
//
//bool Solver::addClause_(vec<Lit>& ps)
//{
//    assert(decisionLevel() == 0);
//    if (!ok) return false;
//
//    // Check if clause is satisfied and remove false/duplicate literals:
//    sort(ps);
//    Lit p; int i, j;
//    for (i = j = 0, p = lit_Undef; i < ps.size(); i++)
//        if (value(ps[i]) == l_True || ps[i] == ~p)
//            return true;
//        else if (value(ps[i]) != l_False && ps[i] != p)
//            ps[j++] = p = ps[i];
//    ps.shrink(i - j);
//
//    if (ps.size() == 0)
//        return ok = false;
//    else if (ps.size() == 1){
//        uncheckedEnqueue(ps[0]);
//        return ok = (propagate() == CRef_Undef);
//    }else{
//        CRef cr = ca.alloc(ps, false);
//        clauses.push(cr);
//        attachClause(cr);
//    }
//
//    return true;
//}
boolean addClause_(VecLit ps) {
    if (decisionLevel() != 0)
        throw new IllegalStateException("decisionLevel() = " + decisionLevel());
    if (!ok) return false;

    // Check if clause is satisfied and remove false/duplicate literals:
    ps.sort();
    Lit p; int i, j;
    for (i = j = 0, p = Lit.UNDEF; i < ps.size(); i++) {
        int v = ps.get(i).var();
        while (v >= nVars()) newVar();
        if (value(ps.get(i)) == Lbool.TRUE || ps.get(i) == p.not())
            return true;
        else if (value(ps.get(i)) != Lbool.FALSE && ps.get(i) != p)
        	ps.set(j++, p = ps.get(i));
    }
    ps.shrink(i - j);

    if (ps.size() == 0)
        return ok = false;
    else if (ps.size() == 1){
        uncheckedEnqueue(ps.get(0));
        return ok = (propagate() == Clause.CRef_Undef);
    }else{
    	Clause cr = new Clause(ps, false, false);
        clauses.push(cr);
        attachClause(cr);
    }

    return true;
}
//
//
//void Solver::attachClause(CRef cr) {
//    const Clause& c = ca[cr];
//    assert(c.size() > 1);
//    watches[~c[0]].push(Watcher(cr, c[1]));
//    watches[~c[1]].push(Watcher(cr, c[0]));
//    if (c.learnt()) learnts_literals += c.size();
//    else            clauses_literals += c.size(); }
void attachClause(Clause cr) {
	if (cr.size() <= 1)
		throw new IllegalArgumentException("cr");
	watches.get(cr.get(0).not()).push(new Watcher(cr, cr.get(1)));
	watches.get(cr.get(1).not()).push(new Watcher(cr, cr.get(0)));
    if (cr.learnt()) learnts_literals += cr.size();
    else            clauses_literals += cr.size();
}
//
//
//void Solver::detachClause(CRef cr, bool strict) {
//    const Clause& c = ca[cr];
//    assert(c.size() > 1);
//    
//    if (strict){
//        remove(watches[~c[0]], Watcher(cr, c[1]));
//        remove(watches[~c[1]], Watcher(cr, c[0]));
//    }else{
//        // Lazy detaching: (NOTE! Must clean all watcher lists before garbage collecting this clause)
//        watches.smudge(~c[0]);
//        watches.smudge(~c[1]);
//    }
//
//    if (c.learnt()) learnts_literals -= c.size();
//    else            clauses_literals -= c.size(); }
void detachClause(Clause cr, boolean strict) {
	if (cr.size() <= 1)
		throw new IllegalArgumentException("cr");
    if (strict){
    	watches.get(cr.get(0).not()).remove(new Watcher(cr, cr.get(1)));
    	watches.get(cr.get(1).not()).remove(new Watcher(cr, cr.get(0)));
    }else{
        // Lazy detaching: (NOTE! Must clean all watcher lists before garbage collecting this clause)
        watches.smudge(cr.get(0).not());
        watches.smudge(cr.get(1).not());
    }

    if (cr.learnt()) learnts_literals -= cr.size();
    else            clauses_literals -= cr.size();
}
void detachClause(Clause cr) {
	detachClause(cr, false);
}
//
//
//void Solver::removeClause(CRef cr) {
//    Clause& c = ca[cr];
//    detachClause(cr);
//    // Don't leave pointers to free'd memory!
//    if (locked(c)) vardata[var(c[0])].reason = CRef_Undef;
//    c.mark(1); 
//    ca.free(cr);
//}
void removeClause(Clause cr) {
    detachClause(cr);
    // Don't leave pointers to free'd memory!
    if (locked(cr)) vardata.get(cr.get(0).var()).reason = Clause.CRef_Undef;
    cr.mark(1); 
//    ca.free(cr);
}
//
//
//bool Solver::satisfied(const Clause& c) const {
//    for (int i = 0; i < c.size(); i++)
//        if (value(c[i]) == l_True)
//            return true;
//    return false; }
boolean satisfied(Clause c) {
    for (int i = 0; i < c.size(); i++)
        if (value(c.get(i)) == Lbool.TRUE)
            return true;
    return false;
}
//
//
//// Revert to the state at given level (keeping all assignment at 'level' but not beyond).
////
//void Solver::cancelUntil(int level) {
//    if (decisionLevel() > level){
//        for (int c = trail.size()-1; c >= trail_lim[level]; c--){
//            Var      x  = var(trail[c]);
//            assigns [x] = l_Undef;
//            if (phase_saving > 1 || (phase_saving == 1) && c > trail_lim.last())
//                polarity[x] = sign(trail[c]);
//            insertVarOrder(x); }
//        qhead = trail_lim[level];
//        trail.shrink(trail.size() - trail_lim[level]);
//        trail_lim.shrink(trail_lim.size() - level);
//    } }
void cancelUntil(int level) {
    if (decisionLevel() > level){
        for (int c = trail.size()-1; c >= trail_lim.get(level); c--){
            int x  = trail.get(c).var();
            assigns.set(x, Lbool.UNDEF);
            if (phase_saving > 1 || (phase_saving == 1) && c > trail_lim.last())
                polarity.set(x, trail.get(c).sign());
            insertVarOrder(Var.valueOf(x)); }
        qhead = trail_lim.get(level);
        trail.shrink(trail.size() - trail_lim.get(level));
        trail_lim.shrink(trail_lim.size() - level);
    }
}
//
//
////=================================================================================================
//// Major methods:
//
//
//Lit Solver::pickBranchLit()
//{
//    Var next = var_Undef;
//
//    // Random decision:
//    if (drand(random_seed) < random_var_freq && !order_heap.empty()){
//        next = order_heap[irand(random_seed,order_heap.size())];
//        if (value(next) == l_Undef && decision[next])
//            rnd_decisions++; }
//
//    // Activity based decision:
//    while (next == var_Undef || value(next) != l_Undef || !decision[next])
//        if (order_heap.empty()){
//            next = var_Undef;
//            break;
//        }else
//            next = order_heap.removeMin();
//
//    return next == var_Undef ? lit_Undef : mkLit(next, rnd_pol ? drand(random_seed) < 0.5 : polarity[next]);
//}
Lit pickBranchLit() {
    Var next = Var.UNDEF;

    // Random decision:
    if (drand() < random_var_freq && !order_heap.empty()){
        next = Var.valueOf(order_heap.get(irand(order_heap.size())));
        if (value(next) == Lbool.UNDEF && decision.get(next.value()))
            rnd_decisions++; }
    // Activity based decision:
    while (next == Var.UNDEF || value(next) != Lbool.UNDEF || !decision.get(next.value()))
        if (order_heap.empty()){
            next = Var.UNDEF;
            break;
        }else
            next = Var.valueOf(order_heap.removeMin());

    return next == Var.UNDEF ?
        Lit.UNDEF :
        Lit.valueOf(next.value(), rnd_pol ?
            drand() < 0.5 :
            polarity.get(next.value()));
}
//
//
///*_________________________________________________________________________________________________
//|
//|  analyze : (confl : Clause*) (out_learnt : vec<Lit>&) (out_btlevel : int&)  ->  [void]
//|  
//|  Description:
//|    Analyze conflict and produce a reason clause.
//|  
//|    Pre-conditions:
//|      * 'out_learnt' is assumed to be cleared.
//|      * Current decision level must be greater than root level.
//|  
//|    Post-conditions:
//|      * 'out_learnt[0]' is the asserting literal at level 'out_btlevel'.
//|      * If out_learnt.size() > 1 then 'out_learnt[1]' has the greatest decision level of the 
//|        rest of literals. There may be others from the same level though.
//|  
//|________________________________________________________________________________________________@*/
//void Solver::analyze(CRef confl, vec<Lit>& out_learnt, int& out_btlevel)
//{
//    int pathC = 0;
//    Lit p     = lit_Undef;
//
//    // Generate conflict clause:
//    //
//    out_learnt.push();      // (leave room for the asserting literal)
//    int index   = trail.size() - 1;
//
//    do{
//        assert(confl != CRef_Undef); // (otherwise should be UIP)
//        Clause& c = ca[confl];
//
//        if (c.learnt())
//            claBumpActivity(c);
//
//        for (int j = (p == lit_Undef) ? 0 : 1; j < c.size(); j++){
//            Lit q = c[j];
//
//            if (!seen[var(q)] && level(var(q)) > 0){
//                varBumpActivity(var(q));
//                seen[var(q)] = 1;
//                if (level(var(q)) >= decisionLevel())
//                    pathC++;
//                else
//                    out_learnt.push(q);
//            }
//        }
//        
//        // Select next clause to look at:
//        while (!seen[var(trail[index--])]);
//        p     = trail[index+1];
//        confl = reason(var(p));
//        seen[var(p)] = 0;
//        pathC--;
//
//    }while (pathC > 0);
//    out_learnt[0] = ~p;
//
//    // Simplify conflict clause:
//    //
//    int i, j;
//    out_learnt.copyTo(analyze_toclear);
//    if (ccmin_mode == 2){
//        uint32_t abstract_level = 0;
//        for (i = 1; i < out_learnt.size(); i++)
//            abstract_level |= abstractLevel(var(out_learnt[i])); // (maintain an abstraction of levels involved in conflict)
//
//        for (i = j = 1; i < out_learnt.size(); i++)
//            if (reason(var(out_learnt[i])) == CRef_Undef || !litRedundant(out_learnt[i], abstract_level))
//                out_learnt[j++] = out_learnt[i];
//        
//    }else if (ccmin_mode == 1){
//        for (i = j = 1; i < out_learnt.size(); i++){
//            Var x = var(out_learnt[i]);
//
//            if (reason(x) == CRef_Undef)
//                out_learnt[j++] = out_learnt[i];
//            else{
//                Clause& c = ca[reason(var(out_learnt[i]))];
//                for (int k = 1; k < c.size(); k++)
//                    if (!seen[var(c[k])] && level(var(c[k])) > 0){
//                        out_learnt[j++] = out_learnt[i];
//                        break; }
//            }
//        }
//    }else
//        i = j = out_learnt.size();
//
//    max_literals += out_learnt.size();
//    out_learnt.shrink(i - j);
//    tot_literals += out_learnt.size();
//
//    // Find correct backtrack level:
//    //
//    if (out_learnt.size() == 1)
//        out_btlevel = 0;
//    else{
//        int max_i = 1;
//        // Find the first literal assigned at the next-highest level:
//        for (int i = 2; i < out_learnt.size(); i++)
//            if (level(var(out_learnt[i])) > level(var(out_learnt[max_i])))
//                max_i = i;
//        // Swap-in this literal at index 1:
//        Lit p             = out_learnt[max_i];
//        out_learnt[max_i] = out_learnt[1];
//        out_learnt[1]     = p;
//        out_btlevel       = level(var(p));
//    }
//
//    for (int j = 0; j < analyze_toclear.size(); j++) seen[var(analyze_toclear[j])] = 0;    // ('seen[]' is now cleared)
//}
// In original code, the argument out_btlevel is updatable reference.
// But this method return it.
int analyze(Clause confl, VecLit out_learnt, int out_btlevel) {
    int pathC = 0;
    Lit p     = Lit.UNDEF;

    // Generate conflict clause:
    //
    out_learnt.push();      // (leave room for the asserting literal)
    int index   = trail.size() - 1;

    do{
        assert(confl != Clause.CRef_Undef); // (otherwise should be UIP)
        Clause c = confl;

        if (c.learnt())
            claBumpActivity(c);

        for (int j = p == Lit.UNDEF ? 0 : 1; j < c.size(); j++){
            Lit q = c.get(j);

            if (!seen.get(q.var()) && level(q.var()) > 0){
                varBumpActivity(Var.valueOf(q.var()));
                seen.set(q.var(), true);
                if (level(q.var()) >= decisionLevel())
                    pathC++;
                else
                    out_learnt.push(q);
            }
        }
        
        // Select next clause to look at:
        while (!seen.get(trail.get(index--).var()));
        p     = trail.get(index + 1);
        confl = reason(Var.valueOf(p.var()));
        seen.set(p.var(), false);
        pathC--;

    }while (pathC > 0);
    out_learnt.set(0, p.not());

    // Simplify conflict clause:
    //
    int i, j;
    out_learnt.copyTo(analyze_toclear);
    if (ccmin_mode == 2) {
        int abstract_level = 0;
        for (i = 1; i < out_learnt.size(); i++)
            abstract_level |= abstractLevel(out_learnt.get(i).var()); // (maintain an abstraction of levels involved in conflict)

        for (i = j = 1; i < out_learnt.size(); i++)
            if (reason(Var.valueOf(out_learnt.get(i).var())) == Clause.CRef_Undef || !litRedundant(out_learnt.get(i), abstract_level))
            	out_learnt.set(j++, out_learnt.get(i));
        
    } else if (ccmin_mode == 1) {
        for (i = j = 1; i < out_learnt.size(); i++) {
        	Var x = Var.valueOf(out_learnt.get(i).var());

            if (reason(x) == Clause.CRef_Undef)
                out_learnt.set(j++, out_learnt.get(i));
            else {
                Clause c = reason(Var.valueOf(out_learnt.get(i).var()));
                for (int k = 1; k < c.size(); k++)
                    if (!seen.get(c.get(k).var()) && level(c.get(k).var()) > 0) {
                        out_learnt.set(j++, out_learnt.get(i));
                        break;
                    }
            }
        }
    }else
        i = j = out_learnt.size();

    max_literals += out_learnt.size();
    out_learnt.shrink(i - j);
    tot_literals += out_learnt.size();

    // Find correct backtrack level:
    //
    if (out_learnt.size() == 1)
        out_btlevel = 0;
    else{
        int max_i = 1;
        // Find the first literal assigned at the next-highest level:
        for (int k = 2; k < out_learnt.size(); k++)
            if (level(out_learnt.get(k).var()) > level(out_learnt.get(max_i).var()))
                max_i = k;
        // Swap-in this literal at index 1:
        Lit pp             = out_learnt.get(max_i);
        out_learnt.set(max_i, out_learnt.get(1));
        out_learnt.set(1, pp);
        out_btlevel = level(pp.var());
    }

    for (int k = 0; k < analyze_toclear.size(); k++)
        seen.set(analyze_toclear.get(k).var(), false);    // ('seen[]' is now cleared)
    return out_btlevel;
}
//
//
//// Check if 'p' can be removed. 'abstract_levels' is used to abort early if the algorithm is
//// visiting literals at levels that cannot be removed later.
//bool Solver::litRedundant(Lit p, uint32_t abstract_levels)
//{
//    analyze_stack.clear(); analyze_stack.push(p);
//    int top = analyze_toclear.size();
//    while (analyze_stack.size() > 0){
//        assert(reason(var(analyze_stack.last())) != CRef_Undef);
//        Clause& c = ca[reason(var(analyze_stack.last()))]; analyze_stack.pop();
//
//        for (int i = 1; i < c.size(); i++){
//            Lit p  = c[i];
//            if (!seen[var(p)] && level(var(p)) > 0){
//                if (reason(var(p)) != CRef_Undef && (abstractLevel(var(p)) & abstract_levels) != 0){
//                    seen[var(p)] = 1;
//                    analyze_stack.push(p);
//                    analyze_toclear.push(p);
//                }else{
//                    for (int j = top; j < analyze_toclear.size(); j++)
//                        seen[var(analyze_toclear[j])] = 0;
//                    analyze_toclear.shrink(analyze_toclear.size() - top);
//                    return false;
//                }
//            }
//        }
//    }
//
//    return true;
//}
boolean litRedundant(Lit p, int abstract_levels) {
    analyze_stack.clear(); analyze_stack.push(p);
    int top = analyze_toclear.size();
    while (analyze_stack.size() > 0){
        assert(reason(Var.valueOf(analyze_stack.last().var())) != Clause.CRef_Undef);
        Clause c = reason(Var.valueOf(analyze_stack.last().var())); analyze_stack.pop();

        for (int i = 1; i < c.size(); i++){
            Lit pp  = c.get(i);
            if (!seen.get(pp.var()) && level(pp.var()) > 0){
                if (reason(Var.valueOf(pp.var())) != Clause.CRef_Undef && (abstractLevel(pp.var()) & abstract_levels) != 0){
                    seen.set(pp.var(), true);
                    analyze_stack.push(pp);
                    analyze_toclear.push(pp);
                }else{
                    for (int j = top; j < analyze_toclear.size(); j++)
                        seen.set(analyze_toclear.get(j).var(), false);
                    analyze_toclear.shrink(analyze_toclear.size() - top);
                    return false;
                }
            }
        }
    }

    return true;
}
//
//
///*_________________________________________________________________________________________________
//|
//|  analyzeFinal : (p : Lit)  ->  [void]
//|  
//|  Description:
//|    Specialized analysis procedure to express the final conflict in terms of assumptions.
//|    Calculates the (possibly empty) set of assumptions that led to the assignment of 'p', and
//|    stores the result in 'out_conflict'.
//|________________________________________________________________________________________________@*/
//void Solver::analyzeFinal(Lit p, vec<Lit>& out_conflict)
//{
//    out_conflict.clear();
//    out_conflict.push(p);
//
//    if (decisionLevel() == 0)
//        return;
//
//    seen[var(p)] = 1;
//
//    for (int i = trail.size()-1; i >= trail_lim[0]; i--){
//        Var x = var(trail[i]);
//        if (seen[x]){
//            if (reason(x) == CRef_Undef){
//                assert(level(x) > 0);
//                out_conflict.push(~trail[i]);
//            }else{
//                Clause& c = ca[reason(x)];
//                for (int j = 1; j < c.size(); j++)
//                    if (level(var(c[j])) > 0)
//                        seen[var(c[j])] = 1;
//            }
//            seen[x] = 0;
//        }
//    }
//
//    seen[var(p)] = 0;
//}
void analyzeFinal(Lit p, VecLit out_conflict) {
    out_conflict.clear();
    out_conflict.push(p);

    if (decisionLevel() == 0)
        return;

    seen.set(p.var(), true);

    for (int i = trail.size()-1; i >= trail_lim.get(0); i--){
        Var x = Var.valueOf(trail.get(i).var());
        if (seen.get(x.value())){
            if (reason(x) == Clause.CRef_Undef){
                assert(level(x.value()) > 0);
                out_conflict.push(trail.get(i).not());
            }else{
                Clause c = reason(x);
                for (int j = 1; j < c.size(); j++)
                    if (level(c.get(j).var()) > 0)
                        seen.set(c.get(j).var(), true);
            }
            seen.set(x.value(), false);
        }
    }

    seen.set(p.var(), false);
}
//
//
//void Solver::uncheckedEnqueue(Lit p, CRef from)
//{
//    assert(value(p) == l_Undef);
//    assigns[var(p)] = lbool(!sign(p));
//    vardata[var(p)] = mkVarData(from, decisionLevel());
//    trail.push_(p);
//}
void uncheckedEnqueue(Lit p, Clause from)
{
    if (value(p) != Lbool.UNDEF)
    	throw new IllegalArgumentException("p");
    assigns.set(p.var(), Lbool.valueOf(!p.sign()));
    vardata.set(p.var(), VarData.mkVarData(from, decisionLevel()));
    trail.push_(p);
}
void uncheckedEnqueue(Lit p) {
	uncheckedEnqueue(p, Clause.CRef_Undef);
}
//
//
///*_________________________________________________________________________________________________
//|
//|  propagate : [void]  ->  [Clause*]
//|  
//|  Description:
//|    Propagates all enqueued facts. If a conflict arises, the conflicting clause is returned,
//|    otherwise CRef_Undef.
//|  
//|    Post-conditions:
//|      * the propagation queue is empty, even if there was a conflict.
//|________________________________________________________________________________________________@*/
//CRef Solver::propagate()
//{
//    CRef    confl     = CRef_Undef;
//    int     num_props = 0;
//    watches.cleanAll();
//
//    while (qhead < trail.size()){
//        Lit            p   = trail[qhead++];     // 'p' is enqueued fact to propagate.
//        vec<Watcher>&  ws  = watches[p];
//        Watcher        *i, *j, *end;
//        num_props++;
//
//        for (i = j = (Watcher*)ws, end = i + ws.size();  i != end;){
//            // Try to avoid inspecting the clause:
//            Lit blocker = i->blocker;
//            if (value(blocker) == l_True){
//                *j++ = *i++; continue; }
//
//            // Make sure the false literal is data[1]:
//            CRef     cr        = i->cref;
//            Clause&  c         = ca[cr];
//            Lit      false_lit = ~p;
//            if (c[0] == false_lit)
//                c[0] = c[1], c[1] = false_lit;
//            assert(c[1] == false_lit);
//            i++;
//
//            // If 0th watch is true, then clause is already satisfied.
//            Lit     first = c[0];
//            Watcher w     = Watcher(cr, first);
//            if (first != blocker && value(first) == l_True){
//                *j++ = w; continue; }
//
//            // Look for new watch:
//            for (int k = 2; k < c.size(); k++)
//                if (value(c[k]) != l_False){
//                    c[1] = c[k]; c[k] = false_lit;
//                    watches[~c[1]].push(w);
//                    goto NextClause; }
//
//            // Did not find watch -- clause is unit under assignment:
//            *j++ = w;
//            if (value(first) == l_False){
//                confl = cr;
//                qhead = trail.size();
//                // Copy the remaining watches:
//                while (i < end)
//                    *j++ = *i++;
//            }else
//                uncheckedEnqueue(first, cr);
//
//        NextClause:;
//        }
//        ws.shrink(i - j);
//    }
//    propagations += num_props;
//    simpDB_props -= num_props;
//
//    return confl;
//}
Clause propagate() {
    Clause    confl     = Clause.CRef_Undef;
    int     num_props = 0;
    watches.cleanAll();

    while (qhead < trail.size()){
        Lit           p   = trail.get(qhead++);     // 'p' is enqueued fact to propagate.
        Vec<Watcher>  ws  = watches.get(p);
        num_props++;

        int i = 0, j = 0, size = ws.size();
        L: while (i < size) {
            // Try to avoid inspecting the clause:
            Lit blocker = ws.get(i).blocker;
            if (value(blocker) == Lbool.TRUE){
            	ws.set(j++, ws.get(i++));
                continue;
            }

            // Make sure the false literal is data[1]:
            Clause c = ws.get(i).cref;
            Lit false_lit = p.not();
            if (c.get(0) == false_lit) {
            	c.set(0, c.get(1));
            	c.set(1, false_lit);
            }
            assert c.get(1) == false_lit;
            i++;

            // If 0th watch is true, then clause is already satisfied.
            Lit first = c.get(0);
            Watcher w = new Watcher(c, first);
            if (first != blocker && value(first) == Lbool.TRUE) {
            	ws.set(j++, w);
                continue;
            }

            // Look for new watch:
            for (int k = 2; k < c.size(); k++) {
                Lit ck = c.get(k);
                if (value(ck) != Lbool.FALSE) {
                	c.set(1, ck);
                	c.set(k, false_lit);
                	watches.get(c.get(1).not()).push(w);
                    continue L;
                 }
            }

            // Did not find watch -- clause is unit under assignment:
            ws.set(j++, w);
            if (value(first) == Lbool.FALSE) {
                confl = c;
                qhead = trail.size();
                // Copy the remaining watches:
                while (i < size)
                    ws.set(j++, ws.get(i++));
            }else
                uncheckedEnqueue(first, c);
        }
        ws.shrink(i - j);
    }
    propagations += num_props;
    simpDB_props -= num_props;

    return confl;
}
//
//
///*_________________________________________________________________________________________________
//|
//|  reduceDB : ()  ->  [void]
//|  
//|  Description:
//|    Remove half of the learnt clauses, minus the clauses locked by the current assignment. Locked
//|    clauses are clauses that are reason to some assignment. Binary clauses are never removed.
//|________________________________________________________________________________________________@*/
//struct reduceDB_lt { 
//    ClauseAllocator& ca;
//    reduceDB_lt(ClauseAllocator& ca_) : ca(ca_) {}
//    bool operator () (CRef x, CRef y) { 
//        return ca[x].size() > 2 && (ca[y].size() == 2 || ca[x].activity() < ca[y].activity()); } 
//};
static class ReduceDB_lt implements Comparator<Clause> { 
	@Override
	public int compare(Clause x, Clause y) {
//        return x.size() > 2 && (y.size() == 2 || x.activity() < y.activity()) ? -1 : 1;
        if (x.size() > 2 && (y.size() == 2 || x.activity() < y.activity()))
            return -1;
        else if (y.size() > 2 && (x.size() == 2 || y.activity() < x.activity()))
            return 1;
        else
            return 0;
	}
}
static final ReduceDB_lt REDUCEDB_LT = new ReduceDB_lt();
//void Solver::reduceDB()
//{
//    int     i, j;
//    double  extra_lim = cla_inc / learnts.size();    // Remove any clause below this activity
//
//    sort(learnts, reduceDB_lt(ca));
//    // Don't delete binary or locked clauses. From the rest, delete clauses from the first half
//    // and clauses with activity smaller than 'extra_lim':
//    for (i = j = 0; i < learnts.size(); i++){
//        Clause& c = ca[learnts[i]];
//        if (c.size() > 2 && !locked(c) && (i < learnts.size() / 2 || c.activity() < extra_lim))
//            removeClause(learnts[i]);
//        else
//            learnts[j++] = learnts[i];
//    }
//    learnts.shrink(i - j);
//    checkGarbage();
//}
void reduceDB() {
    int     i, j;
    double  extra_lim = cla_inc / learnts.size();    // Remove any clause below this activity

//    sort(learnts, reduceDB_lt(ca));
    learnts.sort(REDUCEDB_LT);
    // Don't delete binary or locked clauses. From the rest, delete clauses from the first half
    // and clauses with activity smaller than 'extra_lim':
    for (i = j = 0; i < learnts.size(); i++){
        Clause c = learnts.get(i);
        if (c.size() > 2 && !locked(c) && (i < learnts.size() / 2 || c.activity() < extra_lim))
            removeClause(learnts.get(i));
        else
        	learnts.set(j++, learnts.get(i));
    }
    learnts.shrink(i - j);
    checkGarbage();
}
//
//
//void Solver::removeSatisfied(vec<CRef>& cs)
//{
//    int i, j;
//    for (i = j = 0; i < cs.size(); i++){
//        Clause& c = ca[cs[i]];
//        if (satisfied(c))
//            removeClause(cs[i]);
//        else
//            cs[j++] = cs[i];
//    }
//    cs.shrink(i - j);
//}
void removeSatisfied(Vec<Clause> cs) {
    int i, j;
    for (i = j = 0; i < cs.size(); i++){
        Clause c = cs.get(i);
        if (satisfied(c))
            removeClause(cs.get(i));
        else
        	cs.set(j++, cs.get(i));
    }
    cs.shrink(i - j);
}
//
//
//void Solver::rebuildOrderHeap()
//{
//    vec<Var> vs;
//    for (Var v = 0; v < nVars(); v++)
//        if (decision[v] && value(v) == l_Undef)
//            vs.push(v);
//    order_heap.build(vs);
//}
void rebuildOrderHeap() {
    VecInt vs = new VecInt();
    for (int v = 0; v < nVars(); v++)
        if (decision.get(v) && value(Var.valueOf(v)) == Lbool.UNDEF)
            vs.push(v);
    order_heap.build(vs);
}
//
//
///*_________________________________________________________________________________________________
//|
//|  simplify : [void]  ->  [bool]
//|  
//|  Description:
//|    Simplify the clause database according to the current top-level assigment. Currently, the only
//|    thing done here is the removal of satisfied clauses, but more things can be put here.
//|________________________________________________________________________________________________@*/
//bool Solver::simplify()
//{
//    assert(decisionLevel() == 0);
//
//    if (!ok || propagate() != CRef_Undef)
//        return ok = false;
//
//    if (nAssigns() == simpDB_assigns || (simpDB_props > 0))
//        return true;
//
//    // Remove satisfied clauses:
//    removeSatisfied(learnts);
//    if (remove_satisfied)        // Can be turned off.
//        removeSatisfied(clauses);
//    checkGarbage();
//    rebuildOrderHeap();
//
//    simpDB_assigns = nAssigns();
//    simpDB_props   = clauses_literals + learnts_literals;   // (shouldn't depend on stats really, but it will do for now)
//
//    return true;
//}
boolean simplify() {
	if (decisionLevel() != 0)
		throw new IllegalStateException("decisionLevel() != 0");
    if (!ok || propagate() != Clause.CRef_Undef)
        return ok = false;

    if (nAssigns() == simpDB_assigns || (simpDB_props > 0))
        return true;

    // Remove satisfied clauses:
    removeSatisfied(learnts);
    if (remove_satisfied)        // Can be turned off.
        removeSatisfied(clauses);
    checkGarbage();
    rebuildOrderHeap();

    simpDB_assigns = nAssigns();
    simpDB_props   = clauses_literals + learnts_literals;   // (shouldn't depend on stats really, but it will do for now)

    return true;
}
//
//
///*_________________________________________________________________________________________________
//|
//|  search : (nof_conflicts : int) (params : const SearchParams&)  ->  [lbool]
//|  
//|  Description:
//|    Search for a model the specified number of conflicts. 
//|    NOTE! Use negative value for 'nof_conflicts' indicate infinity.
//|  
//|  Output:
//|    'l_True' if a partial assigment that is consistent with respect to the clauseset is found. If
//|    all variables are decision variables, this means that the clause set is satisfiable. 'l_False'
//|    if the clause set is unsatisfiable. 'l_Undef' if the bound on number of conflicts is reached.
//|________________________________________________________________________________________________@*/
//lbool Solver::search(int nof_conflicts)
//{
//    assert(ok);
//    int         backtrack_level;
//    int         conflictC = 0;
//    vec<Lit>    learnt_clause;
//    starts++;
//
//    for (;;){
//        CRef confl = propagate();
//        if (confl != CRef_Undef){
//            // CONFLICT
//            conflicts++; conflictC++;
//            if (decisionLevel() == 0) return l_False;
//
//            learnt_clause.clear();
//            analyze(confl, learnt_clause, backtrack_level);
//            cancelUntil(backtrack_level);
//
//            if (learnt_clause.size() == 1){
//                uncheckedEnqueue(learnt_clause[0]);
//            }else{
//                CRef cr = ca.alloc(learnt_clause, true);
//                learnts.push(cr);
//                attachClause(cr);
//                claBumpActivity(ca[cr]);
//                uncheckedEnqueue(learnt_clause[0], cr);
//            }
//
//            varDecayActivity();
//            claDecayActivity();
//
//            if (--learntsize_adjust_cnt == 0){
//                learntsize_adjust_confl *= learntsize_adjust_inc;
//                learntsize_adjust_cnt    = (int)learntsize_adjust_confl;
//                max_learnts             *= learntsize_inc;
//
//                if (verbosity >= 1)
//                    printf("| %9d | %7d %8d %8d | %8d %8d %6.0f | %6.3f %% |\n", 
//                           (int)conflicts, 
//                           (int)dec_vars - (trail_lim.size() == 0 ? trail.size() : trail_lim[0]), nClauses(), (int)clauses_literals, 
//                           (int)max_learnts, nLearnts(), (double)learnts_literals/nLearnts(), progressEstimate()*100);
//            }
//
//        }else{
//            // NO CONFLICT
//            if (nof_conflicts >= 0 && conflictC >= nof_conflicts || !withinBudget()){
//                // Reached bound on number of conflicts:
//                progress_estimate = progressEstimate();
//                cancelUntil(0);
//                return l_Undef; }
//
//            // Simplify the set of problem clauses:
//            if (decisionLevel() == 0 && !simplify())
//                return l_False;
//
//            if (learnts.size()-nAssigns() >= max_learnts)
//                // Reduce the set of learnt clauses:
//                reduceDB();
//
//            Lit next = lit_Undef;
//            while (decisionLevel() < assumptions.size()){
//                // Perform user provided assumption:
//                Lit p = assumptions[decisionLevel()];
//                if (value(p) == l_True){
//                    // Dummy decision level:
//                    newDecisionLevel();
//                }else if (value(p) == l_False){
//                    analyzeFinal(~p, conflict);
//                    return l_False;
//                }else{
//                    next = p;
//                    break;
//                }
//            }
//
//            if (next == lit_Undef){
//                // New variable decision:
//                decisions++;
//                next = pickBranchLit();
//
//                if (next == lit_Undef)
//                    // Model found:
//                    return l_True;
//            }
//
//            // Increase decision level and enqueue 'next'
//            newDecisionLevel();
//            uncheckedEnqueue(next);
//        }
//    }
//}
Lbool search(int nof_conflicts) {
	if (!ok)
		throw new IllegalStateException("!ok");
    int backtrack_level = 0;
    int conflictC = 0;
    VecLit learnt_clause = new VecLit();
    starts++;

    for (;;){
        Clause confl = propagate();
        if (confl != Clause.CRef_Undef){
            // CONFLICT
            conflicts++; conflictC++;
            if (decisionLevel() == 0) return Lbool.FALSE;

            learnt_clause.clear();
            backtrack_level = analyze(confl, learnt_clause, backtrack_level);
            cancelUntil(backtrack_level);

            if (learnt_clause.size() == 1){
                uncheckedEnqueue(learnt_clause.get(0));
            }else{
                Clause cr = new Clause(learnt_clause, true, true);
                learnts.push(cr);
                attachClause(cr);
                claBumpActivity(cr);
                uncheckedEnqueue(learnt_clause.get(0), cr);
            }

            varDecayActivity();
            claDecayActivity();

            if (--learntsize_adjust_cnt == 0){
                learntsize_adjust_confl *= learntsize_adjust_inc;
                learntsize_adjust_cnt    = (int)learntsize_adjust_confl;
                max_learnts             *= learntsize_inc;

                if (verbosity >= 1)
                    printf("| %9d | %7d %8d %8d | %8d %8d %6.0f | %6.3f %% |", 
                           (int)conflicts, 
                           (int)dec_vars - (trail_lim.size() == 0 ? trail.size() : trail_lim.get(0)), nClauses(), (int)clauses_literals, 
                           (int)max_learnts, nLearnts(), (double)learnts_literals/nLearnts(), progressEstimate()*100);
            }

        }else{
            // NO CONFLICT
            if (nof_conflicts >= 0 && conflictC >= nof_conflicts || !withinBudget()){
                // Reached bound on number of conflicts:
                progress_estimate = progressEstimate();
                cancelUntil(0);
                return Lbool.UNDEF;
            }

            // Simplify the set of problem clauses:
            if (decisionLevel() == 0 && !simplify())
                return Lbool.FALSE;

            if (learnts.size()-nAssigns() >= max_learnts)
                // Reduce the set of learnt clauses:
                reduceDB();

            Lit next = Lit.UNDEF;
            while (decisionLevel() < assumptions.size()){
                // Perform user provided assumption:
                Lit p = assumptions.get(decisionLevel());
                if (value(p) == Lbool.TRUE) {
                    // Dummy decision level:
                    newDecisionLevel();
                }else if (value(p) == Lbool.FALSE) {
                    analyzeFinal(p.not(), conflict);
                    return Lbool.FALSE;
                }else{
                    next = p;
                    break;
                }
            }

            if (next == Lit.UNDEF) {
                // New variable decision:
                decisions++;
                next = pickBranchLit();

                if (next == Lit.UNDEF)
                    // Model found:
                    return Lbool.TRUE;
            }

            // Increase decision level and enqueue 'next'
            newDecisionLevel();
            uncheckedEnqueue(next);
        }
    }
}
//
//
//double Solver::progressEstimate() const
//{
//    double  progress = 0;
//    double  F = 1.0 / nVars();
//
//    for (int i = 0; i <= decisionLevel(); i++){
//        int beg = i == 0 ? 0 : trail_lim[i - 1];
//        int end = i == decisionLevel() ? trail.size() : trail_lim[i];
//        progress += pow(F, i) * (end - beg);
//    }
//
//    return progress / nVars();
//}
double progressEstimate() {
    double  progress = 0;
    double  F = 1.0 / nVars();

    for (int i = 0; i <= decisionLevel(); i++){
        int beg = i == 0 ? 0 : trail_lim.get(i - 1);
        int end = i == decisionLevel() ? trail.size() : trail_lim.get(i);
        progress += Math.pow(F, i) * (end - beg);
    }

    return progress / nVars();
}
//
///*
//  Finite subsequences of the Luby-sequence:
//
//  0: 1
//  1: 1 1 2
//  2: 1 1 2 1 1 2 4
//  3: 1 1 2 1 1 2 4 1 1 2 1 1 2 4 8
//  ...
//
//
// */
//
//static double luby(double y, int x){
//
//    // Find the finite subsequence that contains index 'x', and the
//    // size of that subsequence:
//    int size, seq;
//    for (size = 1, seq = 0; size < x+1; seq++, size = 2*size+1);
//
//    while (size-1 != x){
//        size = (size-1)>>1;
//        seq--;
//        x = x % size;
//    }
//
//    return pow(y, seq);
//}
static double luby(double y, int x) {

    // Find the finite subsequence that contains index 'x', and the
    // size of that subsequence:
    int size, seq;
    for (size = 1, seq = 0; size < x+1; seq++, size = 2*size+1);

    while (size-1 != x){
        size = (size-1)>>1;
        seq--;
        x = x % size;
    }

    return Math.pow(y, seq);
}
//
//// NOTE: assumptions passed in member-variable 'assumptions'.
//lbool Solver::solve_()
//{
//    model.clear();
//    conflict.clear();
//    if (!ok) return l_False;
//
//    solves++;
//
//    max_learnts               = nClauses() * learntsize_factor;
//    learntsize_adjust_confl   = learntsize_adjust_start_confl;
//    learntsize_adjust_cnt     = (int)learntsize_adjust_confl;
//    lbool   status            = l_Undef;
//
//    if (verbosity >= 1){
//        printf("============================[ Search Statistics ]==============================\n");
//        printf("| Conflicts |          ORIGINAL         |          LEARNT          | Progress |\n");
//        printf("|           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |\n");
//        printf("===============================================================================\n");
//    }
//
//    // Search:
//    int curr_restarts = 0;
//    while (status == l_Undef){
//        double rest_base = luby_restart ? luby(restart_inc, curr_restarts) : pow(restart_inc, curr_restarts);
//        status = search(rest_base * restart_first);
//        if (!withinBudget()) break;
//        curr_restarts++;
//    }
//
//    if (verbosity >= 1)
//        printf("===============================================================================\n");
//
//
//    if (status == l_True){
//        // Extend & copy model:
//        model.growTo(nVars());
//        for (int i = 0; i < nVars(); i++) model[i] = value(i);
//    }else if (status == l_False && conflict.size() == 0)
//        ok = false;
//
//    cancelUntil(0);
//    return status;
//}
Lbool solve_() {
    model.clear();
    conflict.clear();
    if (!ok) return Lbool.FALSE;

    solves++;

    max_learnts               = nClauses() * learntsize_factor;
    learntsize_adjust_confl   = learntsize_adjust_start_confl;
    learntsize_adjust_cnt     = (int)learntsize_adjust_confl;
    Lbool   status            = Lbool.UNDEF;

    if (verbosity >= 1){
        printf("============================[ Search Statistics ]==============================");
        printf("| Conflicts |          ORIGINAL         |          LEARNT          | Progress |");
        printf("|           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |");
        printf("===============================================================================");
    }

    // Search:
    int curr_restarts = 0;
    while (status == Lbool.UNDEF) {
        double rest_base = luby_restart ? luby(restart_inc, curr_restarts) : Math.pow(restart_inc, curr_restarts);
        status = search((int)(rest_base * restart_first));
        if (!withinBudget()) break;
        curr_restarts++;
    }

    if (verbosity >= 1)
        printf("===============================================================================");


    if (status == Lbool.TRUE) {
        // Extend & copy model:
        model.growTo(nVars());
        for (int i = 0; i < nVars(); i++)
        	model.set(i, value(Var.valueOf(i)));
    } else if (status == Lbool.FALSE && conflict.size() == 0)
        ok = false;

    cancelUntil(0);
    return status;
}
//
////=================================================================================================
//// Writing CNF to DIMACS:
//// 
//// FIXME: this needs to be rewritten completely.
//
//static Var mapVar(Var x, vec<Var>& map, Var& max)
//{
//    if (map.size() <= x || map[x] == -1){
//        map.growTo(x+1, -1);
//        map[x] = max++;
//    }
//    return map[x];
//}
static Var mapVar(int x, VecVar map, int[] max) {
    if (map.size() <= x || map.get(x) == Var.UNDEF){
        map.growTo(x + 1, Var.UNDEF);
        map.set(x, Var.valueOf(max[0]++));
    }
    return map.get(x);
}
//
//
//void Solver::toDimacs(FILE* f, Clause& c, vec<Var>& map, Var& max)
//{
//    if (satisfied(c)) return;
//
//    for (int i = 0; i < c.size(); i++)
//        if (value(c[i]) != l_False)
//            fprintf(f, "%s%d ", sign(c[i]) ? "-" : "", mapVar(var(c[i]), map, max)+1);
//    fprintf(f, "0\n");
//}
void toDimacs(PrintWriter f, Clause c, VecVar map, int[] max) {
    if (satisfied(c)) return;

    for (int i = 0; i < c.size(); i++)
        if (value(c.get(i)) != Lbool.FALSE)
            f.printf("%s%d ", c.get(i).sign() ? "-" : "", mapVar(c.get(i).var(), map, max).value() + 1);
    f.printf("0%n");
}
//
//
//void Solver::toDimacs(const char *file, const vec<Lit>& assumps)
//{
//    FILE* f = fopen(file, "wr");
//    if (f == NULL)
//        fprintf(stderr, "could not open file %s\n", file), exit(1);
//    toDimacs(f, assumps);
//    fclose(f);
//}
void toDimacs(File file, VecLit assumps) throws IOException {
	try (PrintWriter f = new PrintWriter(new FileWriter(file))) {
	    toDimacs(f, assumps);
	}
}
//
//
//void Solver::toDimacs(FILE* f, const vec<Lit>& assumps)
//{
//    // Handle case when solver is in contradictory state:
//    if (!ok){
//        fprintf(f, "p cnf 1 2\n1 0\n-1 0\n");
//        return; }
//
//    vec<Var> map; Var max = 0;
//
//    // Cannot use removeClauses here because it is not safe
//    // to deallocate them at this point. Could be improved.
//    int cnt = 0;
//    for (int i = 0; i < clauses.size(); i++)
//        if (!satisfied(ca[clauses[i]]))
//            cnt++;
//        
//    for (int i = 0; i < clauses.size(); i++)
//        if (!satisfied(ca[clauses[i]])){
//            Clause& c = ca[clauses[i]];
//            for (int j = 0; j < c.size(); j++)
//                if (value(c[j]) != l_False)
//                    mapVar(var(c[j]), map, max);
//        }
//
//    // Assumptions are added as unit clauses:
//    cnt += assumptions.size();
//
//    fprintf(f, "p cnf %d %d\n", max, cnt);
//
//    for (int i = 0; i < assumptions.size(); i++){
//        assert(value(assumptions[i]) != l_False);
//        fprintf(f, "%s%d 0\n", sign(assumptions[i]) ? "-" : "", mapVar(var(assumptions[i]), map, max)+1);
//    }
//
//    for (int i = 0; i < clauses.size(); i++)
//        toDimacs(f, ca[clauses[i]], map, max);
//
//    if (verbosity > 0)
//        printf("Wrote %d clauses with %d variables.\n", cnt, max);
//}
void toDimacs(PrintWriter f, VecLit assumps) {
    // Handle case when solver is in contradictory state:
    if (!ok){
        f.printf("p cnf 1 2%n1 0%n-1 0%n");
        return; }

    VecVar map = new VecVar();
    int[] max = {0};

    // Cannot use removeClauses here because it is not safe
    // to deallocate them at this point. Could be improved.
    int cnt = 0;
    for (int i = 0; i < clauses.size(); i++)
        if (!satisfied(clauses.get(i)))
            cnt++;
    for (int i = 0; i < clauses.size(); i++)
        if (!satisfied(clauses.get(i))){
            Clause c = clauses.get(i);
            for (int j = 0; j < c.size(); j++)
                if (value(c.get(j)) != Lbool.FALSE)
                    mapVar(c.get(j).var(), map, max);
        }

    // Assumptions are added as unit clauses:
    cnt += assumptions.size();

    f.printf("p cnf %d %d%n", max[0], cnt);

    for (int i = 0; i < assumptions.size(); i++){
    	if (value(assumptions.get(i)) != Lbool.FALSE)
    		throw new IllegalStateException();
        f.printf("%s%d 0%n", assumptions.get(i).sign() ? "-" : "", mapVar(assumptions.get(i).var(), map, max).value() + 1);
    }

    for (int i = 0; i < clauses.size(); i++)
        toDimacs(f, clauses.get(i), map, max);

    if (verbosity > 0)
        printf("Wrote %d clauses with %d variables.", cnt, max[0]);
}
//
//
////=================================================================================================
//// Garbage Collection methods:
//
//void Solver::relocAll(ClauseAllocator& to)
//{
//    // All watchers:
//    //
//    // for (int i = 0; i < watches.size(); i++)
//    watches.cleanAll();
//    for (int v = 0; v < nVars(); v++)
//        for (int s = 0; s < 2; s++){
//            Lit p = mkLit(v, s);
//            // printf(" >>> RELOCING: %s%d\n", sign(p)?"-":"", var(p)+1);
//            vec<Watcher>& ws = watches[p];
//            for (int j = 0; j < ws.size(); j++)
//                ca.reloc(ws[j].cref, to);
//        }
//
//    // All reasons:
//    //
//    for (int i = 0; i < trail.size(); i++){
//        Var v = var(trail[i]);
//
//        if (reason(v) != CRef_Undef && (ca[reason(v)].reloced() || locked(ca[reason(v)])))
//            ca.reloc(vardata[v].reason, to);
//    }
//
//    // All learnt:
//    //
//    for (int i = 0; i < learnts.size(); i++)
//        ca.reloc(learnts[i], to);
//
//    // All original:
//    //
//    for (int i = 0; i < clauses.size(); i++)
//        ca.reloc(clauses[i], to);
//}
void realocAll(ClauseAllocator to) {
	// do nothing
}
//
//
//void Solver::garbageCollect()
//{
//    // Initialize the next region to a size corresponding to the estimated utilization degree. This
//    // is not precise but should avoid some unnecessary reallocations for the new region:
//    ClauseAllocator to(ca.size() - ca.wasted()); 
//
//    relocAll(to);
//    if (verbosity >= 2)
//        printf("|  Garbage collection:   %12d bytes => %12d bytes             |\n", 
//               ca.size()*ClauseAllocator::Unit_Size, to.size()*ClauseAllocator::Unit_Size);
//    to.moveTo(ca);
//}
void garbageCollect() {
	// do nothing
}
}
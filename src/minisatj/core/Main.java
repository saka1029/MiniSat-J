package minisatj.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import minisatj.utils.BoolOption;
import minisatj.utils.DoubleOption;
import minisatj.utils.DoubleRange;
import minisatj.utils.IntOption;
import minisatj.utils.IntRange;
import minisatj.utils.Option;
import minisatj.utils.SystemStats;

///*****************************************************************************************[Main.cc]
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
//#include <errno.h>
//
//#include <signal.h>
//#include <zlib.h>
//
//#include "utils/System.h"
//#include "utils/ParseUtils.h"
//#include "utils/Options.h"
//#include "core/Dimacs.h"
//#include "core/Solver.h"
//
//using namespace Minisat;
public class Main {
//
////=================================================================================================
//
//
//void printStats(Solver& solver)
//{
//    double cpu_time = cpuTime();
//    double mem_used = memUsedPeak();
//    printf("restarts              : %"PRIu64"\n", solver.starts);
//    printf("conflicts             : %-12"PRIu64"   (%.0f /sec)\n", solver.conflicts   , solver.conflicts   /cpu_time);
//    printf("decisions             : %-12"PRIu64"   (%4.2f %% random) (%.0f /sec)\n", solver.decisions, (float)solver.rnd_decisions*100 / (float)solver.decisions, solver.decisions   /cpu_time);
//    printf("propagations          : %-12"PRIu64"   (%.0f /sec)\n", solver.propagations, solver.propagations/cpu_time);
//    printf("conflict literals     : %-12"PRIu64"   (%4.2f %% deleted)\n", solver.tot_literals, (solver.max_literals - solver.tot_literals)*100 / (double)solver.max_literals);
//    if (mem_used != 0) printf("Memory used           : %.2f MB\n", mem_used);
//    printf("CPU time              : %g s\n", cpu_time);
//}
	static void printf(String format, Object... args) {
		System.out.printf(format + "%n", args);
	}

	static void printStats(Solver solver, long elapseMills) {
	    double cpu_time = SystemStats.cpuTime();
	    double mem_used = SystemStats.memUsedPeak();
	    printf("restarts              : %d", solver.starts);
	    printf("conflicts             : %-12d   (%.0f /sec)", solver.conflicts   , solver.conflicts   /cpu_time);
	    printf("decisions             : %-12d   (%4.2f %% random) (%.0f /sec)", solver.decisions, (float)solver.rnd_decisions*100 / (float)solver.decisions, solver.decisions   /cpu_time);
	    printf("propagations          : %-12d   (%.0f /sec)", solver.propagations, solver.propagations/cpu_time);
	    printf("conflict literals     : %-12d   (%4.2f %% deleted)", solver.tot_literals, (solver.max_literals - solver.tot_literals)*100 / (double)solver.max_literals);
	    if (mem_used != 0) printf("Memory used           : %.2f MB", mem_used);
	    printf("CPU time              : %g sec", cpu_time);
	    printf("elapse time           : %g sec", (double)elapseMills / 1000);
	}
//
//
//static Solver* solver;
	static Solver solver;
//// Terminate by notifying the solver and back out gracefully. This is mainly to have a test-case
//// for this feature of the Solver as it may take longer than an immediate call to '_exit()'.
//static void SIGINT_interrupt(int signum) { solver->interrupt(); }
	static void SIGINT_interrupt(int signum) { solver.interrupt(); }
//
//// Note that '_exit()' rather than 'exit()' has to be used. The reason is that 'exit()' calls
//// destructors and may cause deadlocks if a malloc/free function happens to be running (these
//// functions are guarded by locks for multithreaded use).
//static void SIGINT_exit(int signum) {
//    printf("\n"); printf("*** INTERRUPTED ***\n");
//    if (solver->verbosity > 0){
//        printStats(*solver);
//        printf("\n"); printf("*** INTERRUPTED ***\n"); }
//    _exit(1); }
	static void SIGINT_exit(int signum) {
	    Log.warn(""); Log.warn("*** INTERRUPTED ***");
	    if (solver.verbosity > 0){
	        printStats(solver, 0);
	        Log.warn(""); Log.warn("*** INTERRUPTED ***"); }
	    System.exit(1);
	}
//
//
////=================================================================================================
//// Main:
//
//
//int main(int argc, char** argv)
//{
//    try {
//        setUsageHelp("USAGE: %s [options] <input-file> <result-output-file>\n\n  where input may be either in plain or gzipped DIMACS.\n");
//        // printf("This is MiniSat 2.0 beta\n");
//        
//#if defined(__linux__)
//        fpu_control_t oldcw, newcw;
//        _FPU_GETCW(oldcw); newcw = (oldcw & ~_FPU_EXTENDED) | _FPU_DOUBLE; _FPU_SETCW(newcw);
//        printf("WARNING: for repeatability, setting FPU to use double precision\n");
//#endif
//        // Extra options:
//        //
//        IntOption    verb   ("MAIN", "verb",   "Verbosity level (0=silent, 1=some, 2=more).", 1, IntRange(0, 2));
//        IntOption    cpu_lim("MAIN", "cpu-lim","Limit on CPU time allowed in seconds.\n", INT32_MAX, IntRange(0, INT32_MAX));
//        IntOption    mem_lim("MAIN", "mem-lim","Limit on memory usage in megabytes.\n", INT32_MAX, IntRange(0, INT32_MAX));
//        
//        parseOptions(argc, argv, true);
//
//        Solver S;
//        double initial_time = cpuTime();
//
//        S.verbosity = verb;
//        
//        solver = &S;
//        // Use signal handlers that forcibly quit until the solver will be able to respond to
//        // interrupts:
//        signal(SIGINT, SIGINT_exit);
//        signal(SIGXCPU,SIGINT_exit);
//
//        // Set limit on CPU-time:
//        if (cpu_lim != INT32_MAX){
//            rlimit rl;
//            getrlimit(RLIMIT_CPU, &rl);
//            if (rl.rlim_max == RLIM_INFINITY || (rlim_t)cpu_lim < rl.rlim_max){
//                rl.rlim_cur = cpu_lim;
//                if (setrlimit(RLIMIT_CPU, &rl) == -1)
//                    printf("WARNING! Could not set resource limit: CPU-time.\n");
//            } }
//
//        // Set limit on virtual memory:
//        if (mem_lim != INT32_MAX){
//            rlim_t new_mem_lim = (rlim_t)mem_lim * 1024*1024;
//            rlimit rl;
//            getrlimit(RLIMIT_AS, &rl);
//            if (rl.rlim_max == RLIM_INFINITY || new_mem_lim < rl.rlim_max){
//                rl.rlim_cur = new_mem_lim;
//                if (setrlimit(RLIMIT_AS, &rl) == -1)
//                    printf("WARNING! Could not set resource limit: Virtual memory.\n");
//            } }
//        
//        if (argc == 1)
//            printf("Reading from standard input... Use '--help' for help.\n");
//        
//        gzFile in = (argc == 1) ? gzdopen(0, "rb") : gzopen(argv[1], "rb");
//        if (in == NULL)
//            printf("ERROR! Could not open file: %s\n", argc == 1 ? "<stdin>" : argv[1]), exit(1);
//        
//        if (S.verbosity > 0){
//            printf("============================[ Problem Statistics ]=============================\n");
//            printf("|                                                                             |\n"); }
//        
//        parse_DIMACS(in, S);
//        gzclose(in);
//        FILE* res = (argc >= 3) ? fopen(argv[2], "wb") : NULL;
//        
//        if (S.verbosity > 0){
//            printf("|  Number of variables:  %12d                                         |\n", S.nVars());
//            printf("|  Number of clauses:    %12d                                         |\n", S.nClauses()); }
//        
//        double parsed_time = cpuTime();
//        if (S.verbosity > 0){
//            printf("|  Parse time:           %12.2f s                                       |\n", parsed_time - initial_time);
//            printf("|                                                                             |\n"); }
// 
//        // Change to signal-handlers that will only notify the solver and allow it to terminate
//        // voluntarily:
//        signal(SIGINT, SIGINT_interrupt);
//        signal(SIGXCPU,SIGINT_interrupt);
//       
//        if (!S.simplify()){
//            if (res != NULL) fprintf(res, "UNSAT\n"), fclose(res);
//            if (S.verbosity > 0){
//                printf("===============================================================================\n");
//                printf("Solved by unit propagation\n");
//                printStats(S);
//                printf("\n"); }
//            printf("UNSATISFIABLE\n");
//            exit(20);
//        }
//        
//        vec<Lit> dummy;
//        lbool ret = S.solveLimited(dummy);
//        if (S.verbosity > 0){
//            printStats(S);
//            printf("\n"); }
//        printf(ret == l_True ? "SATISFIABLE\n" : ret == l_False ? "UNSATISFIABLE\n" : "INDETERMINATE\n");
//        if (res != NULL){
//            if (ret == l_True){
//                fprintf(res, "SAT\n");
//                for (int i = 0; i < S.nVars(); i++)
//                    if (S.model[i] != l_Undef)
//                        fprintf(res, "%s%s%d", (i==0)?"":" ", (S.model[i]==l_True)?"":"-", i+1);
//                fprintf(res, " 0\n");
//            }else if (ret == l_False)
//                fprintf(res, "UNSAT\n");
//            else
//                fprintf(res, "INDET\n");
//            fclose(res);
//        }
//        
//#ifdef NDEBUG
//        exit(ret == l_True ? 10 : ret == l_False ? 20 : 0);     // (faster than "return", which will invoke the destructor for 'Solver')
//#else
//        return (ret == l_True ? 10 : ret == l_False ? 20 : 0);
//#endif
//    } catch (OutOfMemoryException&){
//        printf("===============================================================================\n");
//        printf("INDETERMINATE\n");
//        exit(0);
//    }
//}
	public static void main(String[] argv) throws IOException {
	    long start = System.currentTimeMillis();
        Option.setUsageHelp("USAGE: %s [options] <input-file> <result-output-file>%n%n  where input may be in plain DIMACS.%n");
        // printf("This is MiniSat 2.0 beta\n");
        
//#if defined(__linux__)
//        fpu_control_t oldcw, newcw;
//        _FPU_GETCW(oldcw); newcw = (oldcw & ~_FPU_EXTENDED) | _FPU_DOUBLE; _FPU_SETCW(newcw);
//        printf("WARNING: for repeatability, setting FPU to use double precision%n");
//#endif
////=================================================================================================
//// Options:
//
//
	
        final String _cat = "CORE";
//
        //static DoubleOption  opt_var_decay         (_cat, "var-decay",   "The variable activity decay factor",            0.95,     DoubleRange(0, false, 1, false));
        DoubleOption  opt_var_decay = new DoubleOption(_cat, "var-decay",   "The variable activity decay factor", Solver.VAR_DECAY_DEFAULT,     new DoubleRange(0, false, 1, false));
        //static DoubleOption  opt_clause_decay      (_cat, "cla-decay",   "The clause activity decay factor",              0.999,    DoubleRange(0, false, 1, false));
        DoubleOption  opt_clause_decay = new DoubleOption(_cat, "cla-decay",   "The clause activity decay factor", Solver.CLAUSE_DECAY_DEFAULT,    new DoubleRange(0, false, 1, false));
        //static DoubleOption  opt_random_var_freq   (_cat, "rnd-freq",    "The frequency with which the decision heuristic tries to choose a random variable", 0, DoubleRange(0, true, 1, true));
        DoubleOption  opt_random_var_freq = new DoubleOption(_cat, "rnd-freq",    "The frequency with which the decision heuristic tries to choose a random variable", Solver.RANDOM_VAR_FREQ_DEFAULT, new DoubleRange(0, true, 1, true));
        //static DoubleOption  opt_random_seed       (_cat, "rnd-seed",    "Used by the random variable selection",         91648253, DoubleRange(0, false, HUGE_VAL, false));
        DoubleOption  opt_random_seed = new DoubleOption(_cat, "rnd-seed",    "Used by the random variable selection", Solver.RANDOM_SEED_DEFAULT, new DoubleRange(0, false, Double.MAX_VALUE, false));
        //static IntOption     opt_ccmin_mode        (_cat, "ccmin-mode",  "Controls conflict clause minimization (0=none, 1=basic, 2=deep)", 2, IntRange(0, 2));
        IntOption     opt_ccmin_mode = new IntOption(_cat, "ccmin-mode",  "Controls conflict clause minimization (0=none, 1=basic, 2=deep)", Solver.CCMIN_MODE_DEFAULT, new IntRange(0, 2));
        //static IntOption     opt_phase_saving      (_cat, "phase-saving", "Controls the level of phase saving (0=none, 1=limited, 2=full)", 2, IntRange(0, 2));
        IntOption     opt_phase_saving = new IntOption(_cat, "phase-saving", "Controls the level of phase saving (0=none, 1=limited, 2=full)", Solver.PHASE_SAVING_DEFAULT, new IntRange(0, 2));
        //static BoolOption    opt_rnd_init_act      (_cat, "rnd-init",    "Randomize the initial activity", false);
        BoolOption    opt_rnd_init_act = new BoolOption(_cat, "rnd-init",    "Randomize the initial activity", Solver.RND_INIT_ACT_DEFAULT);
        //static BoolOption    opt_luby_restart      (_cat, "luby",        "Use the Luby restart sequence", true);
        BoolOption    opt_luby_restart = new BoolOption(_cat, "luby",        "Use the Luby restart sequence", Solver.LUBY_RESTART_DEFAULT);
        //static IntOption     opt_restart_first     (_cat, "rfirst",      "The base restart interval", 100, IntRange(1, INT32_MAX));
        IntOption     opt_restart_first = new IntOption(_cat, "rfirst",      "The base restart interval", Solver.RESTART_FIRST_DEFAULT, new IntRange(1, Integer.MAX_VALUE));
        //static DoubleOption  opt_restart_inc       (_cat, "rinc",        "Restart interval increase factor", 2, DoubleRange(1, false, HUGE_VAL, false));
        DoubleOption  opt_restart_inc = new DoubleOption(_cat, "rinc",        "Restart interval increase factor", Solver.RESTART_INC_DEFAULT, new DoubleRange(1, false, Double.MAX_VALUE, false));
        //static DoubleOption  opt_garbage_frac      (_cat, "gc-frac",     "The fraction of wasted memory allowed before a garbage collection is triggered",  0.20, DoubleRange(0, false, HUGE_VAL, false));
        DoubleOption  opt_garbage_frac = new DoubleOption(_cat, "gc-frac",     "The fraction of wasted memory allowed before a garbage collection is triggered",  Solver.GARBAGE_FRAC_DEFAULT, new DoubleRange(0, false, Double.MAX_VALUE, false));
        // Extra options:
        //
        IntOption opt_verbosity = new IntOption("MAIN", "verb",   "Verbosity level (0=silent, 1=some, 2=more).", 1, new IntRange(0, 2));
//        IntOption cpu_lim = new IntOption("MAIN", "cpu-lim","Limit on CPU time allowed in seconds.%n", Integer.MAX_VALUE, new IntRange(0, Integer.MAX_VALUE));
//        IntOption mem_lim = new IntOption("MAIN", "mem-lim","Limit on memory usage in megabytes.%n", Integer.MAX_VALUE, new IntRange(0, Integer.MAX_VALUE));
        
        argv = Option.parseOptions(argv, true);

        Solver S = new Solver();

        double initial_time = SystemStats.cpuTime();

        S.var_decay = opt_var_decay.value();
        S.clause_decay = opt_clause_decay.value();
        S.random_var_freq = opt_random_var_freq.value();
        S.random_seed = opt_random_seed.value();
        S.ccmin_mode = opt_ccmin_mode.value();
        S.phase_saving = opt_phase_saving.value();
        S.rnd_init_act = opt_rnd_init_act.value();
        S.luby_restart = opt_luby_restart.value();
        S.restart_first = opt_restart_first.value();
        S.restart_inc = opt_restart_inc.value();
        S.garbage_frac = opt_garbage_frac.value();
        S.verbosity = opt_verbosity.value();
        
        solver = S;
        // Use signal handlers that forcibly quit until the solver will be able to respond to
        // interrupts:
//        signal(SIGINT, SIGINT_exit);
//        signal(SIGXCPU,SIGINT_exit);

//        // Set limit on CPU-time:
//        if (cpu_lim != INT32_MAX){
//            rlimit rl;
//            getrlimit(RLIMIT_CPU, &rl);
//            if (rl.rlim_max == RLIM_INFINITY || (rlim_t)cpu_lim < rl.rlim_max){
//                rl.rlim_cur = cpu_lim;
//                if (setrlimit(RLIMIT_CPU, &rl) == -1)
//                    printf("WARNING! Could not set resource limit: CPU-time.\n");
//            } }
//
//        // Set limit on virtual memory:
//        if (mem_lim != INT32_MAX){
//            rlim_t new_mem_lim = (rlim_t)mem_lim * 1024*1024;
//            rlimit rl;
//            getrlimit(RLIMIT_AS, &rl);
//            if (rl.rlim_max == RLIM_INFINITY || new_mem_lim < rl.rlim_max){
//                rl.rlim_cur = new_mem_lim;
//                if (setrlimit(RLIMIT_AS, &rl) == -1)
//                    printf("WARNING! Could not set resource limit: Virtual memory.\n");
//            } }
        
        if (argv.length == 0)
            printf("Reading from standard input... Use '--help' for help.%n");
        
        try (Reader in = argv.length == 0 ? new InputStreamReader(System.in) : new FileReader(argv[0])) {
	        if (S.verbosity > 0){
	            printf("============================[ Problem Statistics ]=============================");
	            printf("|                                                                             |");
	        }
	        Dimacs.parse_DIMACS(in, S);
        }
        
        Lbool ret = Lbool.UNDEF;
        PrintWriter res = null;
        if (argv.length >= 2)
        	res = new PrintWriter(new FileWriter(argv[1]));
        try {
	        if (S.verbosity > 0){
	            printf("|  Number of variables:  %12d                                         |", S.nVars());
	            printf("|  Number of clauses:    %12d                                         |", S.nClauses()); }
	        
	        double parsed_time = SystemStats.cpuTime();
	        if (S.verbosity > 0){
	            printf("|  Parse time:           %12.2f sec                                     |", parsed_time - initial_time);
	            printf("|                                                                             |"); }
	 
	        // Change to signal-handlers that will only notify the solver and allow it to terminate
	        // voluntarily:
	//        signal(SIGINT, SIGINT_interrupt);
	//        signal(SIGXCPU,SIGINT_interrupt);
	       
	        if (!S.simplify()){
	            if (res != null)
	            	res.printf("UNSAT%n");
	            if (S.verbosity > 0){
	                printf("===============================================================================");
	                printf("Solved by unit propagation");
	                printStats(S, System.currentTimeMillis() - start);
	                printf(""); }
	            printf("UNSATISFIABLE");
	        }
	        
	        VecLit dummy = new VecLit();
	        ret = S.solveLimited(dummy);
	        if (S.verbosity > 0){
	            printStats(S, System.currentTimeMillis() - start);
	            printf(""); }
	        printf(ret == Lbool.TRUE ? "SATISFIABLE" : ret == Lbool.FALSE ? "UNSATISFIABLE" : "INDETERMINATE");
	        if (res != null){
	            if (ret == Lbool.TRUE) {
	                res.printf("SAT%n");
	                for (int i = 0; i < S.nVars(); i++)
	                    if (S.model.get(i) != Lbool.UNDEF)
	                        res.printf("%s%s%d", (i == 0)?"":" ", (S.model.get(i) == Lbool.TRUE) ? "" : "-", i + 1);
	                res.printf(" 0");
	            }else if (ret == Lbool.FALSE)
	                res.printf("UNSAT%n");
	            else
	                res.printf("INDET%n");
	        }
        } finally {
        	if (res != null) res.close();
        }
        System.exit(ret == Lbool.TRUE ? 10 : ret == Lbool.FALSE ? 20 : 0);
	}
}
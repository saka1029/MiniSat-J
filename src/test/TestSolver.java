package test;

import static org.junit.Assert.*;
import minisatj.core.Lbool;
import minisatj.core.Lit;
import minisatj.core.Solver;
import minisatj.core.VecLit;

import org.junit.Test;

public class TestSolver {

    static final Lbool T = Lbool.TRUE;
    static final Lbool F = Lbool.FALSE;
    static final Lbool U = Lbool.UNDEF;
    
    void solve(int[][] problem, boolean result, Lbool[] expects) {
        Solver solver = new Solver();
        for (int i = 0, imax = problem.length; i < imax; ++i) {
            VecLit lits = new VecLit();
            for (int j = 0, jmax = problem[i].length; j < jmax; ++j) {
                int v = problem[i][j];
                int var = Math.abs(v) - 1;
                lits.push(Lit.valueOf(var, v < 0));
            }
            solver.addClause(lits);
        }
        boolean ret = solver.solve();
        assertEquals(result, ret);
        if (ret)
            for (int i = 0, size = expects.length; i < size; ++i)
                assertEquals(expects[i], solver.model.get(i));
    }

    /*
     * Solving a simple problem
     * 
     *  p cnf 3 2
     *  1 -3 0
     *  2 3 -1 0
     *  0
     */
    @Test
    public void testSolveSimple() {
        Lbool [] expects = {Lbool.FALSE, Lbool.FALSE, Lbool.FALSE};
        Solver solver = new Solver();
        VecLit clause1 = new VecLit();
        clause1.push(Lit.valueOf(1, false));
        clause1.push(Lit.valueOf(3, true));
        solver.addClause(clause1);
        VecLit clause2 = new VecLit();
        clause2.push(Lit.valueOf(2, false));
        clause2.push(Lit.valueOf(3, false));
        clause2.push(Lit.valueOf(1, true));
        solver.addClause(clause2);
        boolean result = solver.solve();
        assertEquals(true, result);
        if (result)
            for (int i = 0, size = expects.length; i < size; ++i)
                assertEquals(expects[i], solver.model.get(i));
    }

    @Test
    public void testSimple() {
        int[][] problem = {
            {1, -3},
            {2, 3, -1},
        };
        Lbool [] expects = {F, F, F};
        solve(problem, true, expects);
    }

    /**
     * c satplan version 1.0
     * c created: Thu Jul 24 14:13:58 PDT 1997
     * c operators: bw_orig.ops
     * c facts: anomaly.facts
     * c goal: 3
     * c wff: anomaly.cnf
     * c map: anomaly.map
     * c type: wff
     * p cnf 48 261
     */
    @Test
    public void testAnomaly() {
        int[][] problem = {
            {-1,-3,4},
            {-1,-5,6},
            {-8,-7,9},
            {-8,-5,10},
            {-1,-11,-2},
            {-8,-13,-10},
            {-14,15},
            {-11,16},
            {-12,17},
            {-7,-15},
            {-3,-17},
            {-1,-12},
            {-1,-13},
            {-8,-14},
            {-8,-11},
            {7,15},
            {3,17},
            {1,2},
            {8,10},
            {11,-16},
            {1,-4},
            {1,-6},
            {8,-9},
            {3,-4},
            {5,-6},
            {7,-9},
            {1,8},
            {14,11,12,13},
            {7,3,5},
            {-1,-8},
            {-8,-1},
            {-14,-11},
            {-14,-12},
            {-14,-13},
            {-11,-14},
            {-11,-12},
            {-11,-13},
            {-12,-14},
            {-12,-11},
            {-12,-13},
            {-13,-14},
            {-13,-11},
            {-13,-12},
            {-7,-3},
            {-7,-5},
            {-3,-7},
            {-3,-5},
            {-5,-7},
            {-5,-3},
            {-1,-14},
            {-8,-12},
            {-1,-7},
            {-8,-3},
            {-14,-7},
            {-12,-3},
            {-13,-5},
            {-18,-19,20},
            {-18,-21,22},
            {-18,-23,24},
            {-25,-26,27},
            {-25,-21,28},
            {-25,-23,29},
            {-30,-26,31},
            {-30,-19,32},
            {-30,-23,33},
            {-18,-34,-20},
            {-18,-35,-22},
            {-18,-36,-24},
            {-25,-36,-29},
            {-30,-37,-31},
            {-30,-36,-33},
            {-37,38},
            {-34,39},
            {-35,40},
            {-26,-38},
            {-19,-39},
            {-21,-40},
            {-18,-34,2},
            {-18,-35,4},
            {-18,-36,6},
            {-25,-37},
            {-25,-35},
            {-30,-37,9},
            {-30,-34},
            {-30,-36,10},
            {-18,15},
            {-25,16},
            {-30,17},
            {-26,15},
            {-19,16},
            {-21,17},
            {26,-15,38},
            {19,-16,39},
            {21,-17,40},
            {18,-2,20},
            {18,-4,22},
            {18,-6,24},
            {25,29},
            {30,-9,31},
            {30,-10,33},
            {37,15,-38},
            {34,16,-39},
            {35,17,-40},
            {18,2,-20},
            {18,4,-22},
            {18,6,-24},
            {25,-27},
            {25,-28},
            {30,9,-31},
            {30,-32},
            {30,10,-33},
            {19,2,-20},
            {21,4,-22},
            {23,6,-24},
            {26,-27},
            {21,-28},
            {26,9,-31},
            {19,-32},
            {23,10,-33},
            {18,25,30},
            {37,34,35,36},
            {26,19,21,23},
            {-18,-25},
            {-18,-30},
            {-25,-18},
            {-25,-30},
            {-30,-18},
            {-30,-25},
            {-37,-34},
            {-37,-35},
            {-37,-36},
            {-34,-37},
            {-34,-35},
            {-34,-36},
            {-35,-37},
            {-35,-34},
            {-35,-36},
            {-36,-37},
            {-36,-34},
            {-36,-35},
            {-26,-19},
            {-26,-21},
            {-26,-23},
            {-19,-26},
            {-19,-21},
            {-19,-23},
            {-21,-26},
            {-21,-19},
            {-21,-23},
            {-23,-26},
            {-23,-19},
            {-23,-21},
            {-18,-37},
            {-25,-34},
            {-30,-35},
            {-18,-26},
            {-25,-19},
            {-30,-21},
            {-37,-26},
            {-34,-19},
            {-35,-21},
            {-36,-23},
            {-41,-42},
            {-44,-45},
            {-44,-43},
            {-46,-43},
            {-41,-48},
            {-41,-47,20},
            {-44,-48,29},
            {-46,-47,32},
            {-46,-48,33},
            {-41,38},
            {-44,39},
            {-46,40},
            {-45,38},
            {-42,40},
            {45,-38},
            {42,-40},
            {41,-20},
            {41,-22},
            {44,-27},
            {44,-29},
            {46,-32},
            {46,-33},
            {47,39},
            {41,24},
            {44,28},
            {46,31},
            {43,24},
            {42,28},
            {45,31},
            {41,44,46},
            {47,48},
            {45,42,43},
            {-41,-44},
            {-41,-46},
            {-44,-41},
            {-44,-46},
            {-46,-41},
            {-46,-44},
            {-47,-48},
            {-48,-47},
            {-45,-42},
            {-45,-43},
            {-42,-45},
            {-42,-43},
            {-43,-45},
            {-43,-42},
            {-44,-47},
            {-41,-45},
            {-46,-42},
            {-48,-43},
            {-4,-9},
            {-9,-4},
            {-2,-4},
            {-2,-6},
            {-4,-2},
            {-4,-6},
            {-6,-2},
            {-6,-4},
            {-9,-10},
            {-10,-9},
            {-2,-16},
            {-4,-17},
            {-9,-15},
            {-20,-27},
            {-22,-31},
            {-27,-20},
            {-28,-32},
            {-31,-22},
            {-32,-28},
            {-20,-32},
            {-22,-28},
            {-27,-31},
            {-28,-22},
            {-31,-27},
            {-32,-20},
            {-20,-22},
            {-20,-24},
            {-22,-20},
            {-22,-24},
            {-24,-20},
            {-24,-22},
            {-27,-28},
            {-27,-29},
            {-28,-27},
            {-28,-29},
            {-29,-27},
            {-29,-28},
            {-31,-32},
            {-31,-33},
            {-32,-31},
            {-32,-33},
            {-33,-31},
            {-33,-32},
            {-20,-39},
            {-22,-40},
            {-27,-38},
            {-28,-40},
            {-31,-38},
            {-32,-39},
        };
        Lbool[] expects = {T,F,F,F,T,T,F,F,F,T,T,F,F,F,T,T,T,F,F,F,F,F,F,T,F,T,F,F,T,T,T,F,F,F,F,T,F,F,T,T,F,T,F,T,F,F,F,T };
        solve(problem, true, expects);
    }
    
    @Test
    public void testGraphColoring() {
        int[][] problem = {
//    	p cnf 12 31
            {1, 2, 3},
            {-1, -2},
            {-1, -3},
            {-2, -3},
            {4, 5, 6},
            {-4, -5},
            {-4, -6},
            {-5, -6},
            {7, 8, 9},
            {-7, -8},
            {-7, -9},
            {-8, -9},
            {10, 11, 12},
            {-10, -11},
            {-10, -12},
            {-11, -12},
            {-1, -7},
            {-2, -8},
            {-3, -9},
            {-4, -10},
            {-5, -11},
            {-6, -12},
            {-4, -7},
            {-5, -8},
            {-6, -9},
            {-7, -10},
            {-8, -11},
            {-9, -12},
            {-1, -4},
            {-2, -5},
            {-3, -6},
        };
        Lbool[] expects = {F,F,T,F,T,F,T,F,F,F,F,T};
        solve(problem, true, expects);
    }
    
    @Test
    public void testUnsatisfiable() {
        int[][] problem = {
//    	p cnf 3 6
            {1},
            {2},
            {3},
            {-1, -2},
            {-2, -3},
            {-3, -1},
        };
        Lbool[] expects = null;
        solve(problem, false, expects);
    }
}

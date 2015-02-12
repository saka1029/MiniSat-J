# MiniSat-J
MiniSat-J is a porting MiniSat2.0 to Java8

## Run from command line
```
> java -jar minisatj-1.0.jar 8queens.cnf 8queens.out
2015-02-12T20:42:33.554 INFO ============================[ Problem Statistics ]=============================
2015-02-12T20:42:33.562 INFO |                                                                             |
2015-02-12T20:42:33.593 INFO |  Number of variables:            64                                         |
2015-02-12T20:42:33.594 INFO |  Number of clauses:             744                                         |
2015-02-12T20:42:33.597 INFO |  Parse time:                   0.05 sec                                     |
2015-02-12T20:42:33.597 INFO |                                                                             |
2015-02-12T20:42:33.598 INFO ============================[ Problem Statistics ]=============================
2015-02-12T20:42:33.599 INFO |                                                                             |
2015-02-12T20:42:33.600 INFO |  Number of variables:            64                                         |
2015-02-12T20:42:33.601 INFO |  Number of clauses:             744                                         |
2015-02-12T20:42:33.603 INFO ============================[ Search Statistics ]==============================
2015-02-12T20:42:33.604 INFO | Conflicts |          ORIGINAL         |          LEARNT          | Progress |
2015-02-12T20:42:33.605 INFO |           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
2015-02-12T20:42:33.605 INFO ===============================================================================
2015-02-12T20:42:33.614 INFO ===============================================================================
2015-02-12T20:42:33.615 INFO restarts              : 1
2015-02-12T20:42:33.616 INFO conflicts             : 27             (157 /sec)
2015-02-12T20:42:33.616 INFO decisions             : 52             (0.00 % random) (303 /sec)
2015-02-12T20:42:33.619 INFO propagations          : 412            (2397 /sec)
2015-02-12T20:42:33.620 INFO conflict literals     : 437            (1.58 % deleted)
2015-02-12T20:42:33.620 INFO Memory used           : 59.00 MB
2015-02-12T20:42:33.621 INFO CPU time              : 0.171875 sec
2015-02-12T20:42:33.621 INFO elapse time           : 0.157000 sec
2015-02-12T20:42:33.622 INFO
2015-02-12T20:42:33.623 INFO SATISFIABLE

```

## Run from Java application

```java
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
    Lbool [] expects = {Lbool.FALSE, Lbool.FALSE, Lbool.FALSE};
    if (result)
        for (int i = 0, size = expects.length; i < size; ++i)
            assertEquals(expects[i], solver.model.get(i));
}
```


# MiniSat-J
MiniSat-J is a porting MiniSat2.0 to Java8

## Run from command line
```
> java -jar jp.saka1029.minisatj-1.0.jar data\bw_large.x.cnf
============================[ Problem Statistics ]=============================
|                                                                             |
|  Number of variables:          6325                                         |
|  Number of clauses:          131974                                         |
|  Parse time:                   0.31 sec                                     |
|                                                                             |
============================[ Search Statistics ]==============================
| Conflicts |          ORIGINAL         |          LEARNT          | Progress |
|           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
===============================================================================
|       100 |    6325   131974   300443 |    48390      100     13 |  0.000 % |
|       250 |    5586   131974   300443 |    53229      244     12 | 11.684 % |
|       475 |    5354   111495   249871 |    58552      456     12 | 15.352 % |
|       812 |    5248   111495   249871 |    64407      792     11 | 17.028 % |
|      1318 |    5248   104736   234807 |    70848     1297     11 | 17.028 % |
===============================================================================
restarts              : 10
conflicts             : 1581           (955 /sec)
decisions             : 3756           (0.00 % random) (2268 /sec)
propagations          : 1129406        (681906 /sec)
conflict literals     : 18198          (23.96 % deleted)
Memory used           : 106.00 MB
CPU time              : 1.65625 sec
elapse time           : 1.79900 sec

SATISFIABLE
```

## Run from Java application

Here is a sample problem in DIMACS format.

```
p cnf 3 2
1 -3 0
2 3 -1 0
```

Solving this problem by Java using Minisat-J API.
 
```java
@Test
public void testSolveSimple2() {
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
    assertEquals(true, result);	// must be satisfiable
    Lbool [] expects = {Lbool.FALSE, Lbool.FALSE, Lbool.FALSE};
    if (result)
        for (int i = 0, size = expects.length; i < size; ++i)
            assertEquals(expects[i], solver.model.get(i));
}
```

or more simply

```java
@Test
public void testSolveSimple() {
        Lbool [] expects = {Lbool.FALSE, Lbool.FALSE, Lbool.FALSE};
        Solver solver = new Solver();
        solver.addClause(1, -3);
        solver.addClause(2, 3, -1);
        boolean result = solver.solve();
        assertEquals(true, result);
        if (result)
            for (int i = 0, size = expects.length; i < size; ++i)
                assertEquals(expects[i], solver.model.get(i));
}
```

## Find all solutions

If there are many solutions,
you can obtain all of them.

```java
int deny(Solver solver, int v) {
    return solver.model.get(v - 1) == Lbool.TRUE ? -v : v;
}

@Test
public void testFindAllSolutions() {
    Solver solver = new Solver();
    solver.addClause(1, 2);
    solver.addClause(-1, -2);
    solver.addClause(2, 3);
    solver.addClause(-2, -3);
    solver.addClause(3, 4);
    solver.addClause(-3, -4);
    solver.addClause(4, 1);
    solver.addClause(-4, -1);
    int numberOfSolutions = 0;
    while (solver.solve()) {
        ++numberOfSolutions;
        System.out.printf("solution %d : %s%n", numberOfSolutions, solver.model);
        // add a clause which deny found solution
        solver.addClause(deny(solver, 1), deny(solver, 2), deny(solver, 3), deny(solver, 4));
    }
    System.out.printf("number of solution = %d%n", numberOfSolutions);
    assertEquals(2, numberOfSolutions);
}
```
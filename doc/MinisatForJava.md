MiniSat 2.2.0 for Java
======================

Introduction
------------


"MiniSat 2.2.0 for Java" is a Java version of [Minisat 2.2.0](http://minisat.se/MiniSat.html).

MiniSat is a minimalistic, open-source
SAT ([satisfiability problem](http://en.wikipedia.org/wiki/Boolean_satisfiability_problem)) solver,
developed to help researchers and developers alike to get started on SAT. 

Requirements
------------

Java SE 8

But Java8 specific function 
(Lambda or Stream API) are not used.

No libraries are required.

Quick start
-----------


Here is a sample file (sample.cnf) in
[DIMACS-CNF format](http://www.domagoj-babic.com/uploads/ResearchProjects/Spear/dimacs-cnf.pdf).

```
c A sample .cnf file.
p cnf 3 2
1 -3 0
2 3 -1 0 
```

You can run this in command line.

```
> java  -cp ..\bin minisat.core.Main sample.cnf sample.out
2014-08-18T17:31:04.999 INFO ============================[ Problem Statistics ]=============================
2014-08-18T17:31:05.008 INFO |                                                                             |
2014-08-18T17:31:05.052 INFO |  Number of variables:             3                                         |
2014-08-18T17:31:05.053 INFO |  Number of clauses:               2                                         |
2014-08-18T17:31:05.055 INFO |  Parse time:                   0.02 sec                                     |
2014-08-18T17:31:05.056 INFO |                                                                             |
2014-08-18T17:31:05.057 INFO ============================[ Problem Statistics ]=============================
2014-08-18T17:31:05.057 INFO |                                                                             |
2014-08-18T17:31:05.058 INFO |  Number of variables:             3                                         |
2014-08-18T17:31:05.059 INFO |  Number of clauses:               2                                         |
2014-08-18T17:31:05.060 INFO ============================[ Search Statistics ]==============================
2014-08-18T17:31:05.061 INFO | Conflicts |          ORIGINAL         |          LEARNT          | Progress |
2014-08-18T17:31:05.062 INFO |           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
2014-08-18T17:31:05.063 INFO ===============================================================================
2014-08-18T17:31:05.063 INFO ===============================================================================
2014-08-18T17:31:05.064 INFO restarts              : 1
2014-08-18T17:31:05.065 INFO conflicts             : 0              (0 /sec)
2014-08-18T17:31:05.065 INFO decisions             : 3              (0.00 % random) (48 /sec)
2014-08-18T17:31:05.066 INFO propagations          : 3              (48 /sec)
2014-08-18T17:31:05.066 INFO conflict literals     : 0              ( NaN % deleted)
2014-08-18T17:31:05.067 INFO Memory used           : 61.00 MB
2014-08-18T17:31:05.067 INFO CPU time              : 0.0625000 sec
2014-08-18T17:31:05.068 INFO elapse time           : 0.311000 sec
2014-08-18T17:31:05.068 INFO
2014-08-18T17:31:05.069 INFO SATISFIABLE
```

The results are in sample.out file.

```
SAT
-1 -2 -3 0
```

There are various options.

```
> java  -cp ..\bin minisat.core.Main --help
USAGE: --help [options] <input-file> <result-output-file>

  where input may be in plain DIMACS.

CORE OPTIONS:

  -rnd-init, -no-rnd-init                 (default: off)
  -luby, -no-luby                         (default: on)

  -var-decay    = <double> ( 0.0 ..  1.0) (default: 0.950000)
  -cla-decay    = <double> ( 0.0 ..  1.0) (default: 0.999000)
  -rnd-freq     = <double> [ 0.0 ..  1.0] (default: 0.00000)
  -rnd-seed     = <double> ( 0.0 .. 1.8e+308) (default: 9.16483e+07)
  -rinc         = <double> ( 1.0 .. 1.8e+308) (default: 2.00000)
  -gc-frac      = <double> ( 0.0 .. 1.8e+308) (default: 0.200000)

  -ccmin-mode   = <int>    [   0 ..    2] (default: 2)
  -phase-saving = <int>    [   0 ..    2] (default: 2)
  -rfirst       = <int>    [   1 .. imax] (default: 100)

MAIN OPTIONS:

  -verb         = <int>    [   0 ..    2] (default: 1)

HELP OPTIONS:

  --help        Print help message.
  --help-verb   Print verbose help message.
```

You can also run this from Java program.
This sample is a JUnit code.


```Java
public class TestSolver {

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
        for (int i = 0, size = expects.length; i < size; ++i)
            assertEquals(expects[i], solver.model.get(i));
    }

    @Test
    public void testSimple() {
        int[][] problem = {
            {1, -3},
            {2, 3, -1},
        };
        Lbool [] expects = {Lbool.FALSE, Lbool.FALSE, Lbool.FALSE};
        solve(problem, true, expects);
    }
}
```

Calling sequence is

1. Create Solver instance.
2. Create VecLit instance for a clause.
3. Create Lit instance for a literal `Lit#valueOf(int var, boolean sign)`.
4. Push the Lit to the VecLit `VecLit(Lit literal)`.
5. Add VecLit to the Solver `Solver#addCaluse(VecLit clause)`.
6. Solve `Solver#solve()`.
   It returns `true` for satisfiable, `false` for unsatisfiable.
7. The results in `Solver#model`.


Performance
-----------

Approximately 6 to 8 times slower than MiniSat 2.2.0

- MiniSat for Java result
(java version "1.8.0_05"
Java(TM) SE Runtime Environment (build 1.8.0_05-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.5-b02, mixed mode))


```
> java  -cp ..\bin minisat.core.Main cis2.txt.out
2014-08-19T14:06:15.405 情報 ============================[ Problem Statistics ]=============================
2014-08-19T14:06:15.413 情報 |                                                                             |
2014-08-19T14:06:16.383 情報 |  Number of variables:         53982                                         |
2014-08-19T14:06:16.384 情報 |  Number of clauses:          330190                                         |
2014-08-19T14:06:16.386 情報 |  Parse time:                   0.78 sec                                     |
2014-08-19T14:06:16.387 情報 |                                                                             |
2014-08-19T14:06:16.464 情報 ============================[ Problem Statistics ]=============================
2014-08-19T14:06:16.466 情報 |                                                                             |
2014-08-19T14:06:16.466 情報 |  Number of variables:         53982                                         |
2014-08-19T14:06:16.467 情報 |  Number of clauses:          321085                                         |
2014-08-19T14:06:16.468 情報 ============================[ Search Statistics ]==============================
2014-08-19T14:06:16.469 情報 | Conflicts |          ORIGINAL         |          LEARNT          | Progress |
2014-08-19T14:06:16.470 情報 |           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
2014-08-19T14:06:16.470 情報 ===============================================================================
2014-08-19T14:06:16.658 情報 |       100 |   14252   321085   947533 |   117731       73      3 | 73.599 % |
2014-08-19T14:06:16.778 情報 |       250 |   14221   321085   947533 |   129504      192      3 | 73.656 % |
2014-08-19T14:06:16.849 情報 |       475 |   14123   321085   947533 |   142454      372      3 | 73.838 % |
2014-08-19T14:06:17.367 情報 |       812 |   14017   321085   947533 |   156700      674      4 | 74.034 % |
2014-08-19T14:06:17.426 情報 |      1318 |   13839   321085   947533 |   172370     1158      4 | 74.364 % |
2014-08-19T14:06:17.575 情報 |      2077 |   13574   321085   947533 |   189607     1844      4 | 74.855 % |
2014-08-19T14:06:18.134 情報 |      3216 |   13189   321085   947533 |   208567     2875      4 | 75.568 % |
2014-08-19T14:06:18.969 情報 |      4924 |   11912   123756   360588 |   229424     2674      5 | 77.933 % |
2014-08-19T14:06:19.485 情報 ===============================================================================
2014-08-19T14:06:19.497 情報 restarts              : 30
2014-08-19T14:06:19.498 情報 conflicts             : 6401           (1837 /sec)
2014-08-19T14:06:19.499 情報 decisions             : 79098          (0.00 % random) (22701 /sec)
2014-08-19T14:06:19.499 情報 propagations          : 2313270        (663898 /sec)
2014-08-19T14:06:19.499 情報 conflict literals     : 28777          (11.76 % deleted)
2014-08-19T14:06:19.500 情報 Memory used           : 144.00 MB
2014-08-19T14:06:19.500 情報 CPU time              : 3.48438 sec
2014-08-19T14:06:19.501 情報 elapse time           : 4.14600 sec
2014-08-19T14:06:19.501 情報
2014-08-19T14:06:19.501 情報 SATISFIABLE
```

- MiniSat 2.2.0 result
(Microsoft(R) 32-bit C/C++ Optimizing Compiler Version 15.00.30729.01 for 80x86))


```
> minisat.exe cis2.txt.out
============================[ Problem Statistics ]=============================
|                                                                             |
|  Number of variables:         53982                                         |
|  Number of clauses:          330190                                         |
|  Parse time:                   0.13 s                                       |
|                                                                             |
============================[ Search Statistics ]==============================
| Conflicts |          ORIGINAL         |          LEARNT          | Progress |
|           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
===============================================================================
|       100 |   14252   321085   947533 |   117731       73      3 | 73.599 % |
|       250 |   14221   321085   947533 |   129504      192      3 | 73.656 % |
|       475 |   14123   321085   947533 |   142454      372      3 | 73.838 % |
|       812 |   14017   321085   947533 |   156700      674      4 | 74.034 % |
|      1318 |   13839   321085   947533 |   172370     1158      4 | 74.364 % |
|      2077 |   13574   321085   947533 |   189607     1844      4 | 74.855 % |
|      3216 |   13189   321085   947533 |   208567     2875      4 | 75.568 % |
|      4924 |   11912   123756   360588 |   229424     2674      5 | 77.933 % |
===============================================================================
restarts              : 30
conflicts             : 6401           (11329 /sec)
decisions             : 79098          (0.00 % random) (139996 /sec)
propagations          : 2313270        (4094283 /sec)
conflict literals     : 28777          (11.76 % deleted)
elapse time           : 0.565 s

SATISFIABLE
```

License
-------

It is released under the MIT licence.

> MiniSatForJava Copyright (c) 2014, Sakamoto Osamu<br>
> MiniSat -- Copyright (c) 2003-2006, Niklas Een, Niklas Sorensson<br>
>            Copyright (c) 2007-2010  Niklas Sorensson
>            
> Permission is hereby granted, free of charge, to any person obtaining a
> copy of this software and associated documentation files (the
> "Software"), to deal in the Software without restriction, including
> without limitation the rights to use, copy, modify, merge, publish,
> distribute, sublicense, and/or sell copies of the Software, and to
> permit persons to whom the Software is furnished to do so, subject to
> the following conditions:
> 
> The above copyright notice and this permission notice shall be included
> in all copies or substantial portions of the Software.
> 
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
> OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
> MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
> NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
> LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
> OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
> WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
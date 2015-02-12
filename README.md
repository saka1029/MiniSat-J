# MiniSat-J
MiniSat-J is a porting MiniSat2.0 to Java

## Usage
Run from command line to solve 8-queen in DIMACS format file ```8queens.cnf```.
```
> java -jar ..\minisatj-1.0.jar 8queens.cnf 8queens.out
2015-02-12T20:42:33.554 情報 ============================[ Problem Statistics ]=============================
2015-02-12T20:42:33.562 情報 |                                                                             |
2015-02-12T20:42:33.593 情報 |  Number of variables:            64                                         |
2015-02-12T20:42:33.594 情報 |  Number of clauses:             744                                         |
2015-02-12T20:42:33.597 情報 |  Parse time:                   0.05 sec                                     |
2015-02-12T20:42:33.597 情報 |                                                                             |
2015-02-12T20:42:33.598 情報 ============================[ Problem Statistics ]=============================
2015-02-12T20:42:33.599 情報 |                                                                             |
2015-02-12T20:42:33.600 情報 |  Number of variables:            64                                         |
2015-02-12T20:42:33.601 情報 |  Number of clauses:             744                                         |
2015-02-12T20:42:33.603 情報 ============================[ Search Statistics ]==============================
2015-02-12T20:42:33.604 情報 | Conflicts |          ORIGINAL         |          LEARNT          | Progress |
2015-02-12T20:42:33.605 情報 |           |    Vars  Clauses Literals |    Limit  Clauses Lit/Cl |          |
2015-02-12T20:42:33.605 情報 ===============================================================================
2015-02-12T20:42:33.614 情報 ===============================================================================
2015-02-12T20:42:33.615 情報 restarts              : 1
2015-02-12T20:42:33.616 情報 conflicts             : 27             (157 /sec)
2015-02-12T20:42:33.616 情報 decisions             : 52             (0.00 % random) (303 /sec)
2015-02-12T20:42:33.619 情報 propagations          : 412            (2397 /sec)
2015-02-12T20:42:33.620 情報 conflict literals     : 437            (1.58 % deleted)
2015-02-12T20:42:33.620 情報 Memory used           : 59.00 MB
2015-02-12T20:42:33.621 情報 CPU time              : 0.171875 sec
2015-02-12T20:42:33.621 情報 elapse time           : 0.157000 sec
2015-02-12T20:42:33.622 情報
2015-02-12T20:42:33.623 情報 SATISFIABLE

```



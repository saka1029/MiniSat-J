package jp.saka1029.minisatj.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import jp.saka1029.minisatj.utils.Parser;

///****************************************************************************************[Dimacs.h]
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
//#ifndef Minisat_Dimacs_h
//#define Minisat_Dimacs_h
//
//#include <stdio.h>
//
//#include "utils/ParseUtils.h"
//#include "core/SolverTypes.h"
//
//namespace Minisat {
//
////=================================================================================================
//// DIMACS Parser:
public class Dimacs {
//
//template<class B, class Solver>
//static void readClause(B& in, Solver& S, vec<Lit>& lits) {
//    int     parsed_lit, var;
//    lits.clear();
//    for (;;){
//        parsed_lit = parseInt(in);
//        if (parsed_lit == 0) break;
//        var = abs(parsed_lit)-1;
//        while (var >= S.nVars()) S.newVar();
//        lits.push( (parsed_lit > 0) ? mkLit(var) : ~mkLit(var) );
//    }
//}
//
//
//template<class B, class Solver>
//static void parse_DIMACS_main(B& in, Solver& S) {
//    vec<Lit> lits;
//    int vars    = 0;
//    int clauses = 0;
//    int cnt     = 0;
//    for (;;){
//        skipWhitespace(in);
//        if (*in == EOF) break;
//        else if (*in == 'p'){
//            if (eagerMatch(in, "p cnf")){
//                vars    = parseInt(in);
//                clauses = parseInt(in);
//                // SATRACE'06 hack
//                // if (clauses > 4000000)
//                //     S.eliminate(true);
//            }else{
//                printf("PARSE ERROR! Unexpected char: %c\n", *in), exit(3);
//            }
//        } else if (*in == 'c' || *in == 'p')
//            skipLine(in);
//        else{
//            cnt++;
//            readClause(in, S, lits);
//            S.addClause_(lits); }
//    }
//    if (vars != S.nVars())
//        fprintf(stderr, "WARNING! DIMACS header mismatch: wrong number of variables.\n");
//    if (cnt  != clauses)
//        fprintf(stderr, "WARNING! DIMACS header mismatch: wrong number of clauses.\n");
//}
//template<class B, class Solver>
public static void parse_DIMACS_main(Reader reader, Solver S) throws IOException {
    Parser in = new Parser(reader);
    VecLit lits = new VecLit();
    int vars    = 0;
    int clauses = 0;
    int cnt     = 0;
    for (;;) {
    	String token = in.read();
    	if (token == null)
    	    break;
    	else if (token.equals("c"))
    	    in.skipLine();
    	else if (token.equals("p")) {
    	    token = in.read();
    	    if (!token.equals("cnf"))
               throw new IOException("PARSE ERROR! 'cnf' expected after 'p'%n");
    		vars = in.readInt();
    		clauses = in.readInt();
    	} else {
    		++cnt;
            lits.clear();
            for (;;){
                int parsed_lit = Integer.parseInt(token);
                if (parsed_lit == 0) break;
                int var = Math.abs(parsed_lit) - 1;
                while (var >= S.nVars()) S.newVar();
            	lits.push(Lit.valueOf(var, parsed_lit < 0));
            	token = in.read();
            }
    		S.addClause_(lits);
    	}
    }
    if (vars != S.nVars())
        throw new IOException("WARNING! DIMACS header mismatch: wrong number of variables.");
    if (cnt  != clauses)
        throw new IOException("WARNING! DIMACS header mismatch: wrong number of clauses.");
}
//
//// Inserts problem into solver.
////
//template<class Solver>
//static void parse_DIMACS(gzFile input_stream, Solver& S) {
//    StreamBuffer in(input_stream);
//    parse_DIMACS_main(in, S); }
public static void parse_DIMACS(Reader reader, Solver S) throws IOException {
    parse_DIMACS_main(new BufferedReader(reader), S); }
//
////=================================================================================================
//}
//
//#endif
}
package minisatj.utils;

import java.util.Arrays;
import java.util.Comparator;

import minisatj.mtl.Vec;

///***************************************************************************************[Options.h]
//Copyright (c) 2008-2010, Niklas Sorensson
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
//#ifndef Minisat_Options_h
//#define Minisat_Options_h
//
//#include <stdlib.h>
//#include <stdio.h>
//#include <math.h>
//#include <string.h>
//
//#include "mtl/IntTypes.h"
//#include "mtl/Vec.h"
//#include "utils/ParseUtils.h"
//
//namespace Minisat {
//
////==================================================================================================
//// Top-level option parse/help functions:
//
//
//extern void parseOptions     (int& argc, char** argv, bool strict = false);
//extern void printUsageAndExit(int  argc, char** argv, bool verbose = false);
//extern void setUsageHelp     (const char* str);
//extern void setHelpPrefixStr (const char* str);
//
//
////==================================================================================================
//// Options is an abstract class that gives the interface for all types options:
//
//
public abstract class Option {
//class Option
//{
// protected:
//    const char* name;
	protected String name;
//    const char* description;
	protected String description;
//    const char* category;
	protected String category;
//    const char* type_name;
	protected String type_name;
//
//    static vec<Option*>& getOptionList () { static vec<Option*> options; return options; }
	private static Vec<Option> options = new Vec<>();
	public static Vec<Option> getOptionList() { return options; }
//    static const char*&  getUsageString() { static const char* usage_str; return usage_str; }
	private static String usage_str;
	public static String getUsageString() { return usage_str; }
//    static const char*&  getHelpPrefixString() { static const char* help_prefix_str = ""; return help_prefix_str; }
	private static String help_prefix_str = "";
	public static String getHelpPrefixString() { return help_prefix_str; }
	/** Clear all registered options */
	public static void clear() { options.clear(); }
//
//    struct OptionLt {
//        bool operator()(const Option* x, const Option* y) {
//            int test1 = strcmp(x->category, y->category);
//            return test1 < 0 || test1 == 0 && strcmp(x->type_name, y->type_name) < 0;
//        }
//    };
	static class OptionComp implements Comparator<Option> {
		@Override
		public int compare(Option o1, Option o2) {
			int r = o1.category.compareTo(o2.category);
			if (r != 0) return r;
			return o1.type_name.compareTo(o2.type_name);
		}
	}
	private static final OptionComp COMP = new OptionComp();
//
//    Option(const char* name_, 
//           const char* desc_,
//           const char* cate_,
//           const char* type_) : 
//      name       (name_)
//    , description(desc_)
//    , category   (cate_)
//    , type_name  (type_)
//    { 
//        getOptionList().push(this); 
//    }
	public Option(String name, String description, String category, String type_name) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.type_name = type_name;
		getOptionList().push(this);
	}
//
// public:
//    virtual ~Option() {}
//
//    virtual bool parse             (const char* str)      = 0;
	public abstract boolean parse(String str);
//    virtual void help              (bool verbose = false) = 0;
	public abstract void help(boolean verbose);
	public void help() { help(false); }
	public abstract void setDefault();
//
//    friend  void parseOptions      (int& argc, char** argv, bool strict);
//    friend  void printUsageAndExit (int  argc, char** argv, bool verbose);
//    friend  void setUsageHelp      (const char* str);
//    friend  void setHelpPrefixStr  (const char* str);
//};

///**************************************************************************************[Options.cc]
//Copyright (c) 2008-2010, Niklas Sorensson
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
//#include "mtl/Sort.h"
//#include "utils/Options.h"
//#include "utils/ParseUtils.h"
//
//using namespace Minisat;
//
//void Minisat::parseOptions(int& argc, char** argv, bool strict)
//{
//    int i, j;
//    for (i = j = 1; i < argc; i++){
//        const char* str = argv[i];
//        if (match(str, "--") && match(str, Option::getHelpPrefixString()) && match(str, "help")){
//            if (*str == '\0')
//                printUsageAndExit(argc, argv);
//            else if (match(str, "-verb"))
//                printUsageAndExit(argc, argv, true);
//        } else {
//            bool parsed_ok = false;
//        
//            for (int k = 0; !parsed_ok && k < Option::getOptionList().size(); k++){
//                parsed_ok = Option::getOptionList()[k]->parse(argv[i]);
//
//                // fprintf(stderr, "checking %d: %s against flag <%s> (%s)\n", i, argv[i], Option::getOptionList()[k]->name, parsed_ok ? "ok" : "skip");
//            }
//
//            if (!parsed_ok)
//                if (strict && match(argv[i], "-"))
//                    fprintf(stderr, "ERROR! Unknown flag \"%s\". Use '--%shelp' for help.\n", argv[i], Option::getHelpPrefixString()), exit(1);
//                else
//                    argv[j++] = argv[i];
//        }
//    }
//
//    argc -= (i - j);
//}
	public static String[] parseOptions(String[] args, boolean strict) {
        Vec<Option> opts = getOptionList();
        for (int i = 0; i < opts.size(); ++i)
        	opts.get(i).setDefault();
	    int i, j;
	    for (i = j = 0; i < args.length; i++){
	        String str = args[i];
	        String prefix = "--" + getHelpPrefixString() + "help";
	        if (str.startsWith(prefix)){
	            if (str.length() == prefix.length())
	                printUsageAndExit(args, false);
	            else if (str.startsWith(prefix + "-verb"))
	                printUsageAndExit(args, true);
	        } else {
	            boolean parsed_ok = false;
	            for (int k = 0, size = opts.size(); !parsed_ok && k < size; k++){
	                parsed_ok = opts.get(k).parse(args[i]);
	                // fprintf(stderr, "checking %d: %s against flag <%s> (%s)\n", i, argv[i], Option::getOptionList()[k]->name, parsed_ok ? "ok" : "skip");
	            }
	            if (!parsed_ok)
	                if (strict && args[i].startsWith("-")) {
	                    String message = String.format(
	                    	"ERROR! Unknown flag \"%s\". Use '--%shelp' for help.",
	                    	args[i], getHelpPrefixString());
	                    eprintf("%s%n", message);
	                    throw new IllegalArgumentException(message);
	                } else
	                    args[j++] = args[i];
	        }
	    }
	    return Arrays.copyOf(args, args.length - i + j);
	}
//
//
//void Minisat::setUsageHelp      (const char* str){ Option::getUsageString() = str; }
	public static void setUsageHelp(String str) { usage_str = str; }
//void Minisat::setHelpPrefixStr  (const char* str){ Option::getHelpPrefixString() = str; }
	public static void setHelpPrefixStr(String str) {  help_prefix_str = str; }
//void Minisat::printUsageAndExit (int argc, char** argv, bool verbose)
//{
//    const char* usage = Option::getUsageString();
//    if (usage != NULL)
//        fprintf(stderr, usage, argv[0]);
//
//    sort(Option::getOptionList(), Option::OptionLt());
//
//    const char* prev_cat  = NULL;
//    const char* prev_type = NULL;
//
//    for (int i = 0; i < Option::getOptionList().size(); i++){
//        const char* cat  = Option::getOptionList()[i]->category;
//        const char* type = Option::getOptionList()[i]->type_name;
//
//        if (cat != prev_cat)
//            fprintf(stderr, "\n%s OPTIONS:\n\n", cat);
//        else if (type != prev_type)
//            fprintf(stderr, "\n");
//
//        Option::getOptionList()[i]->help(verbose);
//
//        prev_cat  = Option::getOptionList()[i]->category;
//        prev_type = Option::getOptionList()[i]->type_name;
//    }
//
//    fprintf(stderr, "\nHELP OPTIONS:\n\n");
//    fprintf(stderr, "  --%shelp        Print help message.\n", Option::getHelpPrefixString());
//    fprintf(stderr, "  --%shelp-verb   Print verbose help message.\n", Option::getHelpPrefixString());
//    fprintf(stderr, "\n");
//    exit(0);
//}
	public static void printUsageAndExit(String[] argv, boolean verbose) {
	    String usage = getUsageString();
	    if (usage != null)
	        eprintf(usage, argv[0]);
	
	    Vec<Option> opts = getOptionList();
	    opts.sort(COMP);
	
	    String prev_cat  = null;
	    String prev_type = null;
	
	    for (int i = 0, size = opts.size(); i < size; i++){
	    	Option opt = opts.get(i);
	        String cat  = opt.category;
	        String type = opt.type_name;
	        if (!cat.equals(prev_cat))
	            eprintf("%n%s OPTIONS:%n%n", cat);
	        else if (type != prev_type)
	            eprintf("%n");
	        opt.help(verbose);
	        prev_cat  = cat;
	        prev_type = type;
	    }
	
	    eprintf("%nHELP OPTIONS:%n%n");
	    eprintf("  --%shelp        Print help message.%n", getHelpPrefixString());
	    eprintf("  --%shelp-verb   Print verbose help message.%n", getHelpPrefixString());
	    eprintf("%n");
	    throw new IllegalArgumentException();
	}
	
	protected static void eprintf(String format, Object... args) {
		System.err.printf(format, args);
	}
}

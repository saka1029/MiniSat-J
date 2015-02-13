package jp.saka1029.minisatj.utils;


////==================================================================================================
//// Double options:
//
//
//class DoubleOption : public Option
public class DoubleOption extends Option {
//{
// protected:
//    DoubleRange range;
	private DoubleRange range;
//    double      value;
	private double defaultValue;
	private double value;
//
// public:
//    DoubleOption(const char* c, const char* n, const char* d, double def = double(), DoubleRange r = DoubleRange(-HUGE_VAL, false, HUGE_VAL, false))
//        : Option(n, d, c, "<double>"), range(r), value(def) {
//        // FIXME: set LC_NUMERIC to "C" to make sure that strtof/strtod parses decimal point correctly.
//    }
	public DoubleOption(String category, String name, String description, double def, DoubleRange r) {
		super(name, description, category, "<double>");
		this.defaultValue = def;
		this.range = r;
	}

	public DoubleOption(String category, String name, String description, double def) {
		this(category, name, description, def, new DoubleRange(Double.MIN_VALUE, false, Double.MAX_VALUE, false));
	}

	public DoubleOption(String category, String name, String description) {
		this(category, name, description, 0D);
	}
//
//    operator      double   (void) const { return value; }
	public double value() { return value; }
//    operator      double&  (void)       { return value; }
//    DoubleOption& operator=(double x)   { value = x; return *this; }
	public DoubleOption value(double x) { this.value = x; return this; }
	public void setDefault() { value = defaultValue; }
//
//    virtual bool parse(const char* str){
//        const char* span = str; 
//
//        if (!match(span, "-") || !match(span, name) || !match(span, "="))
//            return false;
//
//        char*  end;
//        double tmp = strtod(span, &end);
//
//        if (end == NULL) 
//            return false;
//        else if (tmp >= range.end && (!range.end_inclusive || tmp != range.end)){
//            fprintf(stderr, "ERROR! value <%s> is too large for option \"%s\".\n", span, name);
//            exit(1);
//        }else if (tmp <= range.begin && (!range.begin_inclusive || tmp != range.begin)){
//            fprintf(stderr, "ERROR! value <%s> is too small for option \"%s\".\n", span, name);
//            exit(1); }
//
//        value = tmp;
//        // fprintf(stderr, "READ VALUE: %g\n", value);
//
//        return true;
//    }
	@Override
	public boolean parse(String str) {
		String prefix = "-" + name + "=";
		if (!str.startsWith(prefix))
			return false;
		double tmp = 0d;
		try {
			tmp = Double.parseDouble(str.substring(prefix.length()));
		} catch (NumberFormatException e) {
			return false;
		}
        if (tmp >= range.end && (!range.end_inclusive || tmp != range.end)){
        	String message = String.format("ERROR! value <%s> is too large for option \"%s\".%n", str, name);
        	eprintf("%s%n", message);
        	throw new IllegalArgumentException(message);
        } else if (tmp <= range.begin && (!range.begin_inclusive || tmp != range.begin)){
        	String message = String.format("ERROR! value <%s> is too small for option \"%s\".%n", str, name);
        	eprintf("%s%n", message);
        	throw new IllegalArgumentException(message);
        }
		value = tmp;
		return true;
	}
//
//    virtual void help (bool verbose = false){
//        fprintf(stderr, "  -%-12s = %-8s %c%4.2g .. %4.2g%c (default: %g)\n", 
//                name, type_name, 
//                range.begin_inclusive ? '[' : '(', 
//                range.begin,
//                range.end,
//                range.end_inclusive ? ']' : ')', 
//                value);
//        if (verbose){
//            fprintf(stderr, "\n        %s\n", description);
//            fprintf(stderr, "\n");
//        }
//    }
	@Override
	public void help(boolean verbose) {
        eprintf("  -%-12s = %-8s %c%4.2g .. %4.2g%c (default: %g)%n", 
                name, type_name, 
                range.begin_inclusive ? '[' : '(', 
                range.begin,
                range.end,
                range.end_inclusive ? ']' : ')', 
                defaultValue);
        if (verbose){
            eprintf("%n        %s%n", description);
            eprintf("%n");
        }
	}
//};
//
//
}

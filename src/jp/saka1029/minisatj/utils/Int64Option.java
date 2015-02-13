package jp.saka1029.minisatj.utils;

//class Int64Option : public Option
public class Int64Option extends Option {
//{
// protected:
//    Int64Range range;
	protected Int64Range range;
//    int64_t  value;
	protected long defaultValue;
	protected long value;
//
// public:
//    Int64Option(const char* c, const char* n, const char* d, int64_t def = int64_t(), Int64Range r = Int64Range(INT64_MIN, INT64_MAX))
//        : Option(n, d, c, "<int64>"), range(r), value(def) {}
	public Int64Option(String category, String name, String description, long def, Int64Range r) {
		super(name, description, category, "<int64>");
		this.range = r;
		this.defaultValue = def;
	}

	public Int64Option(String category, String name, String description, long def) {
		this(category, name, description, def, new Int64Range(Long.MIN_VALUE, Long.MAX_VALUE));
	}

	public Int64Option(String category, String name, String description) {
		this(category, name, description, 0);
	}
// 
//    operator     int64_t   (void) const { return value; }
	public long value() { return value; }
//    operator     int64_t&  (void)       { return value; }
//    Int64Option& operator= (int64_t x)  { value = x; return *this; }
	public Int64Option value(long x) { this.value = x; return this; }
	public void setDefault() { value = defaultValue; }
//
//    virtual bool parse(const char* str){
//        const char* span = str; 
//
//        if (!match(span, "-") || !match(span, name) || !match(span, "="))
//            return false;
//
//        char*   end;
//        int64_t tmp = strtoll(span, &end, 10);
//
//        if (end == NULL) 
//            return false;
//        else if (tmp > range.end){
//            fprintf(stderr, "ERROR! value <%s> is too large for option \"%s\".\n", span, name);
//            exit(1);
//        }else if (tmp < range.begin){
//            fprintf(stderr, "ERROR! value <%s> is too small for option \"%s\".\n", span, name);
//            exit(1); }
//
//        value = tmp;
//
//        return true;
//    }
	@Override
    public boolean parse(String str) {
		String prefix = "-" + name + "=";
		if (!str.startsWith(prefix))
			return false;
		long tmp;
		try {
			tmp = Long.parseLong(str.substring(prefix.length()));
		} catch (NumberFormatException e) {
			return false;
		}
        if (tmp > range.end){
        	String message = String.format("ERROR! value <%s> is too large for option \"%s\".%n", str, name);
        	eprintf("%s%n", message);
        	throw new IllegalArgumentException(message);
        }else if (tmp < range.begin){
        	String message = String.format("ERROR! value <%s> is too small for option \"%s\".%n", str, name);
        	eprintf("%s%n", message);
        	throw new IllegalArgumentException(message);
        }
        value = tmp;
        return true;
    }
//
//    virtual void help (bool verbose = false){
//        fprintf(stderr, "  -%-12s = %-8s [", name, type_name);
//        if (range.begin == INT64_MIN)
//            fprintf(stderr, "imin");
//        else
//            fprintf(stderr, "%4"PRIi64, range.begin);
//
//        fprintf(stderr, " .. ");
//        if (range.end == INT64_MAX)
//            fprintf(stderr, "imax");
//        else
//            fprintf(stderr, "%4"PRIi64, range.end);
//
//        fprintf(stderr, "] (default: %"PRIi64")\n", value);
//        if (verbose){
//            fprintf(stderr, "\n        %s\n", description);
//            fprintf(stderr, "\n");
//        }
//    }
	@Override
	public void help (boolean verbose){
        eprintf("  -%-12s = %-8s [", name, type_name);
        if (range.begin == Long.MIN_VALUE)
            eprintf("imin");
        else
            eprintf("%4d", range.begin);

        eprintf(" .. ");
        if (range.end == Long.MAX_VALUE)
            eprintf("imax");
        else
            eprintf("%4d", range.end);

        eprintf("] (default: %d)%n", defaultValue);
        if (verbose){
            eprintf("%n        %s%n", description);
            eprintf("%n");
        }
    }
//};
}
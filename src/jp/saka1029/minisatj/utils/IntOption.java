package jp.saka1029.minisatj.utils;

//class IntOption : public Option
public class IntOption extends Option {
//{
// protected:
//    IntRange range;
	protected IntRange range;
//    int32_t  value;
	protected int defaultValue;
	protected int value;
//
// public:
//    IntOption(const char* c, const char* n, const char* d, int32_t def = int32_t(), IntRange r = IntRange(INT32_MIN, INT32_MAX))
//        : Option(n, d, c, "<int32>"), range(r), value(def) {}
	public IntOption(String category, String name, String description, int def, IntRange r) {
		super(name, description, category, "<int>");
		this.defaultValue = def;
		this.range = r;
	}

	public IntOption(String category, String name, String description, int def) {
		this(category, name, description, def, new IntRange(Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public IntOption(String category, String name, String description) {
		this(category, name, description, 0);
	}
// 
//    operator   int32_t   (void) const { return value; }
	public int value() { return value; }
//    operator   int32_t&  (void)       { return value; }
//    IntOption& operator= (int32_t x)  { value = x; return *this; }
	public IntOption value(int value) { this.value = value; return this; }
	public void setDefault() { value = defaultValue; }
//
//    virtual bool parse(const char* str){
//        const char* span = str; 
//
//        if (!match(span, "-") || !match(span, name) || !match(span, "="))
//            return false;
//
//        char*   end;
//        int32_t tmp = strtol(span, &end, 10);
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
		int tmp;
		try {
			tmp = Integer.parseInt(str.substring(prefix.length()));
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
//        if (range.begin == INT32_MIN)
//            fprintf(stderr, "imin");
//        else
//            fprintf(stderr, "%4d", range.begin);
//
//        fprintf(stderr, " .. ");
//        if (range.end == INT32_MAX)
//            fprintf(stderr, "imax");
//        else
//            fprintf(stderr, "%4d", range.end);
//
//        fprintf(stderr, "] (default: %d)\n", value);
//        if (verbose){
//            fprintf(stderr, "\n        %s\n", description);
//            fprintf(stderr, "\n");
//        }
//    }
	@Override
	public void help (boolean verbose){
        eprintf("  -%-12s = %-8s [", name, type_name);
        if (range.begin == Integer.MIN_VALUE)
            eprintf("imin");
        else
            eprintf("%4d", range.begin);

        eprintf(" .. ");
        if (range.end == Integer.MAX_VALUE)
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
//
}

package minisatj.utils;

//class BoolOption : public Option
public class BoolOption extends Option {
//{
//    bool value;
	protected boolean defaultValue;
	protected boolean value;
//
// public:
//    BoolOption(const char* c, const char* n, const char* d, bool v) 
//        : Option(n, d, c, "<bool>"), value(v) {}
	public BoolOption(String category, String name, String description, boolean v) {
		super(name, description, category, "<bool>");
		this.defaultValue = v;
	}
	
	public BoolOption(String category, String name, String description) {
		this(category, name, description, false);
	}
//
//    operator    bool     (void) const { return value; }
	public boolean value() { return value; }
//    operator    bool&    (void)       { return value; }
//    BoolOption& operator=(bool b)     { value = b; return *this; }
	public BoolOption value(boolean b) { this.value = b; return this; }
	public void setDefault() { value = defaultValue; }
//
//    virtual bool parse(const char* str){
//        const char* span = str; 
//        
//        if (match(span, "-")){
//            bool b = !match(span, "no-");
//
//            if (strcmp(span, name) == 0){
//                value = b;
//                return true; }
//        }
//
//        return false;
//    }
	@Override
	public boolean parse(String str) {
        if (str.equals("-no-" + name))
        	value = false;
        else if (str.equals("-" + name))
        	value = true;
        else
        	return false;
        return true;
	}
//
//    virtual void help (bool verbose = false){
//
//        fprintf(stderr, "  -%s, -no-%s", name, name);
//
//        for (uint32_t i = 0; i < 32 - strlen(name)*2; i++)
//            fprintf(stderr, " ");
//
//        fprintf(stderr, " ");
//        fprintf(stderr, "(default: %s)\n", value ? "on" : "off");
//        if (verbose){
//            fprintf(stderr, "\n        %s\n", description);
//            fprintf(stderr, "\n");
//        }
//    }
    @Override
    public void help (boolean verbose){
        eprintf("  -%s, -no-%s", name, name);
        for (int i = 0; i < 32 - name.length()*2; i++)
            eprintf(" ");
        eprintf(" ");
        eprintf("(default: %s)%n", defaultValue ? "on" : "off");
        if (verbose){
            eprintf("%n        %s%n", description);
            eprintf("%n");
        }
    }
//};
}

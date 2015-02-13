package jp.saka1029.minisatj.utils;

//class StringOption : public Option
public class StringOption extends Option {
//{
	private String defaultValue;
	private String value;
//    const char* value;
// public:
//    StringOption(const char* c, const char* n, const char* d, const char* def = NULL) 
//        : Option(n, d, c, "<string>"), value(def) {}
	public StringOption(String category, String name, String description, String def) {
		super(name, description, category, "<string>");
		this.defaultValue = def;
	}
	
	public StringOption(String category, String name, String description) {
		this(category, name, description, null);
	}
//
//    operator      const char*  (void) const     { return value; }
	public String value() { return this.value; }
//    operator      const char*& (void)           { return value; }
//    StringOption& operator=    (const char* x)  { value = x; return *this; }
	public StringOption value(String x) { value = x; return this; }
	public void setDefault() { value = defaultValue; }
//
//    virtual bool parse(const char* str){
//        const char* span = str; 
//
//        if (!match(span, "-") || !match(span, name) || !match(span, "="))
//            return false;
//
//        value = span;
//        return true;
//    }
    @Override
    public boolean parse(String str){
    	String prefix = "-" + name + "=";
    	if (!str.startsWith(prefix))
    		return false;
        value = str.substring(prefix.length());
        return true;
    }
//
//    virtual void help (bool verbose = false){
//        fprintf(stderr, "  -%-10s = %8s\n", name, type_name);
//        if (verbose){
//            fprintf(stderr, "\n        %s\n", description);
//            fprintf(stderr, "\n");
//        }
//    }    
    @Override
    public void help (boolean verbose){
        eprintf("  -%-12s = %8s", name, type_name);
        for (int i = 0; i < 24 - name.length() - type_name.length(); i++)
            eprintf(" ");
        eprintf(" ");
        eprintf("(default: %s)%n", defaultValue);
        if (verbose){
            eprintf("%n        %s%n", description);
            eprintf("%n");
        }
    }    
//};
//

}

package minisatj.utils;

//struct Int64Range {
public class Int64Range {
//    int64_t begin;
	public long begin;
//    int64_t end;
	public long end;
//    Int64Range(int64_t b, int64_t e) : begin(b), end(e) {}
	public Int64Range(long b, long e) { this.begin = b; this.end = e; }
//};
}

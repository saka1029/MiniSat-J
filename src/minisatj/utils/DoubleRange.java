package minisatj.utils;

//struct DoubleRange {
public class DoubleRange {
//    double begin;
	public double begin;
//    double end;
	public double end;
//    bool  begin_inclusive;
	public boolean begin_inclusive;
//    bool  end_inclusive;
	public boolean end_inclusive;
//    DoubleRange(double b, bool binc, double e, bool einc) : begin(b), end(e), begin_inclusive(binc), end_inclusive(einc) {}
	public DoubleRange(double begin, boolean begin_inclusive, double end, boolean end_inclusive) {
		this.begin = begin;
		this.begin_inclusive = begin_inclusive;
		this.end = end;
		this.end_inclusive = end_inclusive;
	}
//};
//

}

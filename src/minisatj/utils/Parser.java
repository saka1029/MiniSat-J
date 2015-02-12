package minisatj.utils;

import java.io.IOException;
import java.io.Reader;

public class Parser {

	private static final int EOF = -1;
	
	private Reader in;
	private int ch;
	
	public Parser(Reader in) throws IOException {
		this.in = in;
		this.ch = in.read();
	}
	
	public String read() throws IOException {
		if (ch == EOF) return null;
		while (Character.isWhitespace(ch))
		    ch = in.read();
		if (ch == EOF) return null;
		StringBuilder sb = new StringBuilder();
		do {
		    sb.append((char)ch);
		    ch = in.read();
		} while (ch != EOF && !Character.isWhitespace(ch));
		return sb.toString();
	}
	
	public int readInt() throws IOException {
        return Integer.parseInt(read());
	}
	
	public void skipLine() throws IOException {
	    while (ch != EOF && ch != '\r' && ch != '\n')
	        ch = in.read();
	    int first = ch;
	    ch = in.read();
	    if (first == '\r' && ch == '\n')
	        ch = in.read();
	}
}

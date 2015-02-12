package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import minisatj.utils.Parser;

import org.junit.Test;

public class TestParser {

    @Test
    public void testToken() throws IOException {
        String text =
            "p cnf 5 4\r\n" +
            "1 2 0 3\r\n" +
            "4 5 0\r\n" +
            "-2 0\r\n";
        Parser parser = new Parser(new StringReader(text));
        assertEquals("p", parser.read());
        assertEquals("cnf", parser.read());
        assertEquals("5", parser.read());
        assertEquals("4", parser.read());
        assertEquals("1", parser.read());
        assertEquals("2", parser.read());
        assertEquals("0", parser.read());
        assertEquals("3", parser.read());
        assertEquals("4", parser.read());
        assertEquals("5", parser.read());
        assertEquals("0", parser.read());
        assertEquals("-2", parser.read());
        assertEquals("0", parser.read());
        assertEquals(null, parser.read());
    }

    @Test
    public void testSkipLine() throws IOException {
        String text =
            "p cnf 5 4\r\n" +
            "1 2 0 3\r\n" +
            "4 5 0\r\n" +
            "-2 0\r\n";
        Parser parser = new Parser(new StringReader(text));
        String token = parser.read();
        while (token != null)
            if (token == "3") {
                parser.skipLine();
                token = parser.read();
                assertEquals("4", token);
            } else
                token = parser.read();
    }

}

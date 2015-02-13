package test;

import static org.junit.Assert.*;
import jp.saka1029.minisatj.utils.BoolOption;
import jp.saka1029.minisatj.utils.DoubleOption;
import jp.saka1029.minisatj.utils.DoubleRange;
import jp.saka1029.minisatj.utils.Int64Option;
import jp.saka1029.minisatj.utils.Int64Range;
import jp.saka1029.minisatj.utils.IntOption;
import jp.saka1029.minisatj.utils.IntRange;
import jp.saka1029.minisatj.utils.Option;
import jp.saka1029.minisatj.utils.StringOption;

import org.junit.Test;

public class TestOption {

	@Test
	public void testDefaultValue() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose");
		DoubleOption d = new DoubleOption("main", "d", "double value", 1.0, new DoubleRange(0.5, true, 1.5, true));
		Int64Option l = new Int64Option("main", "l", "int64 value", 3, new Int64Range(0, 100));
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 10));
		StringOption s = new StringOption("main", "s", "string value", "default");
		String[] args = {"infile", "outfile"};
		String[] rest = Option.parseOptions(args, true);
		assertEquals(false, v.value());
		assertEquals(1.0, d.value(), 0.000005);
		assertEquals(3, l.value());
		assertEquals(3, i.value());
		assertEquals("default", s.value());
		assertEquals(2, rest.length);
		assertEquals("infile", rest[0]);
		assertEquals("outfile", rest[1]);
	}

	@Test
	public void testSpecifyValue() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose");
		DoubleOption d = new DoubleOption("main", "d", "double value", 1.0, new DoubleRange(0.5, true, 1.5, true));
		Int64Option l = new Int64Option("main", "l", "int64 value", 3, new Int64Range(0, 100));
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 10));
		StringOption s = new StringOption("main", "s", "string value");
		String[] args = {"-v", "-d=1.5", "-l=8", "-i=4", "-s=test", "infile", "outfile"};
		String[] rest = Option.parseOptions(args, true);
		assertEquals(true, v.value());
		assertEquals(1.5, d.value(), 0.000005);
		assertEquals(8, l.value());
		assertEquals(4, i.value());
		assertEquals("test", s.value());
		assertEquals(2, rest.length);
		assertEquals("infile", rest[0]);
		assertEquals("outfile", rest[1]);
	}

	@Test
	public void testUnknownOption() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose");
		DoubleOption d = new DoubleOption("main", "d", "double value", 1.0, new DoubleRange(0.5, true, 1.5, true));
		Int64Option l = new Int64Option("main", "l", "int64 value", 3, new Int64Range(0, 100));
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 10));
		StringOption s = new StringOption("main", "s", "string value");
		String[] args = {"-s=test", "-k=100", "infile", "outfile"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testHelp() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose");
		DoubleOption d = new DoubleOption("main", "d", "double value", 1.0, new DoubleRange(0.5, true, 1.5, true));
		Int64Option l = new Int64Option("main", "l", "int64 value", 3, new Int64Range(0, 100));
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 10));
		StringOption s = new StringOption("main", "s", "string value", "default");
		String[] args = {"--help"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testHelpVerbose() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose");
		DoubleOption d = new DoubleOption("main", "d", "double value", 1.0, new DoubleRange(0.5, true, 1.5, true));
		Int64Option l = new Int64Option("main", "l", "int64 value", 3, new Int64Range(0, 100));
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 10));
		StringOption s = new StringOption("main", "s", "string value", "default");
		String[] args = {"--help-verb"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testDefaultTrue() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose", true);
		String[] args = {"INFILE"};
		String[] rest = Option.parseOptions(args, true);
		assertEquals(true, v.value());
		assertEquals(1, rest.length);
		assertEquals("INFILE", rest[0]);
	}

	@Test
	public void testSpecifyFalse() {
		Option.clear();
		BoolOption v = new BoolOption("main", "v", "verbose", true);
		String[] args = {"-no-v", "INFILE"};
		String[] rest = Option.parseOptions(args, true);
		assertEquals(false, v.value());
		assertEquals(1, rest.length);
		assertEquals("INFILE", rest[0]);
	}

	@Test
	public void testBoolOptionIllegalValue() {
		Option.clear();
		BoolOption i = new BoolOption("main", "i", "bool value", false);
		String[] args = {"-i=on", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("ERROR"));
		}
	}

	@Test
	public void testIntOptionIllegalValue() {
		Option.clear();
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 5));
		String[] args = {"-i=X", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("ERROR"));
		}
	}

	@Test
	public void testIntOptionTooSmall() {
		Option.clear();
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 5));
		String[] args = {"-i=-1", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("small"));
		}
	}

	@Test
	public void testIntOptionTooLarge() {
		Option.clear();
		IntOption i = new IntOption("main", "i", "integer value", 3, new IntRange(0, 5));
		String[] args = {"-i=6", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("large"));
		}
	}


	@Test
	public void testInt64OptionIllegalValue() {
		Option.clear();
		Int64Option i = new Int64Option("main", "i", "int64 value", 3, new Int64Range(0, 5));
		String[] args = {"-i=X", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("ERROR"));
		}
	}

	@Test
	public void testInt64OptionTooSmall() {
		Option.clear();
		Int64Option i = new Int64Option("main", "i", "int64 value", 3, new Int64Range(0, 5));
		String[] args = {"-i=-1", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("small"));
		}
	}

	@Test
	public void testInt64OptionTooLarge() {
		Option.clear();
		Int64Option i = new Int64Option("main", "i", "int64 value", 3, new Int64Range(0, 5));
		String[] args = {"-i=6", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("large"));
		}
	}

	@Test
	public void testDoubleOptionIllegalValue() {
		Option.clear();
		DoubleOption i = new DoubleOption("main", "i", "double value", 3, new DoubleRange(0, false, 5, false));
		String[] args = {"-i=X", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("ERROR"));
		}
	}

	@Test
	public void testDoubleOptionTooSmall() {
		Option.clear();
		DoubleOption i = new DoubleOption("main", "i", "double value", 3, new DoubleRange(0, false, 5, false));
		String[] args = {"-i=-1", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("small"));
		}
	}

	@Test
	public void testDoubleOptionTooLarge() {
		Option.clear();
		DoubleOption i = new DoubleOption("main", "i", "double value", 3, new DoubleRange(0, false, 5, false));
		String[] args = {"-i=6", "INFILE"};
		try {
			String[] rest = Option.parseOptions(args, true);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("large"));
		}
	}
}

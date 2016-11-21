package main;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class project0Test {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testDerivative0() {
		assertEquals("2", project0.derivative("!d/dx", "x+x"));
	}

	@Test
	public void testDerivative1() {
		assertEquals("1", project0.derivative("!d/dx", "x+y"));
	}

	@Test
	public void testDerivative2() {
		assertEquals("9", project0.derivative("!d/dx", "3*x*3-y*2"));
	}

	@Test
	public void testDerivative3() {
		assertEquals("90", project0.derivative("!d/dx", "3*x*3+y*2"));
	}
	@Test
	public void testDerivative4() {
		assertEquals("Error,no variable!", project0.derivative("!d/dx", "..."));
	}
	@Test
	public void testDerivative5() {
		assertEquals("4*x", project0.derivative("!d/dx", "3*x*x-x*x"));
	}
	@Test
	public void testDerivative6() {
		assertEquals("y", project0.derivative("!d/dx", "3*x*y-2*x*y"));
	}

	@Test
	public void testDerivative7() {
		assertEquals("0", project0.derivative("!d/dx", "1*x*y-1*x*y"));
	}
}

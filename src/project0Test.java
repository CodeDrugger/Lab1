import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class project0Test {
	private static project0 project0 = new project0();
	@Before
	public void setUp() throws Exception {
	}

	public void testSimplify3() {
		assertEquals("6", project0.simplify("x*x+2*y","!simplify x=2 y=1"));

	}
	@Test
	public void testSimplify4() {
		assertEquals("Error", project0.simplify("x*x+2*y","!simplify x=-1"));
	}
}

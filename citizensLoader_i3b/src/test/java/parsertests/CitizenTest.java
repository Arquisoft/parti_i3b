package parsertests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.parser.User;

public class CitizenTest {

	@Test
	public void testEquals() {
		User dummy = new User("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"123456789Z", "132456789", 1234);
		User dummy1 = new User("b", "c", "b@a.com", "10/10/2010", "a",
				"a", "123456789Z", "132456789", 1234);
		User dummy2 = new User("a", "b", "b@a.com", "10/10/2010", "a",
				"a", "3", "132456789", 1234);
		User dummy3 = null;
		User dummy4 = new User("a", "b", "b@a.com", "10/10/2010", "a",
				"a", null, "132456789", 1234);
		User dummy5 = new User("a", "b", "b@a.com", "10/10/2010", "a",
				"a", null, "132456789", 1234);
		Double doub = new Double(5.0);

		assertTrue(dummy.equals(dummy));
		assertTrue(dummy.equals(dummy1));
		assertFalse(dummy.equals(dummy2));
		assertFalse(dummy.equals(dummy3));
		assertFalse(dummy4.equals(dummy1));
		assertFalse(dummy.equals(doub));
		assertTrue(dummy4.equals(dummy5));
	}

}

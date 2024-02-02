package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BugReportLibraryTester {

	private Library emptyLib, smallLib, mediumLib;

	@BeforeEach
	void setUp() {
		emptyLib = new Library();

		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");
		smallLib.add(9781843190321L, "Adam Liu", "Motley Crue is the hypest band");
		smallLib.add(9781843190322L, "Donnie Yen", "IP Man Legend");
		smallLib.add(9781843190323L, "Robert Greene", "48 Laws of Power");
		smallLib.add(9781843190324L, "Tim Parks", "Italian Neighbors");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		// FILL IN -- extend this tester to consider a medium-size library
	}

	@Test
	public void testSmallLibraryCheckinByHolderOnCheckedOutBook() {
		//Create a person with duplicate Strings of the same name in different locations of memory.
		String origonalHolder = "Justin";
		String copyOfOrigonalHolder = new String("Justin");

		//Checkout a book to one of the Strings.
		assertTrue(smallLib.checkout(9781843190321L, origonalHolder, 11, 28, 2022));
		String personWhoCheckedOut = smallLib.lookup(9781843190321L);

		//Check that the same String person is holding the one book they checked out.
		assertEquals(copyOfOrigonalHolder, personWhoCheckedOut);

		//Test that the copy is able to checkin the book with the same name.
		assertTrue(smallLib.checkin(copyOfOrigonalHolder));

		//Check the books holder or null if it is not in the library.
		assertNull(smallLib.lookup(9781843190321L));
	}

	@Test
	public void testSmallLibraryLookupSingleItemCheckedOutCheckByISBNHolderAndDueDate() {
		//Create a person with duplicate Strings of the same name in different locations of memory.
		String origonalHolder = "Jamie";
		String copyOfOrigonalHolder = new String("Jamie");
		//Checkout a book to one of the Strings.
		assertTrue(smallLib.checkout(9781843190321L, origonalHolder, 11, 28, 2022));

		//assertTrue(smallLib.checkout(9781843190321L, copyOfOrigonalHolder, 11, 28, 2022));
		String personWhoCheckedOut = smallLib.lookup(9781843190321L);

		//Check that the same String person is holding the one book they checked out.
		assertEquals(copyOfOrigonalHolder, personWhoCheckedOut);
		assertEquals(copyOfOrigonalHolder, smallLib.lookup(9781843190321L));

		//Check that the size of the arrayList that is creaated in lookup is empty
		//due to the copy name.
		assertEquals(1, smallLib.lookup(copyOfOrigonalHolder).size());
	}

	@Test
	public void testSmallLibraryMultileHoldersCheckout() {
		//Create a person with duplicate Strings of the same name in different locations of memory.
		String origonalHolder = "Dude";
		String secondHolder = "Dan";
		String copyOfOrigonalHolder = new String("Dude");
		String copyOfSecondHolder = new String("Dan");

		//Checkout a book to one of the Strings.
		assertTrue(smallLib.checkout(9781843190321L, origonalHolder, 11, 28, 2022));
		assertTrue(smallLib.checkout(9781843190322L, secondHolder, 11, 28, 2022));

		assertFalse(smallLib.checkout(9781843190321L, copyOfOrigonalHolder, 11, 28, 2022));
		String personWhoCheckedOut = smallLib.lookup(9781843190321L);

		//Check that the same String person is holding the one book they checked out.
		assertEquals(copyOfOrigonalHolder, personWhoCheckedOut);

		//Test that the copy is able to checkin the book with the same name.
		assertTrue(smallLib.checkin(copyOfOrigonalHolder));
		assertTrue(smallLib.checkin(copyOfSecondHolder));
	}

	@Test
	public void testSmallLibraryMultileHoldersCheckOutSameBook() {
		//Create a person with duplicate Strings of the same name in different locations of memory.
		String origonalHolder = "Jorge";
		String secondHolder = "John";
		String copyOfOrigonalHolder = new String("Jorge");
		String copyOfSecondHolder = new String("John");
		String thirdHold = "Jane";
		String copyOfThirdHold = new String("Jane");

		//Checkout a book to one of the Strings.
		assertTrue(smallLib.checkout(9781843190321L, origonalHolder, 11, 28, 2022));
		assertTrue(smallLib.checkout(9781843190322L, secondHolder, 11, 28, 2022));
		assertFalse(smallLib.checkout(9781843190321L, copyOfOrigonalHolder, 11, 28, 2022));
		assertFalse(smallLib.checkout(9781843190322L, copyOfSecondHolder, 11, 28, 2022));
		assertTrue(smallLib.checkout(9781843190323L, thirdHold, 11, 28, 2022));
		assertFalse(smallLib.checkout(9781843190323L, copyOfThirdHold, 11, 28, 2022));

		//Check that the same String person is holding the one book they checked out.
		String personWhoCheckedOut = smallLib.lookup(9781843190321L);
		assertEquals(copyOfOrigonalHolder, personWhoCheckedOut);

		//Test that the copy is able to checkin the book with the same name.
		assertTrue(smallLib.checkin(copyOfOrigonalHolder));
		assertTrue(smallLib.checkin(copyOfSecondHolder));
		assertTrue(smallLib.checkin(copyOfThirdHold));
	}

	@Test
	public void testCheckoutTwoBooksForSamePatronProperDueDates() {
		//Make two strings that represent the same person.
		String origonalHolder = "Jackie";
		String copyHolder = new String("Jackie");
		//Check out two books for one of those strings
		assertTrue(smallLib.checkout(9781843190321L, origonalHolder, 11, 28, 2022));
		assertTrue(smallLib.checkout(9781843190322L, origonalHolder, 11, 28, 2022));
		String personWhoCheckedOut = smallLib.lookup(9781843190321L);
		assertEquals(copyHolder, personWhoCheckedOut);
		//Check if the copy string will find that person.
		assertEquals(2, smallLib.lookup(copyHolder).size());
	}	

}

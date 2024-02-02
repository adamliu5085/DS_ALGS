package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class BugReportLibraryGenericTester {
	
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	
	private LibraryGeneric<String> emptyLib, smallLib, mediumLib;
	
	//Setup copied from original assignment
	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");	
		
		
		emptyLib = new LibraryGeneric<String>();
		
		smallLib = new LibraryGeneric<String>();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new LibraryGeneric<String>();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");	
	}


	@Test
	public void testCheckoutTwoBooksForSamePatronProperDueDates() {
		//Creates two of the same person as different strings
		String holder = "Jack";
		String copyHolder = new String("Jack");
		//Checkout two books to one person
		assertTrue(smallLib.checkout(9780374292799L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780330351690L, holder, 11, 29, 2022));
		//Check that the strings are the same but not in memory.
		assertEquals(copyHolder, smallLib.lookup(9780330351690L));
		assertEquals(copyHolder, smallLib.lookup(9780374292799L));
		//Check if the copy person is holding the checked out books.
		assertEquals(2, smallLib.lookup(copyHolder).size());
	}
	
	@Test
	public void testLibraryCanHaveGenericHolders() {
		//Creates two of the same person as different strings for generic testing. 
		String holder = "Mason";
		String copyHolder = new String("Mason");
		//Checkout two books to one person
		assertTrue(smallLib.checkout(9780374292799L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780330351690L, holder, 11, 29, 2022));
		//Check that the strings are the same but not in memory.
		assertEquals(copyHolder, smallLib.lookup(9780330351690L));
		assertEquals(copyHolder, smallLib.lookup(9780374292799L));
		//Check if the copy person is holding the checked out books.
		assertEquals(2, smallLib.lookup(copyHolder).size());
	}
	
	@Test
	public void testCheckoutTwoBooksForSamePatronProperHolder() {
		//Creates two of the same person as different strings
		String holder = "Zach";
		String copyHolder = new String("Zach");
		//Checkout two books to one person
		assertTrue(smallLib.checkout(9780374292799L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780330351690L, holder, 11, 29, 2022));
		//Check that the strings are the same but not in memory.
		assertEquals(copyHolder, smallLib.lookup(9780330351690L));
		assertEquals(copyHolder, smallLib.lookup(9780374292799L));
		//Check if the copy person is holding the checked out books.
		assertEquals(2, smallLib.lookup(copyHolder).size());
	}
	
	@Test 
	public void testMultipleCheckInsAndOuts() {
		//Creates two of the same person as different strings
		String holder = "Zach";
		String copyHolder = new String("Zach");
		//Checkout two books to one person
		assertTrue(smallLib.checkout(9780374292799L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780330351690L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780446580342L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkin(9780446580342L));
		assertTrue(smallLib.checkin(9780330351690L));
		assertEquals(1, smallLib.lookup(copyHolder).size());
		assertTrue(smallLib.checkout(9780330351690L, holder, 11, 29, 2022));
		assertTrue(smallLib.checkout(9780446580342L, holder, 11, 29, 2022));
		assertEquals(3, smallLib.lookup(copyHolder).size());
		//Check that the strings are the same but not in memory.
		assertEquals(copyHolder, smallLib.lookup(9780330351690L));
		assertEquals(copyHolder, smallLib.lookup(9780374292799L));
	}
	
	@Test
	public void testOverdueListPastDueDateOnAllCheckedOutBooksSortedProperly() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 1, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		mediumLib.checkout(1000000000000L, "Timiri Blagg", 9, 4, 2022);
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 2, 2022).get(0).getHolder());
		assertEquals("Different Person", mediumLib.getOverdueList(9, 4, 2022).get(1).getHolder());
		assertEquals("Timiri Blagg", mediumLib.getOverdueList(9, 5, 2022).get(2).getHolder());
		assertEquals(3, mediumLib.getOverdueList(9, 10, 2022).size());
	}
	
	@Test
	public void testOverdueListPastDueDateOnAllCheckedOutBooks() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 1, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		mediumLib.checkout(1000000000000L, "Timiri Blagg", 9, 4, 2022);
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 2, 2022).get(0).getHolder());
		assertEquals("Different Person", mediumLib.getOverdueList(9, 4, 2022).get(1).getHolder());
		assertEquals("Timiri Blagg", mediumLib.getOverdueList(9, 6, 2022).get(2).getHolder());
		assertThrows(IndexOutOfBoundsException.class, () -> mediumLib.getOverdueList(10, 1, 2022).get(6).getHolder());
		assertEquals(3, mediumLib.getOverdueList(9, 11, 2022).size());
	}
	
	@Test
	public void testNotEveryCheckedOutBookIsOverdue() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 1, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		mediumLib.checkout(9781843190004L, "Timiri Blagg", 11, 30, 2022);
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 4, 2022).get(0).getHolder());
		assertEquals(2, mediumLib.getOverdueList(9, 2, 2022).size());
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 3, 2022).get(0).getHolder());
		assertThrows(IndexOutOfBoundsException.class, () -> mediumLib.getOverdueList(10, 1, 2022).get(2).getHolder());
		assertEquals(2, mediumLib.getOverdueList(10, 11, 2022).size());
	}
	
	@Test
	public void testOverdueListReturnsEmptyWithBooksCheckedOut() {
		String holder = "Reggie";
		mediumLib.checkout(9780374292799L, holder, 11, 29, 2022);
		mediumLib.checkout(9780330351690L, holder, 11, 29, 2022);
		mediumLib.checkout(9780446580342L, holder, 11, 29, 2022);
		ArrayList<LibraryBookGeneric<String>> list = mediumLib.getOverdueList(11, 30, 2023);
		assertEquals(0, list.size());
	}
}
	
package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryTest {

	private Library emptyLib, smallLib, mediumLib;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();
		
		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		// FILL IN -- extend this tester to consider a medium-size library
	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	
	
	//
	// New tests by us
	//
	
	@Test 
	public void testMediumLibraryLookUpLong(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		assertEquals("Jeffrey Jefferson", mediumLib.lookup(9781843190004L));
	}
	
	@Test 
	public void testMediumLibraryLookUpHolder(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		mediumLib.checkout(9781843190011L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound")));
		assertTrue(booksCheckedOut.contains(new Book(9781843190011L, "Moyra Caldecott", "The Eye of Callanish")));
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(1).getHolder());
	}
	
	@Test 
	public void testMediumLibraryLookUpHolderEmpty(){
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	
	@Test
	public void testCheckOut(){
		assertTrue(mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000));
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
	}
	
	@Test
	public void testCheckOutCheckedOut(){
		assertTrue(mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000));
		assertFalse(mediumLib.checkout(9781843190004L, "Different Person", 1, 1, 2000));
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
	}
	
	@Test
	public void testCheckOutNonExistent(){
		assertFalse(mediumLib.checkout(9999999999999L, "Jeffrey Jefferson", 1, 1, 2000));
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		}
	
	@Test
	public void testCheckInLong(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		LibraryBook book = booksCheckedOut.get(0);
		assertTrue(mediumLib.checkin(9781843190004L));
		booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNull(book.getHolder());
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testCheckInCheckedInLong(){
		assertFalse(mediumLib.checkin(9781843190004L));
	}
	
	@Test
	public void testCheckInNonExistentLong(){
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		assertFalse(mediumLib.checkin(9999999999999L));
	}
	
	@Test
	public void testCheckInString(){
			mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		LibraryBook book = booksCheckedOut.get(0);
		assertTrue(mediumLib.checkin("Jeffrey Jefferson"));
		booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNull(book.getHolder());
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testCheckInNonExistentString(){
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		assertFalse(mediumLib.checkin("Jeffrey Jefferson"));
	}
}
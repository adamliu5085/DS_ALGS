package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryGenericTester {
	
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	
	private LibraryGeneric<String> emptyLib, smallLib, mediumLib;
	
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
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	
	
	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = emptyLib.lookup("Jane Doe");
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
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = smallLib.lookup("Jane Doe");
		
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
	
	@Test 
	public void testMediumLibraryLookUpLong(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		assertEquals("Jeffrey Jefferson", mediumLib.lookup(9781843190004L));
	}
	
	@Test 
	public void testMediumLibraryLookUpHolder(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		mediumLib.checkout(9781843190011L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound")));
		assertTrue(booksCheckedOut.contains(new Book(9781843190011L, "Moyra Caldecott", "The Eye of Callanish")));
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(1).getHolder());
	}
	
	@Test 
	public void testMediumLibraryLookUpHolderEmpty(){
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	
	@Test
	public void testCheckOut(){
		assertTrue(mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000));
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
	}
	
	@Test
	public void testCheckOutCheckedOut(){
		assertTrue(mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000));
		assertFalse(mediumLib.checkout(9781843190004L, "Different Person", 1, 1, 2000));
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals("Jeffrey Jefferson", booksCheckedOut.get(0).getHolder());
	}
	
	@Test
	public void testCheckOutNonExistent(){
		assertFalse(mediumLib.checkout(9999999999999L, "Jeffrey Jefferson", 1, 1, 2000));
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		}
	
	@Test
	public void testCheckInLong(){
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		LibraryBookGeneric<String> book = booksCheckedOut.get(0);
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
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		assertFalse(mediumLib.checkin(9999999999999L));
	}
	
	@Test
	public void testCheckInString(){
			mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 1, 1, 2000);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		LibraryBookGeneric<String> book = booksCheckedOut.get(0);
		assertTrue(mediumLib.checkin("Jeffrey Jefferson"));
		booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNull(book.getHolder());
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testCheckInNonExistentString(){
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = mediumLib.lookup("Jeffrey Jefferson");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
		assertFalse(mediumLib.checkin("Jeffrey Jefferson"));
	}
	
	@Test
	public void testGetOverdueListSize() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 8, 2022);
		assertEquals(1, mediumLib.getOverdueList(9, 7, 2022).size());
	}
	
	@Test
	public void testGetOverdueListSizeEmpty() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 6, 2022);
		assertEquals(0, mediumLib.getOverdueList(9, 7, 2022).size());
	}
	
	@Test
	public void testGetOverdueListDueSameDay() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 7, 2022);
		assertEquals(0, mediumLib.getOverdueList(9, 7, 2022).size());
	}
		
	@Test
	public void testGetOverdueListSmallAllOverdue() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 2, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 1, 2022).get(0).getHolder());
		assertEquals(2, mediumLib.getOverdueList(9, 1, 2022).size());
	}
	
	@Test
	public void testGetOverdueListSmallAllOneOverdue() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 1, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		assertEquals("Different Person", mediumLib.getOverdueList(9, 1, 2022).get(0).getHolder());
		assertEquals(1, mediumLib.getOverdueList(9, 1, 2022).size());
	}
	
	@Test
	public void testGetOverdueListMediumAllOverdue() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 9, 2, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 9, 3, 2022);
		mediumLib.checkout(3000000000000L, "Different Person1", 9, 5, 2022);
		mediumLib.checkout(2000000000000L, "Different Person2", 9, 6, 2022);
		mediumLib.checkout(1000000000000L, "Different Person3", 9, 7, 2022);
		assertEquals("Jeffrey Jefferson", mediumLib.getOverdueList(9, 1, 2022).get(0).getHolder());
		assertEquals(5, mediumLib.getOverdueList(9, 1, 2022).size());
	}
	
	@Test
	public void testGetOverdueListMediumSomeOverDue() {
		mediumLib.checkout(9781843190004L, "Jeffrey Jefferson", 8, 1, 2022);
		mediumLib.checkout(9781843190011L, "Different Person", 8, 2, 2022);
		mediumLib.checkout(3000000000000L, "Different Person1", 9, 1, 2022);
		mediumLib.checkout(2000000000000L, "Different Person2", 9, 6, 2022);
		mediumLib.checkout(1000000000000L, "Different Person3", 9, 7, 2022);
		assertEquals("Different Person2", mediumLib.getOverdueList(9, 2, 2022).get(0).getHolder());
		assertEquals(2, mediumLib.getOverdueList(9, 2, 2022).size());
	}
	
	@Test
	public void testGetOrderedByTitle(){
		assertEquals("A man of science", mediumLib.getOrderedByTitle().get(0).getTitle());
		assertEquals("Ziggy Jig", mediumLib.getOrderedByTitle().get(26).getTitle());
		assertEquals(27, mediumLib.getOrderedByTitle().size());
	}
	
	@Test
	public void testGetOrderedByTitleEmptyLib(){
		assertEquals(0, emptyLib.getOrderedByTitle().size());
	}
	
	@Test
	public void testGetOrderedByTitleSmallLib(){
		assertEquals(3, smallLib.getOrderedByTitle().size());
		assertEquals("Into the Wild", smallLib.getOrderedByTitle().get(0).getTitle());
	}
	
}

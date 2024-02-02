package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a library book, in which the ISBN, author, and title cannot
 * change once the book is created.  Note that ISBNs are unique.
 * Books may be checked in and out, with a holder name and due date.
 * 
 * @author Erin Parker and Michael Wadley and Adam Liu
 * @version November 29, 2022.
 */
public class LibraryBook extends Book {
	
	private String bookHolder = null;
	
	private GregorianCalendar dueDate = null;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	public String getHolder(){
		return this.bookHolder;
	}
	
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	
	public void checkOut(String bookHolder, int month, int day, int year) {
		this.bookHolder = bookHolder;
		this.dueDate = new GregorianCalendar(year, month, day);
	}
	
	public void checkIn() {
		this.bookHolder = null;
		this.dueDate = null;
	}
	
	public boolean checkedOut() {
		if (this.bookHolder != null)
			return true;
		return false;
	}
	
}

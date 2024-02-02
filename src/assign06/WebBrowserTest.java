package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A comprehensive set of tests for the WebBrowser class.
 *
 * @author Michael Wadley & Adam Liu
 * @version 10/19/22
 */
public class WebBrowserTest {

    protected SinglyLinkedList<URL> list = new SinglyLinkedList<>();
    protected SinglyLinkedList<URL> emptyList = new SinglyLinkedList<>();
    protected WebBrowser browser = new WebBrowser();


   @BeforeEach
    public void setup() throws MalformedURLException {
        list = new SinglyLinkedList<URL>();
        for (int i = 50; i > 0; i--) {
         	URL link = new URL("https://" + i);
			list.insertFirst(link);
            browser.visit(link);
        }
    }

    @Test
    public void testWebBrowserHistoryConstructor() throws MalformedURLException {
       SinglyLinkedList<URL> history = new SinglyLinkedList<>();
       history.insertFirst(new URL("https://3"));
        history.insertFirst(new URL("https://2"));
        history.insertFirst(new URL("https://1"));
        history.insertFirst(new URL("https://0"));
        WebBrowser browser1 = new WebBrowser(history);
        SinglyLinkedList<URL> browser1History = browser1.history();
//        assertEquals("https://0", browser1History.get(0).toString());
//        assertEquals("https://1", )
    }

    @Test
    public void testBack() {
        assertEquals("https://2", browser.back().toString());
    }

	@Test
	public void testForward(){
       browser.back();
		assertEquals("https://1", browser.forward().toString());
    }

    @Test
    public void testEmptyBack(){
     	WebBrowser empty = new WebBrowser();
     	assertThrows(NoSuchElementException.class, () -> empty.back());
    }

    @Test
    public void testEmptyForward(){
     	WebBrowser empty = new WebBrowser();
     	assertThrows(NoSuchElementException.class, () -> empty.forward());
    }

    @Test
    public void testVisit() throws MalformedURLException {
        WebBrowser browser1 = new WebBrowser();
        browser1.visit(new URL("https://test"));
        assertEquals(browser1.currentURL.toString(), "https://test");

        assertEquals(browser.currentURL.toString(), "https://1");
    }

    @Test
    public void testHistory() throws MalformedURLException {
        SinglyLinkedList<URL> temp = browser.history();
        assertEquals("https://1", temp.get(0).toString());
        assertEquals("https://2", temp.get(1).toString());
        assertEquals("https://3", temp.get(2).toString());
        assertEquals("https://4", temp.get(3).toString());
        assertEquals("https://5", temp.get(4).toString());
        assertEquals("https://6", temp.get(5).toString());
        assertEquals("https://7", temp.get(6).toString());
        assertEquals("https://49", temp.get(48).toString());
        assertEquals("https://50", temp.get(49).toString());
    }

    @Test
    public void testHistorySmall() throws MalformedURLException {
        WebBrowser browser1 = new WebBrowser();
        browser1.visit(new URL("https://5"));
        browser1.visit(new URL("https://6"));
        SinglyLinkedList<URL> temp = browser1.history();
        assertEquals("https://6", temp.get(0).toString());
        assertEquals("https://5", temp.get(1).toString());
    }

    @Test
    public void testHistorySingleURL() throws MalformedURLException {
        WebBrowser browser1 = new WebBrowser();
        browser1.visit(new URL("https://6"));
        SinglyLinkedList<URL> temp = browser1.history();
        assertEquals("https://6", temp.get(0).toString());
    }

    @Test
    public void testHistoryDuplicates() throws MalformedURLException {
        WebBrowser browser1 = new WebBrowser();
        browser1.visit(new URL("https://1"));
        browser1.visit(new URL("https://5"));
        browser1.visit(new URL("https://6"));
        browser1.visit(new URL("https://5"));
        SinglyLinkedList<URL> temp = browser1.history();
        assertEquals("https://5", temp.get(0).toString());
        assertEquals("https://6", temp.get(1).toString());
        assertEquals("https://5", temp.get(2).toString());
        assertEquals("https://1", temp.get(3).toString());
    }

}
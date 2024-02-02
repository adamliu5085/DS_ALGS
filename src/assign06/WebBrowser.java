package assign06;

import java.net.URL;
import java.util.NoSuchElementException;


/**
 * A class that simulates how a web browser's forward & back buttons, and history work.
 *
 * @author Michael Wadley & Adam Liu
 * @version 10/20/22
 */
public class WebBrowser {

    private Stack<URL> back;

    private Stack<URL> forward;

    protected URL currentURL;

    /**
     * Creates a WebBrowser that has no previously visited pages, or pages to visit
     */
    public WebBrowser() {
        back = new ArrayStack<>();
        forward = new ArrayStack<>();
    }


    /**
     * Creates a WebBrowser with the preloaded history and current page given in the history parameter.
     * Only creates a history for the browser, no forward pages.
     *
     * @param history A SinglyLinkedList of URL's ordered from most recently visited, to least recently visited. The first element is the "current" page.
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        //This constructor creates a new web browser with a preloaded history of visited webpages, given as a list of URL Links to an external site. objects.
        //The first webpage in the list is the "current" page visited, and the remaining webpages are ordered from most recently visited to least recently visited.
        back = new ArrayStack<>();
        forward = new ArrayStack<>();
        LinkedListStack<URL> historyStack = new LinkedListStack<>();
        int historySize = history.size();
        for (int i = 0; i < historySize; i++) {
            historyStack.push(history.deleteFirst());
        }
        for (int i = 0; i < historySize; i++) {
            back.push(historyStack.pop());
        }
        currentURL = back.pop();
    }

    /**
     * Simulates visiting a webpage.
     * The current page (if any) goes to history, and the given webpage becomes the current page.
     *
     * @param webpage The URL to simulate visiting.
     */
    public void visit(URL webpage) {
        if (currentURL != null)
            back.push(currentURL);
        currentURL = webpage;
        forward.clear();
    }

    /**
     * Simulates pressing the back button in a web browser.
     * Saves the current page as the next to visit and goes back to the previously visited page.
     *
     * @return The previously visited page (before the current page).
     * @throws NoSuchElementException If there is no previously visited page to go back to.
     */
    public URL back() throws NoSuchElementException {
        if (back.size() == 0)
            throw new NoSuchElementException();
        forward.push(currentURL);
        currentURL = back.pop();
        return currentURL;
    }

    /**
     * Simulates pressing the forward button in a web browser.
     * Saves the current page as the previously visited page, and goes to the next page to visit.
     *
     * @return The next page to visit (after the current page).
     * @throws NoSuchElementException
     */
    public URL forward() throws NoSuchElementException {
        if (forward.size() == 0)
            throw new NoSuchElementException();
        back.push(currentURL);
        currentURL = forward.pop();
        return currentURL;
    }

    /**
     * Creates and returns a singlyLinkedList of URL's ordered from most recently visited, to least recently visited.
     * (The first element is the "current" page.)
     *
     * @return A singlyLinkedList of URL's ordered from most recently visited, to least recently visited. The first element is the "current" page
     */
    public SinglyLinkedList<URL> history() {
        ArrayStack<URL> historyStack = new ArrayStack<>();
        historyStack.push(currentURL);
        int backSize = back.size();
        for (int i = 0; i < backSize; i++) {
            historyStack.push(back.pop());
        }
        int size = historyStack.size();
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        for (int i = 0; i < size; i++) {
            URL url = historyStack.pop();
            history.insertFirst(url);
            back.push(url);
        }
        currentURL = back.pop();

        return history;
    }

}

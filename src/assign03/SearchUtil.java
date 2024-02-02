package assign03;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Daniel Kopta and Michael Wadley and Adam Liu
 * A utility class for searching, containing just one method (see below).
 *
 */
public class SearchUtil {

	
	/**
	 * 
	 * @param <T>	The type of elements contained in the list
	 * @param list	An ArrayList to search through. 
	 * 				This ArrayList is assumed to be sorted according 
	 * 				to the supplied comparator. This method has
	 * 				undefined behavior if the list is not sorted. 
	 * @param item	The item to search for
	 * @param cmp	Comparator for comparing Ts or a super class of T
	 * @return		true if the item exists in the list, false otherwise
	 */
	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp)
    {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) / 2);
            if (list.get(mid).equals(item))
                return true;
            if (cmp.compare(list.get(mid), item) == -1)
                lo = mid + 1;
            if (cmp.compare(list.get(mid), item) == 1)
                hi = mid - 1;
        }
        return false;
    }	
}
package ca.uwo.csd.cs2212.team18;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Implementation of the Searchable interface that searches a List of String objects. 
 * @author DianaGodoy
 *
 * Code adapted from {@link http://www.algosome.com/articles/java-jcombobox-autocomplete.html Source Code}
 */

public class StringSearchable implements Searchable<String,String>{

	private List<String> terms = new ArrayList<String>();

	/**
	 * Constructs a new object based upon the parameter terms. 
	 * @param terms The inventory of terms to search.
	 */
	public StringSearchable(ArrayList<String> terms){
		this.terms.addAll(terms);
	}

	/**
	 * Searches the entered string in the array of terms regardless
	 * of upper/lower case and returns the matching values. This is 
	 * used for displaying  drop down menu of only the valid cities
	 * according to characters typed.
	 * 
	 * @param value The String to search.
	 * @return founds The Collection of Strings matching the search value.
	 */
	public Collection<String> search(String value) {
		//value.toLowerCase();
		List<String> founds = new ArrayList<String>();
	
		for ( String s : terms ){
			if ( s.toLowerCase().indexOf(value.toLowerCase()) == 0 ){
				founds.add(s);
			}
		}
		return founds;
	}
}

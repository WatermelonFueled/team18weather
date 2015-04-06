package ca.uwo.csd.cs2212.team18;

import java.util.Collection;

/**
 * <h1> Searchable </h1>
 * Interface to search an underlying inventory of items and return a collection of found items. 
 * @param <E> The type of items to be found.
 * @param <V> The type of items to be searched
 * 
 * @author DianaGodoy
 *
 * Code adapted from {@link http://www.algosome.com/articles/java-jcombobox-autocomplete.html Source Code}
 */

public interface Searchable<E, V>{

	/**
	 * Searches an underlying inventory of items consisting of type E
	 * @param value A searchable value of type V
	 * @return A Collection of items of type E.
	 */
	public Collection<E> search(V value);
}
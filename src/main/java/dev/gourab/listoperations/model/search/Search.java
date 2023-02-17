package dev.gourab.listoperations.model.search;

import java.util.List;

/**
 * 
 * @author Gourab Bhattacharjee
 *
 */

@FunctionalInterface
public interface Search {
	
	Integer search(List<Integer> lists, Integer targetNumber);

}

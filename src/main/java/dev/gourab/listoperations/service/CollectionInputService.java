package dev.gourab.listoperations.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import dev.gourab.listoperations.model.CollectionInput;
import dev.gourab.listoperations.model.search.Search;
import dev.gourab.listoperations.repository.CollectionInputRepository;
import dev.gourab.listoperations.service.search.BinarySearch;
import dev.gourab.listoperations.service.search.BinarySearchSupport;

/**
 * 
 * @author Gourab Bhattacharjee
 *
 */

@Service
public class CollectionInputService {

	private final CollectionInputRepository collectionInputRepository;

	private final BinarySearch binarySearch;

	private final BinarySearchSupport binarySearchSupport;

	public CollectionInputService(CollectionInputRepository collectionInputRepository, BinarySearch binarySearch,
			BinarySearchSupport binarySearchSupport) {
		this.collectionInputRepository = collectionInputRepository;
		this.binarySearch = binarySearch;
		this.binarySearchSupport = binarySearchSupport;
	}

	/**
	 * @paramlists collection of numbers
	 * @param targetNumber the number to be searched
	 * @return the index of the element in the collection
	 */
	public Integer search(List<Integer> lists, Integer targetNumber) {
		return searchAPI(binarySearch::search, lists, targetNumber);
	}

	private Integer iterativeSearch(List<Integer> lists, Integer targetNumber) {
		return searchAPI((nums, target) -> {
			var size = nums.size();
			var start = 0;
			var end = size - 1;
			while (start <= end) {
				var middle = start + (end - start) / 2;
				var middleNo = nums.get(middle);
				if (middleNo == target) {
					return middle;
				} else if (middleNo < target) {
					start = middle + 1;
				} else {
					end = middle - 1;
				}
			}
			return -1;

		}, lists, targetNumber);
	}

	public String persistSearchResults(CollectionInput collectionInput) {
		if (collectionInput == null || collectionInput.getLists() == null || collectionInput.getLists().isEmpty()) {
			return "Search cannot be performed on an empty collection!";
		}
		collectionInput
				.setSearchResultIndex(iterativeSearch(collectionInput.getLists(), collectionInput.getSearchTarget()));
		collectionInputRepository.save(collectionInput);
		return "Collection input with id: " + collectionInput.getListId() + " got saved!";
	}

	public List<CollectionInput> getAllCollectionInputs() {
		Iterable<CollectionInput> collectionInputIterable = collectionInputRepository.findAll();
		if (collectionInputIterable != null) {
			return StreamSupport.stream(collectionInputIterable.spliterator(), false).toList();
		}
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param search       the Search API. Needs implementation of this interface.
	 * @param lists        collection of numbers
	 * @param targetNumber the number to be search
	 * @return
	 */
	public Integer searchAPI(Search search, List<Integer> lists, Integer targetNumber) {
		return search.search(lists, targetNumber);
	}

	public Integer searchInSortedAndRotatedCollection(CollectionInput collectionInput) {
		if (collectionInput == null || collectionInput.getLists() == null || collectionInput.getLists().isEmpty()) {
			return -1;
		}
		return searchAPI(binarySearchSupport::search, collectionInput.getLists(), collectionInput.getSearchTarget());
	}

}

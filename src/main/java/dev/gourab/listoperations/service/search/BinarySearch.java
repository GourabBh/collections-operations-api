package dev.gourab.listoperations.service.search;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BinarySearch {

	public Integer search(List<Integer> lists, Integer targetNumber) {
		if (lists.isEmpty()) {
			return -1;
		}
		var size = lists.size();
		var start = 0;
		var end = size - 1;
		return recursiveSearch(lists,start, end, targetNumber);

	}

	private Integer recursiveSearch(List<Integer> lists, int start, int end, Integer targetNumber) {
		if (start <= end) {
			var middle = start + (end - start) / 2;
			if (targetNumber.equals(lists.get(middle))) {
				return middle;
			} else if (targetNumber < lists.get(middle)) {
				return recursiveSearch(lists, start, middle - 1, targetNumber);
			} else {
				return recursiveSearch(lists, middle + 1, end, targetNumber);
			}
		}
		return -1;
	}

}

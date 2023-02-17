package dev.gourab.listoperations.service.search;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BinarySearchSupport {

	public Integer search(List<Integer> lists, Integer targetNumber) {
		if (lists == null || lists.isEmpty()) {
			return -1;
		}
		var size = lists.size();
		var start = 0;
		var end = size - 1;
		while (start <= end) {
			var middle = start + (end - start) / 2;
			var middleElement = lists.get(middle);
			if (middleElement == targetNumber) {
				return middle;
			} else if (lists.get(start) <= middleElement) {
				if (lists.get(start) <= targetNumber && targetNumber <= middleElement) {
					end = middle - 1;
				} else {
					start = middle + 1;
				}
			} else {
				if (lists.get(end) >= targetNumber && targetNumber >= middleElement) {
					start = middle + 1;
				} else {
					end = middle - 1;
				}
			}
		}
		return -1;
	}

}

package dev.gourab.listoperations.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gourab.listoperations.model.CollectionInput;
import dev.gourab.listoperations.service.CollectionInputService;

@RestController
@RequestMapping("/api/search")
public class CollectionInputController {

	private final CollectionInputService collectionInputService;

	private static final Logger LOG = Logger.getLogger(CollectionInputController.class.getName());

	public CollectionInputController(CollectionInputService collectionInputService) {
		this.collectionInputService = collectionInputService;
	}

	@PostMapping("/target")
	public ResponseEntity<String> searchTarget(@RequestBody CollectionInput collectionInput) {
		try {
			if (collectionInput == null || collectionInput.getLists() != null && collectionInput.getLists().isEmpty()
					|| collectionInput.getSearchTarget() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var persistResult = collectionInputService.persistSearchResults(collectionInput);
			LOG.log(Level.INFO, "Element search performed for collection input id: " + collectionInput.getListId());
			return new ResponseEntity<>(persistResult, HttpStatus.OK);
		} catch (Exception exception) {
			LOG.log(Level.SEVERE, "Exception occured while searching for " + collectionInput.getSearchTarget(),
					exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/collection")
	public ResponseEntity<List<CollectionInput>> getAllCollectionInputs() {
		try {
			return new ResponseEntity<>(collectionInputService.getAllCollectionInputs(), HttpStatus.OK);
		} catch (Exception exception) {
			LOG.log(Level.SEVERE, "Exception occured while getting collection lists ", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/rotated/target")
	public ResponseEntity<Integer> searchTargetInRotatedCollection(@RequestBody CollectionInput collectionInput) {
		try {
			if (collectionInput == null || collectionInput.getLists() != null && collectionInput.getLists().isEmpty()
					|| collectionInput.getSearchTarget() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var searchElementIndex = collectionInputService.searchInSortedAndRotatedCollection(collectionInput);
			LOG.log(Level.INFO, "Element search performed for collection input id: " + collectionInput.getListId());
			return new ResponseEntity<>(searchElementIndex, HttpStatus.OK);
		} catch (Exception exception) {
			LOG.log(Level.SEVERE, "Exception occured while searching for " + collectionInput.getSearchTarget(),
					exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

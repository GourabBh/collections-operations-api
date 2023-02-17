package dev.gourab.listoperations.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COLLECTION_INPUT")
public class CollectionInput implements Serializable {

	private static final long serialVersionUID = 3584571584988108761L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID listId;
	private List<Integer> lists;
	private Integer searchTarget;
	private Integer searchResultIndex;
}

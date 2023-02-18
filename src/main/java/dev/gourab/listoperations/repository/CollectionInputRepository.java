package dev.gourab.listoperations.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import dev.gourab.listoperations.model.CollectionInput;

public interface CollectionInputRepository extends ListCrudRepository<CollectionInput, UUID> {

}

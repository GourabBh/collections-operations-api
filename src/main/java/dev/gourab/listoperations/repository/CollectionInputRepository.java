package dev.gourab.listoperations.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import dev.gourab.listoperations.model.CollectionInput;

public interface CollectionInputRepository extends CrudRepository<CollectionInput, UUID> {

}

package com.vmware.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vmware.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

	Optional<Book> findByBookid(String id);
}

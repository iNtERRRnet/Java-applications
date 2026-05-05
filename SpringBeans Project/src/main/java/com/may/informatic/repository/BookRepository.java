package com.may.informatic.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.may.informatic.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
	List<Book> findByNameContainingIgnoreCase(String name);
	List<Book> findByAuthorContainingIgnoreCase(String author);
	List<Book> findByGenreContainingIgnoreCase(String genre);
	Optional<Book> findById(int id);
	List<Book> findByTakersId(int takersId);
}
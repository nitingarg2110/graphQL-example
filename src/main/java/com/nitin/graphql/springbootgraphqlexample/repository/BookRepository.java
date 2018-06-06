package com.nitin.graphql.springbootgraphqlexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitin.graphql.springbootgraphqlexample.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}

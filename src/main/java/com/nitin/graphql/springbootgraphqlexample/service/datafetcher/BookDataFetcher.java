package com.nitin.graphql.springbootgraphqlexample.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nitin.graphql.springbootgraphqlexample.model.Book;
import com.nitin.graphql.springbootgraphqlexample.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment environment) {
		String isn = environment.getArgument("id");
		return bookRepository.getOne(isn);
	}

}

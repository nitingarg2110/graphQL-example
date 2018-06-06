package com.nitin.graphql.springbootgraphqlexample.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.nitin.graphql.springbootgraphqlexample.model.Book;
import com.nitin.graphql.springbootgraphqlexample.repository.BookRepository;
import com.nitin.graphql.springbootgraphqlexample.service.datafetcher.AllBooksDataFetcher;
import com.nitin.graphql.springbootgraphqlexample.service.datafetcher.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:books.graphql")
	Resource resource;

	private GraphQL graphQL;

	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	private BookDataFetcher bookDataFetcher;

	@Autowired
	BookRepository bookRepository;

	@PostConstruct
	public void loadSchema() throws IOException {
		loadData();

		File schemafile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemafile);
		RuntimeWiring wiring = buildRunTimeWiring();
		GraphQLSchema graphQlSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(graphQlSchema).build();

	}

	private void loadData() {
		Stream.of(
				new Book("1", "Java1", "Tata", new String[] { "Nitin" }, "Nov 2017"), 
				new Book("2", "Java2", "Tata", new String[] { "NitinG" }, "Nov 2017"), 
				new Book("3", "Java3", "Tata", new String[] { "NitinK" }, "Nov 2017"),
				new Book("4", "Java4", "Tata", new String[] { "NitinG" }, "Nov 2017")
		).forEach(book -> bookRepository.save(book));

	}

	private RuntimeWiring buildRunTimeWiring() {

		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher).dataFetcher("book", bookDataFetcher)).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}

}
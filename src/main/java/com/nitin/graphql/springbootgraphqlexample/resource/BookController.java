package com.nitin.graphql.springbootgraphqlexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.graphql.springbootgraphqlexample.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/rest/books")
@RestController
public class BookController {

	@Autowired
	GraphQLService graphQLService;

	@PostMapping
	public ResponseEntity<Object> getBook(@RequestBody String query) {
		ExecutionResult result = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);

	}

}

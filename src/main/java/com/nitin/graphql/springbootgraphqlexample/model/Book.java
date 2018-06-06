package com.nitin.graphql.springbootgraphqlexample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Book {

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String isn, String title, String publisher, String[] authors, String publishedDate) {
		super();
		this.isn = isn;
		this.title = title;
		this.publisher = publisher;
		this.authors = authors;
		this.publishedDate = publishedDate;
	}

	@Id
	private String isn;
	private String title;
	private String publisher;
	private String[] authors;
	private String publishedDate;

}

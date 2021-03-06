package com.a2nine.accounts.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionTypes extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3418726870965527429L;

	private Long id;

	private String name;

	private String description;

	@JsonCreator
	public TransactionTypes(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("description") String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public TransactionTypes(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}
}

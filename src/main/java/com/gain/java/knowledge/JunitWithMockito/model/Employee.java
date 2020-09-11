package com.gain.java.knowledge.JunitWithMockito.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable{

	private static final long serialVersionUID = 4625037178092085622L;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("message")
	private String message;

	@Builder
	@JsonCreator
	public Employee(@JsonProperty("id") Integer id,
			@JsonProperty("name") String name,
			@JsonProperty("message") String message) {
		this.id = id;
		this.name = name;
		this.message = message;
	}

}

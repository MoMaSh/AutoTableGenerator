package com.mmsh.atg.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.mmsh.atg.annotations.ATGTable;

@Entity
@ATGTable()
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "fname")
	private String firstName;

	@Column(name = "lname")
	private String lastName;
	
	@Column
	private Boolean married;

	@Column
	private Integer age;
	
	@Column
	private Date dateAdded;
	
	@Column
	private Boolean hasJob;

	@OneToOne(fetch = FetchType.LAZY)
	private Address address;

}

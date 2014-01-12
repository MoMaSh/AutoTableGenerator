package com.mmsh.atg.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String streetName;
	
	@Column
	private String number;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
	private Person owner;
	
}

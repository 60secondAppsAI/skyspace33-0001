package com.skyspace33.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="passengers")
@Getter @Setter @NoArgsConstructor
public class Passenger {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="passenger_id")
	private Integer passengerId;
    
  	@Column(name="first_name")
	private String firstName;
    
  	@Column(name="last_name")
	private String lastName;
    
  	@Column(name="email")
	private String email;
    
  	@Column(name="phone_number")
	private String phoneNumber;
    
	




}

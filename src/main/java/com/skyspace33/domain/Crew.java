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
@Table(name="crews")
@Getter @Setter @NoArgsConstructor
public class Crew {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="crew_id")
	private Integer crewId;
    
  	@Column(name="role")
	private String role;
    
	




}

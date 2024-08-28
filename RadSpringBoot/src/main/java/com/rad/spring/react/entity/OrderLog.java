package com.rad.spring.react.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class OrderLog implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@JoinColumn
	@ManyToOne
	private Orders orders;
	@JoinColumn
	@ManyToOne
	private Users user;
	private Integer logType;
	private String logMessage;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	
}

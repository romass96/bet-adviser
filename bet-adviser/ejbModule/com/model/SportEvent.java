package com.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sport_events")
public class SportEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "result")
	private String result;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sport_type")
	private Sport sport;
	
	@Transient
	private EventDetails details;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public EventDetails getDetails() {
		return details;
	}

	public void setDetails(EventDetails details) {
		this.details = details;
	}
	
	
	
}

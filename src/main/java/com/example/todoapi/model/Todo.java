package com.example.todoapi.model;

import com.example.todoapi.user.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Tittel kan ikke være tom")
	@Size(max = 100, message = "Tittel kan være maks 100 tegn")
	private String tittel;

	@Size(max = 500, message = "Beskrivelse kan være maks 500 tegn")
	private String beskrivelse;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status kan ikke være null")
	private Status status;
	
	@ManyToOne @JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Todo() {
	}

	public Todo(String tittel, String beskrivelse, Status status) {
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTittel() {
		return tittel;
	}

	public void setTittel(String tittel) {
		this.tittel = tittel;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", tittel=" + tittel + ", beskrivelse=" + beskrivelse + ", status=" + status + "]";
	}

}

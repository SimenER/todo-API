package com.example.todoapi.dto;

import com.example.todoapi.model.Status;

public class TodoResponseDTO {
	
	private Long id;
	private String tittel;
	private String beskrivelse;
	private Status status;
	
	public TodoResponseDTO(Long id, String tittel, String beskrivelse, Status status) {
		this.id = id;
		this.tittel = tittel;
		this.beskrivelse = beskrivelse;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getTittel() {
		return tittel;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public Status getStatus() {
		return status;
	}

}

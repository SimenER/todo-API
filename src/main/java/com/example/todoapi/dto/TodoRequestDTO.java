package com.example.todoapi.dto;

import com.example.todoapi.model.Status;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TodoRequestDTO {
	
	@NotBlank(message = "Tittel kan ikke være tom")
	@Size(max = 100, message = "Tittel kan være maks 100 tegn")
	private String tittel;

	@Size(max = 500, message = "Beskrivelse kan være maks 500 tegn")
	private String beskrivelse;
	
	@NotNull(message = "Status kan ikke være null")
	private Status status;

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

}

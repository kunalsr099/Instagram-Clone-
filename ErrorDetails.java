package com.lpu.Momento;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ErrorDetails {
    private String message;
    private String description;
    private LocalDateTime timestamp;
	public ErrorDetails(String message, String description, LocalDateTime timestamp) {
		this.message = message;
		this.description = description;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
    
    
}

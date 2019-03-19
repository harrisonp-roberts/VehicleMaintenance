package com.maintenancetracker.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Transfer Object used to represent Maintenance applied to a car. Used to communicate with a frontend
 */
public class MaintenanceDTO {
	private String type;
	private String date;

	/**
	 * Constructs a MaintenanceDTO
	 *
	 * @param maintenanceType Type of maintenance
	 */
	public MaintenanceDTO(String maintenanceType, String date) {
		this.type = maintenanceType;
		this.date = date;
	}

	public MaintenanceDTO() {
		this.type = "";
		this.date = "";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

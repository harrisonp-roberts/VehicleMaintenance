package com.maintenancetracker.models;

import domain.Car;
import domain.ElectricCar;
import domain.GasCar;
import maintenance.Maintenance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Data Transfer Object representation of a Car used to communicate with a frontend
 */
public class CarDTO {
	private String name, desc, type, make, model, year, id;
	private int odometer;
	private ArrayList<MaintenanceDTO> maintenanceHistory;

	/**
	 * Constructs a car with no args
	 */
	public CarDTO() {
		this.name = "";
		this.desc = "";
		this.type = "";
		this.make = "";
		this.model = "";
		this.year = "";
		this.odometer = 0;
		this.maintenanceHistory = null;
	}

	/**
	 * Constructs a car with args
	 * @param name name of car
	 * @param desc description of car
	 * @param type type of car
	 * @param make make of car
	 * @param model model of car
	 * @param year year of car
	 * @param odometer mileage on the car
	 * @param id id of car
	 * @param maintenanceHistory maintenance history of the car
	 */
	public CarDTO(String name, String desc, String type, String make, String model, String year, int odometer, String id, ArrayList<MaintenanceDTO> maintenanceHistory) {
		this.name = name;
		this.desc = desc;
		this.type = type;
		this.make = make;
		this.model = model;
		this.year = year;
		this.odometer = odometer;
		this.id = id;
		this.maintenanceHistory = maintenanceHistory;
	}

	/**
	 * Constructs a car DTO from a Car
	 * @param car car to convert to DTO
	 */
	public CarDTO(Car car) {
		this.name = car.getName();
		this.desc = car.getDesc();
		if(car instanceof ElectricCar) {
			this.type = "electric";
		} else if (car instanceof GasCar) {
			this.type = "gascar";
		} else {
			this.type = "dieselcar";
		}
		this.make = car.getMake();
		this.model = car.getModel();
		this.year = car.getYear();
		this.odometer = car.getOdometer();
		this.id = car.getId();

		this.maintenanceHistory = new ArrayList<>();

		SimpleDateFormat yyyymmddFormat = new SimpleDateFormat("yyyy-mm-dd");

		if(car.getMaintenanceHistory() != null) {
			for(Maintenance m : car.getMaintenanceHistory()) {
				if(m.getDate() != null) {
					this.maintenanceHistory.add(new MaintenanceDTO(m.getMaintenanceType(), yyyymmddFormat.format(m.getDate())));
				} else {
					this.maintenanceHistory.add(new MaintenanceDTO(m.getMaintenanceType(), null));
				}
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<MaintenanceDTO> getMaintenanceHistory() {
		return maintenanceHistory;
	}

	public void setMaintenanceHistory(ArrayList<MaintenanceDTO> maintenanceHistory) {
		this.maintenanceHistory = maintenanceHistory;
	}
}

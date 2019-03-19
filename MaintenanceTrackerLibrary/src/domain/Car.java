package domain;

import exception.InvalidMaintenanceTypeException;
import maintenance.Maintenance;

import java.util.ArrayList;

/**
 * This class describes an abstract Car implementation
 */
public abstract class Car {
	private String name, make, model, year, desc, id;
	private int odometer;
	ArrayList<Maintenance> maintenanceHistory;
	ArrayList<String> validMaintenance;

	/**
	 * Creates a new instance of the Car class
	 *
	 * @param make     of the vehicle
	 * @param model    of the vehicle
	 * @param year     of the vehicle
	 * @param odometer reading on the vehicle
	 */
	public Car(String name, String desc, String make, String model, String year, int odometer) {
		this.name = name;
		this.desc = desc;
		this.make = make;
		this.model = model;
		this.year = year;
		this.odometer = odometer;
		maintenanceHistory = new ArrayList<>();
		validMaintenance = new ArrayList<>();
	}


	/**
	 * Adds a maintenance task to the history of the vehicle
	 *
	 * @param m Maintenance task to apply to the vehicle
	 * @throws InvalidMaintenanceTypeException if the maintenance type is invalid
	 */
	public void addMaintenance(Maintenance m) throws InvalidMaintenanceTypeException {
		if (!validMaintenance.contains(m.getMaintenanceType())) {
			throw new InvalidMaintenanceTypeException();
		}

		maintenanceHistory.add(m);

	}

	/**
	 * @param desc description to set on car
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return description of car
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return name of car
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name name to set on car
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return make of car
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make make to set on car
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return model of car
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model model to set on car
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return year of car
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year year to set on car
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return mileage of the car
	 */
	public int getOdometer() {
		return odometer;
	}

	/**
	 * @param odometer set mileage on the car
	 */
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	/**
	 * @return the cars maintenance history
	 */
	public ArrayList<Maintenance> getMaintenanceHistory() {
		return maintenanceHistory;
	}

	/**
	 * @param maintenanceHistory maintenance history to set on car
	 */
	public void setMaintenanceHistory(ArrayList<Maintenance> maintenanceHistory) throws InvalidMaintenanceTypeException {
		if(maintenanceHistory != null) {
			for (Maintenance mh : maintenanceHistory) {
				if (!validMaintenance.contains(mh.getMaintenanceType())) {
					throw new InvalidMaintenanceTypeException();
				}
			}
		}
		this.maintenanceHistory = maintenanceHistory;
	}

	/**
	 * @return ArrayList containing the maintenance that is allowed to be performed on the car
	 */
	public ArrayList<String> getValidMaintenance() {
		return validMaintenance;
	}

	/**
	 * @param validMaintenance ArrayList containing maintenance types that are allowed to be performed on the car
	 */
	public void setValidMaintenance(ArrayList<String> validMaintenance) {
		this.validMaintenance = validMaintenance;
	}

	/**
	 * @param id id to set on the car
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id of the car
	 */
	public String getId() {
		return id;
	}
}

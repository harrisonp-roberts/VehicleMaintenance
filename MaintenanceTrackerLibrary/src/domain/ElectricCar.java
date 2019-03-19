package domain;

import maintenance.ReplaceAirFilter;
import maintenance.TireRotation;

/**
 * Implementation of Car as an ElectricCar
 */
public class ElectricCar extends Car {

	/**
	 * Constructs instance of the ElectricCar
	 *
	 * @param make     make of the vehicle
	 * @param model    model of the vehicle
	 * @param year     year of manufacture
	 * @param odometer vehicle mileage
	 */
	public ElectricCar(String name, String desc, String make, String model, String year, int odometer) {
		super(name, desc, make, model, year, odometer);
		validMaintenance.add(TireRotation.MAINTENANCE_TYPE);
		validMaintenance.add(ReplaceAirFilter.MAINTENANCE_TYPE);
	}

}

package domain;

import maintenance.OilChange;
import maintenance.ReplaceAirFilter;
import maintenance.TireRotation;

/**
 * Implementation of Car as a gas Car
 */
public class GasCar extends Car {

	/**
	 * Initializes instance of GasCar
	 *
	 * @param make     make of the car
	 * @param model    model of the car
	 * @param year     year of manufacture
	 * @param odometer mileage
	 */
	public GasCar(String name, String desc, String make, String model, String year, int odometer) {
		super(name, desc, make, model, year, odometer);
		validMaintenance.add(OilChange.MAINTENANCE_TYPE);
		validMaintenance.add(TireRotation.MAINTENANCE_TYPE);
		validMaintenance.add(ReplaceAirFilter.MAINTENANCE_TYPE);
	}
}

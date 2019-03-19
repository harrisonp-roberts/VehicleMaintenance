package domain;

import maintenance.OilChange;
import maintenance.ReplaceAirFilter;
import maintenance.TireRotation;

public class DieselCar extends Car {

	/**
	 * Creates a new instance of the DieselCar class
	 *
	 * @param make     make of the vehicle
	 * @param model    model of the vehicle
	 * @param year     year of the vehicle
	 * @param odometer reading on the vehicle
	 */
	public DieselCar(String name, String desc, String make, String model, String year, int odometer) {
		super(name, desc, make, model, year, odometer);
		validMaintenance.add(OilChange.MAINTENANCE_TYPE);
		validMaintenance.add(TireRotation.MAINTENANCE_TYPE);
		validMaintenance.add(ReplaceAirFilter.MAINTENANCE_TYPE);
	}
}

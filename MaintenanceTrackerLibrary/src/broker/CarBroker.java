package broker;

import domain.Car;
import exception.InvalidVehicleTypeException;
import maintenance.Maintenance;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Brokers cars to the user of this library. Handles persisting new and updated cars, removing cars, and getting cars.
 * This broker follows the singleton pattern
 */
public class CarBroker {
	private static final CarBroker INSTANCE = new CarBroker();
	ArrayList<Car> cars;

	/**
	 * Gets the static instance of this broker
	 *
	 * @return instance of this broker
	 */
	public static CarBroker getInstance() {
		return INSTANCE;
	}

	/**
	 * Empties the cars from the broker
	 */
	public void reset() {
		cars.removeIf(Objects::nonNull);
	}

	/**
	 * Constructs an instance of the car broker
	 */
	private CarBroker() {
		cars = new ArrayList<>();
	}

	/**
	 * Adds or updates a car
	 *
	 * @param car car that is to be added or updated within broker
	 */
	public void persist(Car car) {

		if (car.getId() != null) {
			cars.removeIf(c -> c.getId().equals(car.getId()));
		} else {
			car.setId(UUID.randomUUID().toString());
		}
		cars.add(car);
	}

	/**
	 * Gets a list of all cars in broker
	 *
	 * @return an ArrayList containing all cars
	 */
	public ArrayList<Car> getAllCars() {
		return cars;
	}

	/**
	 * Gets a specified car
	 *
	 * @param car Car to retrieve
	 * @return Specified car
	 */
	public Car getCar(Car car) {
		if (car.getId() != null) {
			for (Car c : cars) {
				if (c.getId().equals(car.getId())) {
					return c;
				}
			}
		}

		return null;
	}

	/**
	 * Removes a car from the broker based on ID
	 *
	 * @param car car to remove from the broker
	 * @return true if car exists and is removed, else false
	 */
	public boolean removeCar(Car car) {
		if (car.getId() != null) {
			return cars.removeIf(c -> c.getId().equals(car.getId()));
		}
		return false;
	}
}

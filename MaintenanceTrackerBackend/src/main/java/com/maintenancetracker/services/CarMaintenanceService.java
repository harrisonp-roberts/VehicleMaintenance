package com.maintenancetracker.services;

import broker.CarBroker;
import com.maintenancetracker.models.CarDTO;
import com.maintenancetracker.models.MaintenanceDTO;
import domain.Car;
import domain.DieselCar;
import domain.ElectricCar;
import domain.GasCar;
import exception.InvalidMaintenanceTypeException;
import maintenance.Maintenance;
import maintenance.OilChange;
import maintenance.ReplaceAirFilter;
import maintenance.TireRotation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * REST Controller for the Maintenance Tracker Backend. Responds to all resource requests for the application
 */
@RequestMapping("/")
@RestController
public class CarMaintenanceService {
	CarBroker cb;

	/**
	 * Constructs the REST Controller
	 */
	CarMaintenanceService() {
		cb = CarBroker.getInstance();
	}

	/**
	 * Responds to put requests at /persists to save new or modified vehicles
	 *
	 * @param car car to be saved
	 * @return ResponseEntity indicating status of requested operation
	 */
	@RequestMapping(value = "/persist", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> persist(@RequestBody @Valid CarDTO car) {
		try {
			Car toSave = createCar(car);
			cb.persist(toSave);
		} catch (InvalidMaintenanceTypeException e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok("Car Persisted");
	}

	/**
	 * Responds to get requests at /getAll to get all cars in the broker
	 *
	 * @return ResponseEntity containing a list of all cars in broker
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ArrayList<CarDTO>> getAll() {
		ArrayList<CarDTO> toReturn = new ArrayList<>();

		cb.getAllCars().forEach(c -> {
			CarDTO toAdd = new CarDTO(c);
			toReturn.add(toAdd);
		});

		return ResponseEntity.ok(toReturn);
	}

	/**
	 * Reponds to delete requests at /remove to remove a specific car from the broker
	 * @param car car to remove from the broker
	 * @return ResponseEntity indicating the status of the requested operation
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeCar(@RequestBody @Valid CarDTO car) {
		try {
			if (car.getId() != null) {
				cb.removeCar(createCar(car));
				return ResponseEntity.ok("Car Removed");
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (InvalidMaintenanceTypeException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Creates a new car (including maintenance history) from a car DTO
	 *
	 * @param carDTO Car DTO to convert to a car
	 * @return Car
	 * @throws InvalidMaintenanceTypeException if the CarDTO contains invalid maintenance
	 */
	private Car createCar(CarDTO carDTO) throws InvalidMaintenanceTypeException {

		String type = carDTO.getType();
		Car toSave;

		//If car does not have an ID, assign it one
		if (carDTO.getId().equals("")) {
			carDTO.setId(UUID.randomUUID().toString());
		}

		//Determine the type of car
		if (type.equals("gas")) {
			toSave = new GasCar(carDTO.getName(), carDTO.getDesc(), carDTO.getMake(), carDTO.getModel(), carDTO.getYear(), carDTO.getOdometer());

		} else if (type.equals("electric")) {
			toSave = new ElectricCar(carDTO.getName(), carDTO.getDesc(), carDTO.getMake(), carDTO.getModel(), carDTO.getYear(), carDTO.getOdometer());
			toSave.setDesc(carDTO.getDesc());
		} else {
			toSave = new DieselCar(carDTO.getName(), carDTO.getDesc(), carDTO.getMake(), carDTO.getModel(), carDTO.getYear(), carDTO.getOdometer());
			toSave.setDesc(carDTO.getDesc());
		}

		toSave.setId(carDTO.getId());

		//Create Maintenance history for the car
		ArrayList<Maintenance> maintenanceHistory = createMaintenanceHistory(carDTO.getMaintenanceHistory());
		toSave.setMaintenanceHistory(maintenanceHistory);

		return toSave;
	}

	/**
	 * Creates Maintenance History based on maintenanceDTOS
	 * @param maintenanceDTOS ArrayList of maintenanceDTOs to convert to Maintenance
	 * @return ArrayList of Maintenance
	 */
	private ArrayList<Maintenance> createMaintenanceHistory(ArrayList<MaintenanceDTO> maintenanceDTOS) {
		ArrayList<Maintenance> maintenanceHistory = new ArrayList<>();

		if (maintenanceDTOS == null) {
			return null;
		}

		//determine the types of maintenance within the ArrayList
		for (MaintenanceDTO m : maintenanceDTOS) {

			Date date = null;

			if(m.getDate() != null) {
				try {
					date = new SimpleDateFormat("yyyy-mm-dd").parse(m.getDate());
				} catch (ParseException e) {
					System.err.println("Could not parse date");
				}
			}

			if (m.getType().equals("oilchange")) {
				maintenanceHistory.add(new OilChange(date));
			} else if (m.getType().equals("tirerotation")) {
				maintenanceHistory.add(new TireRotation(date));
			} else {
				maintenanceHistory.add(new ReplaceAirFilter(date));
			}
		}

		return maintenanceHistory;
	}

}

package broker;

import domain.*;
import exception.InvalidMaintenanceTypeException;
import maintenance.Maintenance;
import maintenance.OilChange;
import maintenance.ReplaceAirFilter;
import maintenance.TireRotation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarBrokerTest {
	private CarBroker vehicleBroker;
	private Car car1;
	private Car car2;
	private Car car3;
	private TireRotation tireRotation;
	private ReplaceAirFilter replaceAirFilter;
	private OilChange oilChange;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		vehicleBroker = CarBroker.getInstance();
		car1 = new ElectricCar("Car 1", "Electric Car", "Tesla", "Model 3", "2018", 10000);
		car2 = new GasCar("Car 2", "Gas Car", "Toyota", "Corolla", "2012", 100000);
		car3 = new DieselCar("Car 3", "Diesel Car", "Volkswagen", "Golf TDI", "2016", 1120);

		tireRotation = new TireRotation();
		replaceAirFilter = new ReplaceAirFilter();
		oilChange = new OilChange();
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() {
		ArrayList<Car> allVehicles = vehicleBroker.getAllCars();

		vehicleBroker.reset();
	}

	@Test
	void testPersistOneCar() {
		assertEquals(vehicleBroker.getAllCars().size(), 0);

		vehicleBroker.persist(car1);

		assertEquals(vehicleBroker.getAllCars().get(0), car1);
		assertEquals(vehicleBroker.getAllCars().size(), 1);
	}

	@Test
	void testPersistMultipleCars() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);

		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car2), car2);
		assertNull(vehicleBroker.getCar(car3));

	}

	@Test
	void testUpdateCar() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);

		car1.setDesc("test");

		assertEquals(vehicleBroker.getAllCars().size(), 2);
		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car1).getDesc(), "test");

	}

	@Test
	void testUpdateRemove() {
		vehicleBroker.persist(car1);
		car1.setDesc("test");
		vehicleBroker.persist(car1);

		assertTrue(vehicleBroker.removeCar(car1));

		assertEquals(vehicleBroker.getAllCars().size(), 0);
	}

	@Test
	void testUpdateMultipleCars() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);

		car1.setDesc("test1");
		car2.setDesc("test2");

		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car2), car2);

		assertEquals(vehicleBroker.getCar(car1).getDesc(), "test1");
		assertEquals(vehicleBroker.getCar(car2).getDesc(), "test2");

	}

	@Test
	void testUpdateRemoveMultipleCars() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);

		car1.setDesc("test1");
		car2.setDesc("test2");

		vehicleBroker.removeCar(car1);

		assertEquals(vehicleBroker.getAllCars().size(), 1);
		assertEquals(vehicleBroker.getCar(car2), car2);
		assertEquals(vehicleBroker.getCar(car1), null);
	}

	@Test
	void testGetCarAllExist() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);
		vehicleBroker.persist(car3);

		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car2), car2);
		assertEquals(vehicleBroker.getCar(car3), car3);

	}

	@Test
	void testGetCarRemoved() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);
		vehicleBroker.persist(car3);

		vehicleBroker.removeCar(car2);

		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car2), null);
		assertEquals(vehicleBroker.getCar(car3), car3);
	}

	@Test
	void testGetCarNotExist() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);

		assertEquals(vehicleBroker.getCar(car1), car1);
		assertEquals(vehicleBroker.getCar(car2), car2);
		assertEquals(vehicleBroker.getCar(car3), null);
	}

	@Test
	void testGetAllCarsOne() {
		vehicleBroker.persist(car1);

		ArrayList<Car> cars = vehicleBroker.getAllCars();

		assertEquals(cars.size(), 1);

		assertEquals(cars.get(0), car1);
	}

	@Test
	void testGetAllCarsMultiple() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);
		vehicleBroker.persist(car3);

		ArrayList<Car> cars = vehicleBroker.getAllCars();

		assertEquals(cars.size(), 3);
		assertTrue(cars.contains(car1));
		assertTrue(cars.contains(car2));
		assertTrue(cars.contains(car3));

	}

	@Test
	void testGetAllCarsRemoved() {
		assertEquals(0, vehicleBroker.getAllCars().size());

		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);
		vehicleBroker.persist(car3);

		vehicleBroker.removeCar(car3);

		ArrayList<Car> cars = vehicleBroker.getAllCars();

		assertEquals(cars.size(), 2);
		assertTrue(cars.contains(car1));
		assertTrue(cars.contains(car2));
		assertFalse(cars.contains(car3));

	}

	@Test
	void testGetAllCarsUpdatedRemoved() {
		vehicleBroker.persist(car1);
		vehicleBroker.persist(car2);
		vehicleBroker.persist(car3);

		car2.setDesc("test");
		vehicleBroker.persist(car2);

		vehicleBroker.removeCar(car2);

		ArrayList<Car> cars = vehicleBroker.getAllCars();

		assertEquals(cars.size(), 2);
		assertTrue(cars.contains(car1));
		assertTrue(cars.contains(car3));
		assertFalse(cars.contains(car2));
	}

	@Test
	void testAddMaintenanceElectric() {
		try {
			car1.addMaintenance(tireRotation);
			car1.addMaintenance(replaceAirFilter);

			ArrayList<Maintenance> car1History = car1.getMaintenanceHistory();

			assertEquals(car1History.size(), 2);

			assertEquals(car1History.get(0), tireRotation);
			assertEquals(car1History.get(1), replaceAirFilter);

		} catch (InvalidMaintenanceTypeException e) {
			fail("Failed");
		}

	}

	@Test
	void testAddInvalidMaintenanceElectric() {
		try {
			car1.addMaintenance(oilChange);
		} catch (InvalidMaintenanceTypeException e) {
			assertTrue(true);
		}
	}

	@Test
	void testAddMaintenanceGas() {
		try {
			car2.addMaintenance(tireRotation);

			car2.addMaintenance(replaceAirFilter);

			car2.addMaintenance(oilChange);

			ArrayList<Maintenance> car2History = car2.getMaintenanceHistory();

			assertEquals(car2History.size(), 3);

			assertEquals(car2History.get(0), tireRotation);
			assertEquals(car2History.get(1), replaceAirFilter);
			assertEquals(car2History.get(2), oilChange);


		} catch (InvalidMaintenanceTypeException e) {
			fail("Falied");
		}
	}

	@Test
	void testAddMaintenanceDiesel() {
		try {
			car3.addMaintenance(tireRotation);

			car3.addMaintenance(replaceAirFilter);

			car3.addMaintenance(oilChange);

			ArrayList<Maintenance> car3History = car3.getMaintenanceHistory();

			assertEquals(car3History.get(0), tireRotation);
			assertEquals(car3History.get(1), replaceAirFilter);
			assertEquals(car3History.get(2), oilChange);

			assertEquals(car3History.size(), 3);
		} catch (InvalidMaintenanceTypeException e) {
			fail("Failed");
		}

	}

}

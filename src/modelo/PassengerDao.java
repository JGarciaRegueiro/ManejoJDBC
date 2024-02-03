package modelo;

import java.util.List;

import entities.Passenger;

public interface PassengerDao {
	void addPassenger(Passenger passenger);
	void deletePassenger(int idPassenger);
	Passenger getPassenger(int idPassenger);
	List<Passenger> getPassengers();
	void addPassengerToCar(int idPassenger, int idCar);
	void deletePassengerFromCar(int idPassenger, int idCar);
	List<Passenger> getPassengersByCar(int idCar);
}

package modelo;

import java.sql.Connection;
import java.util.List;

import entities.Passenger;
import repository.PassengerRepository;

public class PassengerDaoImpl implements PassengerDao{
	
	private Connection connection;
	private PassengerRepository prepo;

	public PassengerDaoImpl(Connection connection) {
		this.setConnection(connection);
		this.prepo = new PassengerRepository(connection);
	}

	@Override
	public void addPassenger(Passenger passenger) {
		prepo.insertPassenger(passenger);
	}

	@Override
	public void deletePassenger(int idPassenger) {
		prepo.deletePassenger(idPassenger);
	}

	@Override
	public Passenger getPassenger(int idPassenger) {
		return prepo.getPassenger(idPassenger);
	}

	@Override
	public List<Passenger> getPassengers() {
		return prepo.getPassengers();
	}

	@Override
	public void addPassengerToCar(int idPassenger, int idCar) {
		prepo.addPassengerToCar(idCar, idPassenger);
	}

	@Override
	public List<Passenger> getPassengersByCar(int idCar) {
		return prepo.getPassengersByCar(idCar);
	}
	
	@Override
	public void deletePassengerFromCar(int idPassenger, int idCar) {
		prepo.deletePassengerFromCar(idPassenger, idCar);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}

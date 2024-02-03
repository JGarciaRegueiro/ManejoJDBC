package modelo;

import java.sql.Connection;
import java.util.List;

import entities.Car;
import repository.CarRepository;

public class CarDaoImpl implements CarDao{
	
	private Connection connection;
	private CarRepository crepo;
	
	public CarDaoImpl(Connection connection) {
		this.setConnection(connection);
		this.crepo = new CarRepository(connection);
	}
	
	@Override
	public void addCar(Car car) {
		crepo.insertCar(car);
	}

	@Override
	public void deleteCar(int idCar) {
		crepo.deleteCar(idCar);
	}

	@Override
	public Car getCar(int idCar) {
		return crepo.getCar(idCar);
	}

	@Override
	public void modifyCar(Car car) {
		crepo.updateCar(car);
	}

	@Override
	public List<Car> getCars() {
		return crepo.getCars();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

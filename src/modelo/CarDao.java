package modelo;

import java.util.List;

import entities.Car;

public interface CarDao {
	
	void addCar(Car car);
	void deleteCar(int idCar);
	Car getCar(int idCar);
	void modifyCar(Car car);
	List<Car> getCars();	

}

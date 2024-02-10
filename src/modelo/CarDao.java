package modelo;

import java.util.List;

import entities.Car;

/**
 * Interfaz que define las operaciones para acceder a la base de datos de coches.
 */

public interface CarDao {
	/**
     * Agrega un nuevo coche a la base de datos.
     * @param car:El coche a añadir.
     */
	void addCar(Car car);
	
	/**
     * Elimina un coche de la base de datos por su identificador.
     * @param idCar: El identificador del coche a eliminar.
     */
	void deleteCar(int idCar);
	
	/**
     * Obtiene un coche de la base de datos por su identificador.
     * @param idCar El identificador del coche a obtener.
     * @return El coche encontrado o null si no existe.
     */
	Car getCar(int idCar);
	
	/**
     * Modifica los datos de un coche en la base de datos.
     * @param car: El coche con los datos actualizados.
     */
	void modifyCar(Car car);
	
	/**
     * Obtiene una lista de todos los coches almacenados en la base de datos.
     * @return La lista de coches.
     */
	List<Car> getCars();	

}

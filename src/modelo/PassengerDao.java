package modelo;

import java.util.List;


/**
 * Interfaz que define las operaciones para acceder a la base de datos de pasajeros.
 */

import entities.Passenger;

public interface PassengerDao {
	/**
     * Agrega un nuevo pasajero a la base de datos.
     * @param passenger El pasajero a añadir.
     */
	void addPassenger(Passenger passenger);
	
	 /**
     * Elimina un pasajero de la base de datos por su identificador.
     * @param idPassenger El identificador del pasajero a eliminar.
     */
	void deletePassenger(int idPassenger);
	
	/**
     * Obtiene un pasajero de la base de datos por su identificador.
     * @param idPassenger El identificador del pasajero a obtener.
     * @return El pasajero encontrado o null si no existe.
     */
	Passenger getPassenger(int idPassenger);
	
	/**
     * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
     * @return La lista de pasajeros.
     */
	List<Passenger> getPassengers();
	
	/**
     * Asocia un pasajero a un coche en la base de datos.
     * @param idPassenger El identificador del pasajero.
     * @param idCar: El identificador del coche.
     */
	void addPassengerToCar(int idPassenger, int idCar);
	
	/**
     * Desasocia un pasajero de un coche en la base de datos.
     * @param idPassenger El identificador del pasajero.
     * @param idCar: El identificador del coche.
     */
	void deletePassengerFromCar(int idPassenger, int idCar);
	
	/**
     * Obtiene una lista de todos los pasajeros asociados a un coche en la base de datos.
     * @param idCar El identificador del coche.
     * @return La lista de pasajeros asociados al coche.
     */
	List<Passenger> getPassengersByCar(int idCar);
}

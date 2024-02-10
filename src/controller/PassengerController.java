package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Car;
import entities.Passenger;
import modelo.CarDaoImpl;
import modelo.PassengerDaoImpl;

/**
 * Controlador para gestionar las operaciones relacionadas con los pasajeros.
 */

public class PassengerController {
	
	private Connection connection;
	
	
	 /**
     * Constructor de PassengerController.
     * @param connection: La conexión a la base de datos.
     */
	
	public PassengerController(Connection connection) {
		this.connection = connection;
	}

	/**
     * Inicia el sistema de gestión de pasajeros.
     */
	
	public void init() {
		Scanner scanner = new Scanner(System.in);
		PassengerDaoImpl pdao = new PassengerDaoImpl(connection);
		CarDaoImpl cdao = new CarDaoImpl(connection);
		int option;
		
		do {
			System.out.println("---------------------------- X ----------------------------\n");
			System.out.println("1. Añadir nuevo pasajero");
			System.out.println("2. Borrar pasajero por id");
			System.out.println("3. Consultar pasajero por id");
			System.out.println("4. Listado de todos los pasajeros");
			System.out.println("5. Añadir pasajero a un coche");
			System.out.println("6. Eliminar pasajero de un coche");
			System.out.println("7. Listar todos los pasajeros de un coche");
			System.out.println("8. Volver atrás\n");
			System.out.println("---------------------------- X ----------------------------");
			intValid(scanner);
			
			option = scanner.nextInt();
			scanner.nextLine();
			
			switch (option) {
				case 1:
					System.out.println("Introduce el nombre:");
					String name = scanner.nextLine();
					System.out.println("Introduce la edad:");
					intValid(scanner);
					int age = scanner.nextInt();
					System.out.println("Introduce el peso:");
					doubleValid(scanner);
					float weight = (float) scanner.nextDouble();
		            Passenger passenger = new Passenger(name,age,weight);
					pdao.addPassenger(passenger);
					break;
					
				case 2:
					System.out.println("Introduce el id del pasajero a borrar:");
					intValid(scanner);
					int idPassengerDelete = scanner.nextInt();
					pdao.deletePassenger(idPassengerDelete);
					break;
					
				case 3:
					System.out.println("Introduce el id del pasajero a consultar:");
					intValid(scanner);
					int idPassenger = scanner.nextInt();
					if (pdao.getPassenger(idPassenger) != null){
						System.out.println(pdao.getPassenger(idPassenger));
					} else {
						System.err.println("No existe ningún pasajero con ese id");
					}
					break;
					
				case 4:
					System.out.println("Aquí tienes el listado de pasajeros:");
					List<Passenger> listPassengers = pdao.getPassengers();
					for (Passenger passengerItem : listPassengers) {
						System.out.println(passengerItem);
					}					
					break;
					
				case 5:
					
					System.out.println("Introduce el id del pasajero:");
					intValid(scanner);
					int idPassengerToCar = scanner.nextInt();
					if(pdao.getPassenger(idPassengerToCar) != null) {
						System.out.println("Aquí tienes el listado de coches disponibles:");
						List<Car> listCars = cdao.getCars();
						for (Car carItem : listCars) {
							System.out.println(carItem);
						}
						System.out.println("Introduce el id del coche a asignar:");
						intValid(scanner);
						int idCar = scanner.nextInt();
						if(cdao.getCar(idCar) != null) {			
							boolean pAtCar = false;
							List<Passenger> listPassengersByCar = pdao.getPassengersByCar(idCar);
							for (Passenger passengerItem : listPassengersByCar) {								
								if(idPassengerToCar == passengerItem.getId()) {
									pAtCar = true;
								}
							}
							if(!pAtCar)
								pdao.addPassengerToCar(idPassengerToCar, idCar);
							else 
								System.err.println("El pasajero ya se encuentra en el coche");
						}else {
							System.err.println("No existe ningún coche con ese ID");
						}
					} else {
						System.err.println("No existe ningún pasajero con ese ID");
					}
					break;
					
				case 6:
					System.out.println("Introduce el id del pasajero:");
					intValid(scanner);
					int idPassengerDeleteFromCar = scanner.nextInt();
					if(pdao.getPassenger(idPassengerDeleteFromCar) != null) {
						System.out.println("Introduce el id del coche:");
						intValid(scanner);
						int idCar = scanner.nextInt();
						if(cdao.getCar(idCar) != null) {
							pdao.deletePassengerFromCar(idPassengerDeleteFromCar, idCar);
						}else {
							System.err.println("No existe ningún coche con ese ID");
						}
					} else {
						System.err.println("No existe ningún pasajero con ese ID");
					}
					break;
					
				case 7:
					System.out.println("Introduce el id del coche:");
					intValid(scanner);
					int idCar = scanner.nextInt();
					List<Passenger> listPassengersByCar = pdao.getPassengersByCar(idCar);
					
						for (Passenger passengerItem : listPassengersByCar) {
							System.out.println(passengerItem);
						}
					
					break;
					
				case 8:
					System.out.println("---------------------- Fin gestión pasajeros ----------------------");
					break;
					
				default:
					System.err.println("Introduce un valor entre 1 y 8");
			}
			
		} while (option != 8);
	}
	
	/**
     * Valida si la entrada es un número entero.
     * @param scanner: Objeto Scanner para la entrada.
     */
	
	private static void intValid(Scanner scanner) {
		while (!scanner.hasNextInt()) {
            System.err.println("¡Error! Ingresa un número entero válido");
            scanner.next();
		}
	}
	/**
     * Valida si la entrada es un número decimal.
     * @param scanner:Objeto Scanner para la entrada.
     */
	
	private static void doubleValid(Scanner scanner) {
		while (!scanner.hasNextDouble()) {
            System.err.println("¡Error! Ingresa un número decimal válido usando comas");
            scanner.next();
		}
	}
	
}

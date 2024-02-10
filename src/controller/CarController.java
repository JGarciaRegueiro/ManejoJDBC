package controller;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import entities.Car;
import modelo.CarDaoImpl;

public class CarController {
	
	private Connection connection;
	
	 /**
     * Construye un objeto CarController con la conexión a la base de datos dada.
     * 
     * @param connection: La conexión a la base de datos que se utilizará.
     */
	public CarController(Connection connection) {
		this.connection = connection;
	}
	
	/**
     * Inicia el sistema de gestión de coches.
     */

	public void init() {
		
		Scanner scanner = new Scanner(System.in);
		CarDaoImpl cdao = new CarDaoImpl(connection);
		int option;
		
		do {
			System.out.println("---------------------------- X ----------------------------\n");
			System.out.println("1. Añadir nuevo coche");
			System.out.println("2. Borrar coche por id");
			System.out.println("3. Consultar coche por id");
			System.out.println("4. Modificar coche por id");
			System.out.println("5. Listado de todos los coches");
			System.out.println("6. Gestión de pasajeros");
			System.out.println("7. Terminar el programa\n");
			System.out.println("---------------------------- X ----------------------------");
			
			intValid(scanner);
			
			option = scanner.nextInt();
			scanner.nextLine();
			
			switch (option) {
				case 1:
					System.out.println("Introduce la marca:");
					String brand = stringValid(scanner);
					System.out.println("Introduce el modelo:");
					String model = stringValid(scanner);
					System.out.println("Introduce el año del coche:");
					intValid(scanner);
					int year = scanner.nextInt();
					System.out.println("Introduce los kilómetros del coche:");
					intValid(scanner);
					int km = scanner.nextInt();
		            Car car = new Car(brand,model,year,km);
		            cdao.addCar(car);
					break;
					
				case 2:
					System.out.println("Introduce el id del coche a borrar:");
					intValid(scanner);
					int idCarDelete = scanner.nextInt();
					cdao.deleteCar(idCarDelete);
					break;
					
				case 3:
					System.out.println("Introduce el id del coche a consultar:");
					intValid(scanner);
					int idCar = scanner.nextInt();
					if(cdao.getCar(idCar) != null){
						System.out.println(cdao.getCar(idCar));
					} else {
						System.err.println("No existe ningún coche con ese id");
					}
					break;
					
				case 4:
					System.out.println("Introduce el id del coche a modificar:");
					intValid(scanner);
					int idCarModify = scanner.nextInt();
					if (cdao.getCar(idCarModify) != null) {
						scanner.nextLine();
						System.out.println("Introduce la marca:");
						String brandUpdate = stringValid(scanner);
						System.out.println("Introduce el modelo:");
						String modelUpdate = stringValid(scanner);
						System.out.println("Introduce el año del coche:");
						intValid(scanner);
						int yearUpdate = scanner.nextInt();
						System.out.println("Introduce los kilómetros del coche:");
						intValid(scanner);
						int kmUpdate = scanner.nextInt();
			            Car carUpdate = new Car(brandUpdate,modelUpdate,yearUpdate,kmUpdate);
			            carUpdate.setId(idCarModify);
			            cdao.modifyCar(carUpdate);
					} else {
						System.err.println("El ID introducido no corresponde a ningún coche");
						break;
					}
					break;
					
				case 5:
					System.out.println("Aquí tienes el listado de coches:");
					List<Car> listCars = cdao.getCars();
					for (Car carItem : listCars) {
						System.out.println(carItem);
					}
					break;
					
				case 6:
					System.out.println("Redirigiendo...");
					PassengerController passengerController = new PassengerController(connection);
					passengerController.init();
					break;
					
				case 7:
					System.out.println("---------------------- Fin de programa ----------------------");
					break;
					
				default:
					System.err.println("Introduce un valor entre 1 y 7");
			}
			
		} while (option != 7);
		
		scanner.close();
	}
	
	/**
     * Valida si la entrada es un número entero.
     * 
     * @param scanner: Objeto Scanner para la entrada.
     */
	
	private void intValid(Scanner scanner) {
		while (!scanner.hasNextInt()) {
            System.err.println("¡Error! Ingresa un número entero válido");
            scanner.next();
		}
	}
	
	/**
     * Valida si la entrada es una cadena no vacía.
     * 
     * @param scanner: Objeto Scanner para la entrada.
     * @return La cadena validada no vacía.
     */
	
	private String stringValid(Scanner scanner) {
		String input;
        do {
            input = scanner.nextLine();

            if (input.isEmpty()) {
                System.err.println("¡Error! Este valor no puede estar vacío");
                System.out.println("Introduce un valor: ");
            }
        } while (input.isEmpty());
        return input;
	}
	
}

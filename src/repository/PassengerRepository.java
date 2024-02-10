package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Passenger;

/**
 * Repositorio que maneja las operaciones de base de datos relacionadas con los pasajeros.
 */
public class PassengerRepository {
	
	private Connection connection;
	
	/**
     * Constructor que establece la conexión para el repositorio de pasajeros.
     * @param connection: La conexión a la base de datos.
     */
	public PassengerRepository(Connection connection) {
		this.connection = connection;
	}
	
	/**
     * Inserta un nuevo pasajero en la base de datos.
     * @param passenger:El pasajero a insertar.
     */
	
	public void insertPassenger(Passenger passenger) {
        String sql = "INSERT INTO PASSENGERS (NAME, AGE, WEIGHT) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passenger.getName());
            statement.setInt(2, passenger.getAge());
            statement.setFloat(3, passenger.getWeight());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Se ha añdido el pasajero: " + passenger + " correctamente.");
            } else {
                System.err.println("Ha sucedido un error y no se ha podido añadir el pasajero.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Obtiene un pasajero de la base de datos por su identificador.
     * @param idPassenger: El identificador del pasajero a obtener.
     * @return El pasajero encontrado o null si no existe.
     */
	public Passenger getPassenger(int idPassenger) {
		String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPassenger);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Passenger passenger = new Passenger();
                    passenger.setId(resultSet.getInt("ID"));
                    passenger.setName(resultSet.getString("NAME"));
                    passenger.setAge(resultSet.getInt("AGE"));
                    passenger.setWeight(resultSet.getFloat("WEIGHT"));

                    return passenger;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
     * Elimina un pasajero de la base de datos por su identificador.
     * @param idPassenger: El identificador del pasajero a eliminar.
     */
	public void deletePassenger(int idPassenger) {
		String sql = "DELETE FROM PASSENGERS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPassenger);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pasajero con ID " + idPassenger + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún pasajero con el ID " + idPassenger + " para eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
     * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
     * @return La lista de pasajeros.
     */
	public List<Passenger> getPassengers() {
		List<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM PASSENGERS";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Passenger passenger = new Passenger();
                    passenger.setId(resultSet.getInt("ID"));
                    passenger.setName(resultSet.getString("NAME"));
                    passenger.setAge(resultSet.getInt("AGE"));
                    passenger.setWeight(resultSet.getFloat("WEIGHT"));

                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
	}
	
	
	/**
     * Asocia un pasajero a un coche en la base de datos.
     * @param idPassenger: El identificador del pasajero.
     * @param idCar:El identificador del coche.
     */
	public void addPassengerToCar(int idPassenger, int idCar) {
        String sql = "INSERT INTO carspassengers (id_car, id_passenger) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCar);
            statement.setInt(2, idPassenger);

            statement.executeUpdate();
            System.out.println("Pasajero añadido al coche exitosamente en carspassengers.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Elimina la asociación de un pasajero con un coche en la base de datos.
     * @param idPassenger: El identificador del pasajero.
     * @param idCar:El identificador del coche.
     */
	public void deletePassengerFromCar(int idPassenger, int idCar) {
        String sql = "DELETE FROM carspassengers WHERE id_car = ? AND id_passenger = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCar);
            statement.setInt(2, idPassenger);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pasajero eliminado del coche correctamente.");
            } else {
                System.out.println("No se encontró ninguna relación entre el coche y el pasajero.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Obtiene una lista de todos los pasajeros asociados a un coche en particular.
     * @param idCar: El identificador del coche.
     * @return La lista de pasajeros asociados al coche.
     */
	public List<Passenger> getPassengersByCar(int idCar) {
	    List<Passenger> passengers = new ArrayList<>();
	    String checkCarSql = "SELECT COUNT(*) AS count FROM cars WHERE id = ?";
	    String getPassengersSql = "SELECT p.* FROM passengers p WHERE p.id IN (SELECT id_passenger FROM carspassengers WHERE id_car = ?)";
	    
	    // Comprobar si el coche existe o no
	    try (PreparedStatement checkCarStatement = connection.prepareStatement(checkCarSql)) {
	        checkCarStatement.setInt(1, idCar);

	        try (ResultSet checkCarResultSet = checkCarStatement.executeQuery()) {
	            if (checkCarResultSet.next()) {
	                int carCount = checkCarResultSet.getInt("count");
	                if (carCount == 0) {
	                    System.out.println("El coche con ID " + idCar + " no existe.");
	                    return passengers; 
	                }
	            }
	           
	        }

	        // El coche existe,obtener los pasajeros
	        
	        try (PreparedStatement getPassengersStatement = connection.prepareStatement(getPassengersSql)) {
	            getPassengersStatement.setInt(1, idCar);

	            try (ResultSet resultSet = getPassengersStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Passenger passenger = new Passenger();
	                    passenger.setId(resultSet.getInt("ID"));
	                    passenger.setName(resultSet.getString("NAME"));
	                    passenger.setAge(resultSet.getInt("AGE"));
	                    passenger.setWeight(resultSet.getFloat("WEIGHT"));

	                    passengers.add(passenger);
	                }
	            }
	            
	            if (passengers.isEmpty()) {     
        	        System.out.println("Este coche no tiene pasajeros");
        	    }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return passengers;
	}	

}

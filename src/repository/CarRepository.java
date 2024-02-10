package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Car;

/**
 * Repositorio que maneja las operaciones de base de datos relacionadas con los coches.
 */
public class CarRepository {
	
	private Connection connection;
	
	 /**
     * Constructor que establece la conexión para el repositorio de coches.
     * @param connection: La conexión a la base de datos.
     */
	
	public CarRepository(Connection connection) {
		this.connection = connection;
	}
	
	/**
     * Inserta un nuevo coche en la base de datos.
     * @param car:El coche a insertar.
     */
	public void insertCar(Car car) {
        String sql = "INSERT INTO CARS (BRAND, MODEL, YEAR, KM) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setLong(4, car.getKm());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Se ha añadido el coche correctamente");
            } else {
                System.err.println("Ha sucedido un error y no se ha podido añadir el coche.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Obtiene un coche de la base de datos por su identificador.
     * @param idCar: El identificador del coche a obtener.
     * @return El coche encontrado o null si no existe.
     */
	public Car getCar(int idCar) {
		String sql = "SELECT * FROM CARS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCar);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("ID"));
                    car.setBrand(resultSet.getString("BRAND"));
                    car.setModel(resultSet.getString("MODEL"));
                    car.setYear(resultSet.getInt("YEAR"));
                    car.setKm(resultSet.getInt("KM"));

                    return car;
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
     * Elimina un coche de la base de datos por su identificador.
     * @param idCar: El identificador del coche a eliminar.
     */
	public void deleteCar(int idCar) {
		String sql = "DELETE FROM CARS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCar);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Coche con ID " + idCar + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún coche con el ID " + idCar + " para eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	 /**
     * Actualiza los datos de un coche en la base de datos.
     * @param car: El coche con los datos actualizados.
     */
	public void updateCar(Car car) {
		String sql = "UPDATE CARS SET BRAND = ?, MODEL = ?, YEAR = ?, KM = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getKm());
            statement.setInt(5, car.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Coche con ID " + car.getId() + " actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún coche con el ID " + car.getId() + " para actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
     * Obtiene una lista de todos los coches almacenados en la base de datos.
     * @return La lista de coches.
     */
	public List<Car> getCars() {
		List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM CARS";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("ID"));
                    car.setBrand(resultSet.getString("BRAND"));
                    car.setModel(resultSet.getString("MODEL"));
                    car.setYear(resultSet.getInt("YEAR"));
                    car.setKm(resultSet.getInt("KM"));

                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cars;
		
	}

}

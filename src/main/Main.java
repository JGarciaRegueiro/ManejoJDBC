package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import controller.CarController;

/**
 * Clase principal que inicia la aplicación.
 */
public class Main {

	public static void main(String[] args) {
		
		//Leemos la config que pusimos en el archivo config.properties
		Properties properties = new Properties();
		try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
		
		// Configuramos la conexión
		String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");        
        
        // Intentamos establecer la conexión
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password.isBlank()? "" : password)) {
            System.out.println("Conexión realizada!");
            
            CarController carController = new CarController(connection);
            carController.init();

        } catch (SQLException e) {        	
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

	}

}

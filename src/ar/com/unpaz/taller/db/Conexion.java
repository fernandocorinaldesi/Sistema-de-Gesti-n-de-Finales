package ar.com.unpaz.taller.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	
	private  static String DB_DRIVER_CLASS;
	private  static String DB_URL;
	private  static String DB_USERNAME;
	private  static String DB_PASSWORD;
	
	
	
	
	static {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
        	fis = new FileInputStream("./config/db.properties");
        	props.load(fis);
        	
        	DB_DRIVER_CLASS = props.getProperty("DB_DRIVER_CLASS");
        	DB_URL = props.getProperty("DB_URL");
        	DB_USERNAME=props.getProperty("DB_USERNAME");
        	DB_PASSWORD = props.getProperty("DB_PASSWORD");
        	
        	Class.forName(DB_DRIVER_CLASS);
        	
        }
        catch (IOException error )
            {
            	System.out.println("Error la leer el archivo de configuracion db.properties " + error);
            	
            }
		catch(ClassNotFoundException error)
		{
			System.out.println("Error: al cargar el driver de conexión a la Base " + error);
			
		}
	}
	
	
	public static Connection getConexion() 
	{
		Connection con = null;
        try {
        
        	con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			return(con);
		}


		catch(SQLException error)
		{
			System.out.println("Error: en el socket de conexión a la base " + error);
			return(null);
		}
	
	}
}

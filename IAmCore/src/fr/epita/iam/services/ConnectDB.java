/**
 * 
 */
package fr.epita.iam.services;

/**
 * @author Administrator
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection getConection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/identitymanagement", "root", "");
		} catch (Exception e) {
			connection = null;
		}
		return connection;
	}
}
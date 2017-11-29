package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaMySql {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from categoria");
		
		while (resultSet.next())
			System.out.printf("%5s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		
		statement.close();
		connection.close();
		
	}
	
	
	

}

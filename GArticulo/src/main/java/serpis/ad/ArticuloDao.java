package serpis.ad;


import java.sql.*;

public class ArticuloDao {

	public static void conectar(Connection connection) throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba",
				"root", "sistemas");
	}
}

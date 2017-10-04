using System;
using MySql.Data.MySqlClient;
using System.Data;

namespace CMySQL
{
    class MainClass
    {
        private static IDbConnection connection;
        public static void Main(string[] args)
        {
            String connectionString = "server=localhost;database=dbprueba;user=root;password=sistemas";
            connection = new MySqlConnection(connectionString);
            connection.Open();
            //nuevaCategoria();
            leerCategorias();
            connection.Close();



        }

        private static void leerCategorias(){
			IDbCommand dbCommand = connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria";
			IDataReader dataReader = dbCommand.ExecuteReader();
			while (dataReader.Read())
				Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
			dataReader.Close();
        }

        private static void nuevaCategoria(){
            IDbCommand dbCommand = connection.CreateCommand();
            dbCommand.CommandText = "insert into categoria (nombre) values ('categoria 4')";
            addParameter(dbCommand, "nombre", "categoria 6");
            dbCommand.ExecuteNonQuery();
        }

        private static void addParameter(IDbCommand dbCommand, String name, object value){
			
	        IDbDataParameter dbDataParameter = dbCommand.CreateParameter();
            dbDataParameter.ParameterName = name;
            dbDataParameter.Value = value;
			dbCommand.Parameters.Add(dbDataParameter);
			
        }

        private static void eliminarCategoria(){
			IDbCommand DbComand = connection.CreateCommand();
			DbComand.CommandText = "delete from categoria where id=4";
			DbComand.ExecuteNonQuery();
		}

		private static void modificarCategoria()
		{
			IDbCommand DbComand = connection.CreateCommand();
			DbComand.CommandText = "update categoria set nombre where id=4";
			DbComand.ExecuteNonQuery();
		}

    }
}

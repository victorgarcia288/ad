﻿using System;
using MySql.Data.MySqlClient;
using System.Data;
using Serpis;

namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        object id;
        public CategoriaWindow(object id) : base(Gtk.WindowType.Toplevel) {
            this.Build();

            this.id = id;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria where id = @id";
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read();
            String nombre = (string)dataReader["nombre"];
            dataReader.Close();
            entryNombre.Text = nombre;

			saveAction.Activated += delegate {
				update();
				Destroy();
			};
		}

        public CategoriaWindow() : base(Gtk.WindowType.Toplevel)
        {
            this.Build();

            saveAction.Activated += delegate {
                insert();
                Destroy();
            };

 
        }

		private void insert()
		{
			string name = entryNombre.Text;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
			DbCommandHelper.AddParameter(dbCommand, "nombre", name);
			dbCommand.ExecuteNonQuery();
		}

		private void update()
		{
            string name = entryNombre.Text;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre=@nombre where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			DbCommandHelper.AddParameter(dbCommand, "nombre", name);
			dbCommand.ExecuteNonQuery();
		}
    }
}

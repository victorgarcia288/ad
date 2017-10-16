﻿using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;

using CCategoria;

public partial class MainWindow : Gtk.Window
{
    
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        

        this.Build();

        string connectionString = "server=localhost;database=dbprueba;user=root;password=sistemas";
		App.Instance.Connection = new MySqlConnection(connectionString);
		App.Instance.Connection.Open();

		treeview1.AppendColumn("id", new CellRendererText(), "text", 0);
        treeview1.AppendColumn("nombre", new CellRendererText(), "text", 1);
        ListStore listStore = new ListStore(typeof(string), typeof(string));
        treeview1.Model = listStore;

        fillListStore(listStore);

        newAction.Activated += delegate {
            new CategoriaWindow();
        };

        refreshAction.Activated += delegate {
            listStore.Clear();
            fillListStore(listStore);
        };

	}

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        App.Instance.Connection.Close();
        Application.Quit();
        a.RetVal = true;
    }

    private void fillListStore(ListStore listStore){
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "select * from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["nombre"]);
		dataReader.Close();
    }
}

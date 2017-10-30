﻿using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;
using Serpis;

using CCategoria;

public partial class MainWindow : Gtk.Window
{
    
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        

        this.Build();
        Title = "Categoria";

        deleteAction.Sensitive = false;
        editAction.Sensitive = false;

        string connectionString = "server=localhost;database=dbprueba;user=root;password=sistemas";
		App.Instance.Connection = new MySqlConnection(connectionString);
		App.Instance.Connection.Open();

		treeview1.AppendColumn("id", new CellRendererText(), "text", 0);
        treeview1.AppendColumn("nombre", new CellRendererText(), "text", 1);
        ListStore listStore = new ListStore(typeof(string), typeof(string));
        treeview1.Model = listStore;

        fillListStore(listStore);

        treeview1.Selection.Changed += delegate {
            bool hasSelected = treeview1.Selection.CountSelectedRows() > 0;
            deleteAction.Sensitive = hasSelected;
            editAction.Sensitive = hasSelected;
        };

        newAction.Activated += delegate {
            Categoria categoria = new Categoria();
            new CategoriaWindow(categoria);
        };

        editAction.Activated += delegate {
            object id = getId();
            Categoria categoria = CategoriaDao.Load(id);
            new CategoriaWindow(categoria);
        };

        refreshAction.Activated += delegate {
            listStore.Clear();
            fillListStore(listStore);
        };

        deleteAction.Activated += delegate {
            if (WindowsHelper.Confirm(this, "Quieres eliminar el registro"))
            {

                object id = getId();
                CategoriaDao.Delete(id);
            }				
			
           
        };


	}

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        App.Instance.Connection.Close();
        Application.Quit();
        a.RetVal = true;
    }

    private object getId(){
	    TreeIter treeIter;
	    treeview1.Selection.GetSelected(out treeIter);
        return treeview1.Model.GetValue(treeIter, 0);
    }
    

    private void fillListStore(ListStore listStore){
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "select * from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
            listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"]);
		dataReader.Close();
    }
}

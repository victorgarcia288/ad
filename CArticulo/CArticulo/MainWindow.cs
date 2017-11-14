using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;
using CArticulo;
using Serpis;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();
		Title = "Articulo";

		

		string connectionString = "server=localhost;database=dbprueba;user=root;password=sistemas";
		App.Instance.Connection = new MySqlConnection(connectionString);
		App.Instance.Connection.Open();

		TreeViewHelper.Fill(treeview1,"select a.id, a.precio, a.nombre, c.nombre as categoria" +
                            " from articulo a left join categoria c on " +
                            "a.categoria = c.id order by a.id");

		newAction.Activated += delegate {
            Articulo articulo = new Articulo();
            new ArticuloWindow(articulo);
		};

    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}

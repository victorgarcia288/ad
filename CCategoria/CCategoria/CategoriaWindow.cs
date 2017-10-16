using System;
using MySql.Data.MySqlClient;
using System.Data;
namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
       
        public CategoriaWindow() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();

            saveAction.Activated += delegate {
                string name = entryNOmbre.Text;
                IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
                dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
                DbCommandHelper.AddParameter(dbCommand, "nombre", name);
                dbCommand.ExecuteNonQuery();
                Destroy();
            };
        }
    }
}

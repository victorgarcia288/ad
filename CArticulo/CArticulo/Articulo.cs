using System;
namespace CArticulo
{
    public class Articulo
    {
        public Articulo()
        {
        }

        private long categoria;
        private long id;
        private string nombre = "";
        private decimal precio;

        public long Categoria{
            get { return categoria; }
            set { categoria = value; }
        }

        public long Id{
            get { return id; }
            set { id = value; }
        }

        public string Nombre{
            get { return nombre; }
            set { nombre = value; }
        }

        public decimal Precio{
            get { return precio; }
            set { precio = value; }
        }
    }
}

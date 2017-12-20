package serpis.ad;

import java.util.*;

public class ArticuloMain {
	
	public enum Option {Salir, Nuevo, Editar, Eliminar, Consultar, Listar}
	
	private static Scanner scanner = new Scanner (System.in);
	
	

	public static void main(String[] args) {
		
		//while (true) {
			//Option option = scanOption();
			//if ( option == Option.Salir)
				//break;
			//else if (option == Option.Nuevo);
			
		//}
		
		
	//}
		
		new Menu()
		 	.add("Salir", null);
			.add("Nuevo", () -> nuevo());
			.add("Editar", () -> editar());
	

	
	//public static Option scanOption() {
		//for(int index =0; index < Option.values().length; index++) 
			//System.out.printf("%s, %s\n", index, Option.values()[index]);
		//String options = String.format("^[0-%s]$", Option.values().length -1);
		//while(true) {
			//System.out.print("Elige una opcion: ");
			//String line = scanner.nextLine();
			//if (line.matches(options)) 
				//return Option.values()[Integer.parseInt(line)];
			//System.out.println("Opcion invalida, vuelva a introducir.");	
		}
	}

}

package serpis.ad;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Escaner {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static int scanInt(String label) {
		while(true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return Integer.parseInt(line);
			}catch (NumberFormatException ex) {
				System.out.println("Debe ser un un numero,Vuelve a introducir");
			}
		}
		
	}
	
	

}

package serpis.ad;

import java.sql.ResultSet;

public class Menu {
	public String label;
	public Runnable runnable;
	public Option(String label, Runnable runnable);
	
	private List<Option> options = new ArrayList<>();
	
	public Menu() {
		
	}
	
	public Menu add(String label, Runnable runnable) {
		options.add(new Option(label, runnable));
	}
	public void run() {
		while (true) {
			Option option = scanOption();
			if(option.runnable == null)
				return;
			option.runnable.run;
		}
	}
	private Option scanOption() {
	
	
}

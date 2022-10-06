package travel.app.view;

import java.util.Scanner;

public class ConsolView implements View{
	public ConsolView() {}
	
	private Scanner scan = new Scanner(System.in);
	
	public void Display(String msg) {
		System.out.println(msg);
	}
	
	public int getIntInput(String question) {
		System.out.println(question);
		return scan.nextInt();
	}
	
	public String getStringInput(String question) {
		String line = "";
		System.out.println(question);
		do {
			while(scan.hasNextLine()) {
	            line = scan.nextLine();
	            return line;
	        }
		}while(line.equals(""));
		
        
        return line;
	}
}
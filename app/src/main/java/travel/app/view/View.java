package travel.app.view;

public interface View{
	void Display(String msg);
	
	int getIntInput(String question);
	
	String getStringInput(String question);
}
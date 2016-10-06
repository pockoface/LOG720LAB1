import java.util.Scanner;

public class VehiculeClient {
	
	public static String selectedFileRecord = "";
	public static String selectedManager = "";
	
	public static void main(String[] args) {
		
		System.out.println("Client du véhicule: ");
		displayMainMenu();
		mainMenu();
		
	}
	
	private static void displayMainMenu(){
		System.out.println("A - Gestionnaire de réactions");
		System.out.println("B - Gestionnaire des dossiers");
		System.out.println("C - Gestionnaire des infractions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'une lettre.");
	}
	
	private static void mainMenu(){
		
		while(selectedManager != "A" || selectedManager != "B" ||  selectedManager != "C"){
			System.out.println("Attention! Veuillez entrer un choix valide!");
			selectedManager = managerSelector();
		}
		
		Scanner valeurEntree = new Scanner(System.in);
		System.out.println(valeurEntree);
	}
	
	private static String managerSelector(){
		return "Test";
		
	}
	
}

import java.util.Scanner;

public class VehiculeClient {
	
	public static String selectedFileRecord = "";
	public static int selectedManager = 0;
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Client du véhicule: ");
		displayMainMenu();
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3).");
		mainMenu();
		
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Gestionnaire des dossiers");
		System.out.println("2 - Gestionnaire des réactions");
		System.out.println("3 - Gestionnaire des infractions");
	}
	
	private static void mainMenu(){
		
		selectedManager = managerSelector();
		
		while(selectedManager != 1 && selectedManager != 2 && selectedManager != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = managerSelector();	
		}
		
		switch(selectedManager){
		case 1:
			displayFilesMenu();
			enteredValue.close();
			break;
		case 2:
			displayReactionsMenu();
			enteredValue.close();
			break;
		case 3:
			displayInfractionsMenu();
			enteredValue.close();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = managerSelector();
			break;
		}
		
		
	}
	
	private static void displayFilesMenu(){
		System.out.println("DOSSIERS");
	}
	
	private static void displayReactionsMenu(){
		System.out.println("Gestionnaire des réactions");
		System.out.println("1 - Liste des réactions");
		System.out.println("2 - Ajouter des réactions à la liste des réactions");
		System.out.println("3 - Ajouter des réactions au dossier sélectionné");
	}
	
	private static void displayInfractionsMenu(){
		System.out.println("INFRACTIONS");
	}

	private static int managerSelector(){
		try{
			int returnValue =Integer.parseInt(enteredValue.next());
			return returnValue;
		}catch(Exception e){
			return 0;	
		}
	} 
}

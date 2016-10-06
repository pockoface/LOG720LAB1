import java.util.Scanner;

public class VehiculeClient {
	
	public static String selectedFileRecord = "";
	public static int selectedManager = 0;
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Client du véhicule: ");
		displayMainMenu();
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
		mainMenu();	
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Gestionnaire des dossiers");
		System.out.println("2 - Gestionnaire des réactions");
		System.out.println("3 - Gestionnaire des infractions");
	}
	
	private static void mainMenu(){
		
		selectedManager = integerItemSelector();
		
		while(selectedManager != 1 && selectedManager != 2 && selectedManager != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = integerItemSelector();	
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
			selectedManager = integerItemSelector();
			break;
		}	
	}
	
	private static void displayFilesMenu(){
		clearConsole();
		System.out.println("Gestionnaire des dossiers");
		System.out.println("1 - Liste des dossiers");
		System.out.println("2 - Liste de dossiers selon le nom et prénom");
		System.out.println("3 - Liste de dossiers selon le numéro de plaque");
		System.out.println("4 - Liste de dossiers selon le numéro de permis");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2, 3 ou 4)");
	}
	
	private static void displayReactionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des réactions");
		System.out.println("1 - Liste des réactions");
		System.out.println("2 - Ajouter une réaction à un dossier");
		System.out.println("3 - Ajouter une ou plusieurs réactions à la liste des réactions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
	}
	
	private static void displayInfractionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des infractions");
		System.out.println("1 - Liste des infractions");
		System.out.println("2 - Ajouter une infraction à un dossier");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1 ou 2)");
	}

	private static int integerItemSelector(){
		try{
			int returnValue =Integer.parseInt(enteredValue.next());
			return returnValue;
		}catch(Exception e){
			return 0;	
		}
	}
	
	private static String stringItemSelector(){
		try{
			String returnValue = enteredValue.next().toString();
			return returnValue;
		}catch(Exception e){
			return "";	
		}
	}
	
	private static void clearConsole(){
		for (int i = 0; i < 5; ++i) {
			System.out.println();
		}
	}
}

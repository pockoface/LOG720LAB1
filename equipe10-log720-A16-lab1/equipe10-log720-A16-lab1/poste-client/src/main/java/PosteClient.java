import java.util.Scanner;

public class PosteClient {
	public static String selectedFileRecord = "";
	public static int selectedMainMenuItem = 0;
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Client du poste: ");
		displayMainMenu();
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2, 3 ou 4)");
		mainMenu();	
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Ajouter un ou plusieurs dossiers à la banque de dossiers");
		System.out.println("2 - Ajouter une ou plusieurs infractions à la banque d'infractions");
		System.out.println("3 - Visualiser la liste de l'ensemble des dossiers");
		System.out.println("4 - Visualiser la liste de l'ensemble des infractions");
	}
	
	private static void mainMenu(){
		
		selectedMainMenuItem = integerItemSelector();
		
		while(selectedMainMenuItem != 1 && selectedMainMenuItem != 2 && selectedMainMenuItem != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedMainMenuItem = integerItemSelector();	
		}
		
		switch(selectedMainMenuItem){
		case 1:
			addFilesMenu();
			enteredValue.close();
			break;
		case 2:
			addInfractionsMenu();
			enteredValue.close();
			break;
		case 3:
			displayFilesMenu();
			enteredValue.close();
			break;
		case 4:
			displayInfractionsMenu();
			enteredValue.close();
			break;	
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedMainMenuItem = integerItemSelector();
			break;
		}	
	}
	
	private static void addFilesMenu(){
		clearConsole();
		System.out.println("Gestionnaire d'ajout de dossiers");
		System.out.println("1 - Ajouter un dossier");
		System.out.println("2 - Ajouter plusieurs dossiers");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1 ou 2)");
	}
	
	private static void addInfractionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire d'ajout de dossiers");
		System.out.println("1 - Liste des réactions");
		System.out.println("2 - Ajouter une réaction à un dossier");
		System.out.println("3 - Ajouter une ou des réactions à la liste des réactions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
	}
	
	private static void displayFilesMenu(){
		clearConsole();
		System.out.println("Liste des dossiers");
		System.out.println("1 - add item here...");
		System.out.println("2 - add item here...");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1 ou 2)");
	}
	
	private static void displayInfractionsMenu(){
		clearConsole();
		System.out.println("Liste des infractions");
		System.out.println("1 - add item here...");
		System.out.println("2 - add item here...");
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

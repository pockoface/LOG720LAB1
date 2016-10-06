import java.util.Scanner;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ca.etsmtl.log720.lab1.BanqueDossiers;
import ca.etsmtl.log720.lab1.BanqueDossiersHelper;
import ca.etsmtl.log720.lab1.BanqueInfractions;
import ca.etsmtl.log720.lab1.BanqueInfractionsHelper;
import ca.etsmtl.log720.lab1.BanqueReactions;
import ca.etsmtl.log720.lab1.BanqueReactionsHelper;

public class VehiculeClient {
	
	public static String selectedFileRecord = "";
	public static int selectedManager = 0;
	public static int selectedItem = 0;
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));

			NameComponent[] dossierServer = new NameComponent[] { new NameComponent(
					"Dossier", "service") };
			
			NameComponent[] reactionServer = new NameComponent[] { new NameComponent(
					"Reaction", "service") };
			
			NameComponent[] infractionServer = new NameComponent[] { new NameComponent(
					"Infraction", "service") };
			
			BanqueDossiers dossier = BanqueDossiersHelper
					.narrow(nc.resolve(dossierServer));
			
			BanqueReactions reaction = BanqueReactionsHelper
					.narrow(nc.resolve(reactionServer));
			
			BanqueInfractions infraction = BanqueInfractionsHelper
					.narrow(nc.resolve(infractionServer));
			
			System.out.println("Client du véhicule: ");
			displayMainMenu();
			System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
			mainMenu();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Gestionnaire des dossiers");
		System.out.println("2 - Gestionnaire des réactions");
		System.out.println("3 - Gestionnaire des infractions");
	}
	
	private static void mainMenu(){
		
		selectedManager = integerSelector();
		
		while(selectedManager != 1 && selectedManager != 2 && selectedManager != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = integerSelector();	
		}
		
		switch(selectedManager){
		case 1:
			displayFilesMenu();
			filesMenu();
			enteredValue.close();
			break;
		case 2:
			displayReactionsMenu();
			reactionsMenu();
			enteredValue.close();
			break;
		case 3:
			displayInfractionsMenu();
			infractionsMenu();
			enteredValue.close();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = integerSelector();
			break;
		}	
	}
	
	/***********************************************************************************************/
	private static void displayFilesMenu(){
		clearConsole();
		System.out.println("Gestionnaire des dossiers");
		System.out.println("1 - Liste des dossiers");
		System.out.println("2 - Liste de dossiers selon le nom et prénom");
		System.out.println("3 - Liste de dossiers selon le numéro de plaque");
		System.out.println("4 - Liste de dossiers selon le numéro de permis");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2, 3 ou 4)");		
	}
	
	private static void filesMenu(){
		
		selectedItem = integerSelector();
		
		while(selectedItem != 1 && selectedItem != 2 && selectedItem != 3 && selectedItem != 4){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayFilesMenu();
			selectedItem = integerSelector();	
		}
		
		switch(selectedItem){
		case 1:
			listFilesFunction();
			break;
		case 2:
			listFilesByNameFunction();
			break;
		case 3:
			listFilesByPlateFunction();
			break;
		case 4:
			listFilesByLicenseFunction();
			break;	
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayFilesMenu();
			selectedItem = integerSelector();
			break;
		}
	}
	
	private static void listFilesFunction(){
		System.out.println("LIST FILES");
	}
	
	private static void listFilesByNameFunction(){
		System.out.println("LIST FILES BY NAME AND LAST NAME");
	}
	
	private static void listFilesByPlateFunction(){
		System.out.println("LIST FILES BY PLATE");
	}
	
	private static void listFilesByLicenseFunction(){
		System.out.println("LIST FILES BY LICENSE");
	}
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayReactionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des réactions");
		System.out.println("1 - Liste des réactions");
		System.out.println("2 - Ajouter une réaction à un dossier");
		System.out.println("3 - Ajouter une ou plusieurs réactions à la liste des réactions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
	}
	
	private static void reactionsMenu(){
		
		selectedItem = integerSelector();
		
		while(selectedItem != 1 && selectedItem != 2 && selectedItem != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayReactionsMenu();
			selectedItem = integerSelector();	
		}
		
		switch(selectedItem){
		case 1:
			listReactionsFunction();
			break;
		case 2:
			addReactionToFileFunction();
			break;
		case 3:
			addReactionToReactionsListFunction();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayReactionsMenu();
			selectedItem = integerSelector();
			break;
		}
	}
	
	private static void listReactionsFunction(){
		System.out.println("LIST REACTIONS");
	}
	
	private static void addReactionToFileFunction(){
		System.out.println("ADD A REACTION TO A FILE");
	}
	
	private static void addReactionToReactionsListFunction(){
		System.out.println("ADD REACTIONS TO REACTIONS LIST");
	}
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayInfractionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des infractions");
		System.out.println("1 - Liste des infractions");
		System.out.println("2 - Ajouter une infraction à un dossier");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1 ou 2)");
	}
	
	private static void infractionsMenu(){
		
		selectedItem = integerSelector();
		
		while(selectedItem != 1 && selectedItem != 2){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayInfractionsMenu();
			selectedItem = integerSelector();	
		}
		
		switch(selectedItem){
		case 1:
			listInfractionsFunction();
			break;
		case 2:
			addInfractionsToFileFunction();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayInfractionsMenu();
			selectedItem = integerSelector();
			break;
		}
	}
	
	private static void listInfractionsFunction(){
		System.out.println("LIST INFRACTIONS");
	}
	
	private static void addInfractionsToFileFunction(){
		System.out.println("ADD INFRACTION TO A FILE");
	}
	/***********************************************************************************************/
	private static int integerSelector(){
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

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
import ca.etsmtl.log720.lab1.CollectionDossier;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.CollectionReaction;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.Reaction;

public class VehiculeClient {
	
	public static String selectedStringValue = "";
	public static int selectedManager = 0;
	public static int selectedIntegerValue = 0;
	public static Scanner enteredValue = new Scanner(System.in);
	
	public static BanqueDossiers dossier;
	public static BanqueReactions reaction;
	public static BanqueInfractions infraction;

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
			
			dossier = BanqueDossiersHelper
					.narrow(nc.resolve(dossierServer));
			
			reaction = BanqueReactionsHelper
					.narrow(nc.resolve(reactionServer));
			
			infraction = BanqueInfractionsHelper
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
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2 && selectedIntegerValue != 3 && selectedIntegerValue != 4){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayFilesMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
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
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void listFilesFunction(){
		System.out.println("LIST FILES");
		 printFilesList();
	}
	
	private static void listFilesByNameFunction(){
		System.out.println("LIST FILES BY NAME AND LAST NAME");
		
		String lastName = returnLastName();
		String firstName = returnFirstName();
		
		System.out.println("Prénom = " + firstName + " ||| Nom de famille = " + lastName);
		//dossier.trouverDossiersParNom(lastName, firstName);
		 printFilesList();
	}
	
	private static String returnLastName(){
		System.out.println("Veuillez entrer le nom de famille:");
		String lastName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnLastName();
		}
		
		lastName = selectedStringValue;
		
		return lastName;
	}
	
	private static String returnFirstName(){
		System.out.println("Veuillez entrer le prénom:");
		String firstName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnFirstName();
		}
		
		firstName = selectedStringValue;
		
		return firstName;
	}
	
	private static void listFilesByPlateFunction(){
		System.out.println("LIST FILES BY PLATE");
		
		String carPlate = returnCarPlate();
		
		System.out.println("Plaque de la voiture = " + carPlate);
		//dossier.trouverDossiersParPlaque(carPlate);
		 printFilesList();
	}
	
	private static String returnCarPlate(){
		System.out.println("Veuillez entrer la plaque de la voiture:");
		String carPlate;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnCarPlate();
		}
		
		carPlate = selectedStringValue;
		
		return carPlate;
	}
	
	private static void listFilesByLicenseFunction(){
		System.out.println("LIST FILES BY LICENSE");
		
		String driverLicenseNumber = returnDriverLicense();
		
		System.out.println("Numéro du permis = " + driverLicenseNumber);
		//dossier.trouverDossierParPermis(driverLicenseNumber);
		printFilesList();
	}
	
	private static String returnDriverLicense(){
		System.out.println("Veuillez entrer le numéros du permis:");
		String driverLicenseNumber;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnDriverLicense();
		}
		
		driverLicenseNumber = selectedStringValue;
		
		return driverLicenseNumber;
	}
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayReactionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des réactions");
		System.out.println("1 - Liste des réactions");
		System.out.println("2 - Ajouter une réaction à un dossier");
		System.out.println("3 - Ajouter une réaction à la liste des réactions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2 ou 3)");
	}
	
	private static void reactionsMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2 && selectedIntegerValue != 3){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayReactionsMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
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
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void listReactionsFunction(){
		System.out.println("LIST REACTIONS");
		printReactionsList();
	}
	
	private static void addReactionToFileFunction(){
		System.out.println("ADD A REACTION TO A FILE");

		int idFile = returnFileID();
		int idReaction = returnReactionID();
		
		System.out.println("Numéro d'identification du dossier = " + idFile + " ||| Numéro d'identification de la réaction = " + idReaction);
		//dossier.ajouterReactionAuDossier(idFile, idReaction);
		printReactionsList();
	}
	
	private static int returnFileID(){
		System.out.println("Veuillez entrer le numéro d'identification du dossier:");
		int idFile;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == 0){
			System.out.println("Entrée invalide!");
			returnFileID();
		}
		
		idFile = selectedIntegerValue;
		
		return idFile;
	}
	
	private static int returnReactionID(){
		System.out.println("Veuillez entrer le numéro d'identification de la réaction:");
		int idReaction;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == 0){
			System.out.println("Entrée invalide!");
			returnReactionID();
		}
		
		idReaction = selectedIntegerValue;
		
		return idReaction;
	}
	
	private static void addReactionToReactionsListFunction(){
		System.out.println("ADD REACTIONS TO REACTIONS LIST");
		
		String reactionName = returnReactionName();
		int reactionGravity = returnReactionGravity();
		
		System.out.println("Nom de réaction = " + reactionName + " ||| Gravité de la réaction = " + reactionGravity);
		//reaction.ajouterReaction(reactionName, reactionGravity);
		printReactionsList();
	}
	
	private static String returnReactionName(){
		System.out.println("Veuillez entrer le nom de la réaction:");
		String reactionName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnReactionName();
		}
		
		reactionName = selectedStringValue;
		
		return reactionName;
	}
	
	private static int returnReactionGravity(){
		System.out.println("Veuillez entrer la gravité de la réaction:");
		int reactionGravity;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == 0){
			System.out.println("Entrée invalide!");
			returnReactionGravity();
		}
		
		reactionGravity = selectedIntegerValue;
		
		return reactionGravity;
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
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayInfractionsMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
		case 1:
			listInfractionsFunction();
			break;
		case 2:
			addInfractionsToFileFunction();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayInfractionsMenu();
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void listInfractionsFunction(){
		System.out.println("LIST INFRACTIONS");
		printInfractionList();
	}
	
	private static void addInfractionsToFileFunction(){
		System.out.println("ADD INFRACTION TO A FILE");
		
		int idFile = returnFileID();
		int idInfraction = returnInfractionID();
		
		System.out.println("Numéro d'identification du dossier = " + idFile + " ||| Numéro d'identification de l'infraction = " + idInfraction);
		//dossier.ajouterInfractionAuDossier(idFile, idInfraction);
		printInfractionList();
	}
	
	private static int returnInfractionID(){
		System.out.println("Veuillez entrer le numéro d'identification de l'infraction:");
		int idInfraction;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == 0){
			System.out.println("Entrée invalide!");
			returnInfractionID();
		}
		
		idInfraction = selectedIntegerValue;
		
		return idInfraction;
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
	
	private static String stringSelector(){
		try{
			String returnValue = enteredValue.next().toString();
			return returnValue;
		}catch(Exception e){
			return "failed";	
		}
	}
	
	private static void printFilesList(){
		CollectionDossier cdos = dossier.dossiers();
		for(int i = 0; i < cdos.size(); i++){
			Dossier dos = cdos.getDossier(i);
			System.out.println(dos.toString());	
		}
	}
	
	private static void printReactionsList(){
		CollectionReaction creact = reaction.reactions();
		for(int i = 0; i < creact.size(); i++){
			Reaction react = creact.getReaction(i);
			System.out.println(react.toString());	
		}
	}
	
	private static void printInfractionList(){
		CollectionInfraction cinfra = infraction.infractions();
		for(int i = 0; i < cinfra.size(); i++){
			Infraction inf = cinfra.getInfraction(i);
			System.out.println(inf.toString());
		}
	}
	
	private static void clearConsole(){
		for (int i = 0; i < 5; ++i) {
			System.out.println();
		}
	}
}

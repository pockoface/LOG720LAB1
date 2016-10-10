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
import ca.etsmtl.log720.lab1.InvalidIdException;
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
			
			System.out.println("Client du vehicule: ");
			displayMainMenu();
			System.out.println("Veuillez selectionner un choix a l'aide d'un chiffre (1, 2 ou 3)");
			mainMenu();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Gestionnaire des dossiers");
		System.out.println("2 - Gestionnaire des reactions");
		System.out.println("3 - Gestionnaire des infractions");
	}
	
	private static void mainMenu(){
		
		selectedManager = integerSelector();
		
		while(selectedManager != 1 && selectedManager != 2 && selectedManager != 3){
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
		System.out.println("2 - Liste de dossiers selon le nom et prenom");
		System.out.println("3 - Liste de dossiers selon le numero de plaque");
		System.out.println("4 - Liste de dossiers selon le numero de permis");
		System.out.println("Veuillez selectionner un choix a l'aide d'un chiffre (1, 2, 3 ou 4)");		
	}
	
	private static void filesMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2 && selectedIntegerValue != 3 && selectedIntegerValue != 4){
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
		System.out.println("Prenom = " + firstName + " ||| Nom de famille = " + lastName);
		CollectionDossier d = dossier.trouverDossiersParNom(lastName, firstName);
		for(int i = 0; i < d.size(); i++) {
			System.out.println(d.getDossier(i)._toString());
		}
	}
	
	private static String returnLastName(){
		System.out.println("Veuillez entrer le nom de famille:");
		String lastName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entree invalide!");
			returnLastName();
		}
		
		lastName = selectedStringValue;
		
		return lastName;
	}
	
	private static String returnFirstName(){
		System.out.println("Veuillez entrer le prenom:");
		String firstName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entree invalide!");
			returnFirstName();
		}
		
		firstName = selectedStringValue;
		
		return firstName;
	}
	
	private static void listFilesByPlateFunction(){
		System.out.println("LIST FILES BY PLATE");
		
		String carPlate = returnCarPlate();
		
		System.out.println("Plaque de la voiture = " + carPlate);
		
		CollectionDossier d = dossier.trouverDossiersParPlaque(carPlate);
		for(int i = 0; i < d.size(); i++) {
			System.out.println(d.getDossier(i)._toString());
		}
		 
		
	}
	
	private static String returnCarPlate(){
		System.out.println("Veuillez entrer la plaque de la voiture:");
		String carPlate;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entree invalide!");
			returnCarPlate();
		}
		
		carPlate = selectedStringValue;
		
		return carPlate;
	}
	
	private static void listFilesByLicenseFunction(){
		System.out.println("LIST FILES BY LICENSE");
		
		String driverLicenseNumber = returnDriverLicense();
		
		System.out.println("Numero du permis = " + driverLicenseNumber);
		Dossier d = dossier.trouverDossierParPermis(driverLicenseNumber);
		System.out.println(d._toString());
		System.out.println("====INFRACTION====");
		CollectionInfraction inf = infraction.trouverInfractionsParDossier(d);
		System.out.println("FUCKTOU " + inf.size());
		/*for(int i = 0; i < inf.size(); i++) {
			System.out.println(inf.getInfraction(i)._toString());
		}*/
		System.out.println("====REACTION====");
		CollectionReaction r = reaction.trouverReactionsParDossier(d);
		System.out.println(r.size());
		/*for(int i = 0; i < r.size(); i++) {
			System.out.println(r.getReaction(i)._toString());
		}*/
	}
	
	private static String returnDriverLicense(){
		System.out.println("Veuillez entrer le numeros du permis:");
		String driverLicenseNumber;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entree invalide!");
			returnDriverLicense();
		}
		
		driverLicenseNumber = selectedStringValue;
		
		return driverLicenseNumber;
	}
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayReactionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des reactions");
		System.out.println("1 - Liste des reactions");
		System.out.println("2 - Ajouter une reaction a un dossier");
		System.out.println("3 - Ajouter une reaction a la liste des reactions");
		System.out.println("Veuillez selectionner un choix a l'aide d'un chiffre (1, 2 ou 3)");
	}
	
	private static void reactionsMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2 && selectedIntegerValue != 3){
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
		try {
			System.out.println("ADD A REACTION TO A FILE");

			int idFile = returnFileID();
			int idReaction = returnReactionID();
		
			System.out.println("Numero d'identification du dossier = " + idFile + " ||| Numero d'identification de la reaction = " + idReaction);
		
			dossier.ajouterReactionAuDossier(idFile, idReaction);
			printReactionsList();

		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int returnFileID(){
		System.out.println("Veuillez entrer le numero d'identification du dossier:");
		int idFile;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == Integer.MIN_VALUE){
			System.out.println("Entree invalide!");
			returnFileID();
		}
		
		idFile = selectedIntegerValue;
		
		return idFile;
	}
	
	private static int returnReactionID(){
		System.out.println("Veuillez entrer le numero d'identification de la reaction:");
		int idReaction;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == Integer.MIN_VALUE){
			System.out.println("Entree invalide!");
			returnReactionID();
		}
		
		idReaction = selectedIntegerValue;
		
		return idReaction;
	}
	
	private static void addReactionToReactionsListFunction(){
		System.out.println("ADD REACTIONS TO REACTIONS LIST");
		
		String reactionName = returnReactionName();
		int reactionGravity = returnReactionGravity();
		
		System.out.println("Nom de reaction = " + reactionName + " ||| Gravite de la reaction = " + reactionGravity);
		reaction.ajouterReaction(reactionName, reactionGravity);
		printReactionsList();
	}
	
	private static String returnReactionName(){
		System.out.println("Veuillez entrer le nom de la reaction:");
		String reactionName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entree invalide!");
			returnReactionName();
		}
		
		reactionName = selectedStringValue;
		
		return reactionName;
	}
	
	private static int returnReactionGravity(){
		System.out.println("Veuillez entrer la gravite de la reaction:");
		int reactionGravity;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == Integer.MIN_VALUE){
			System.out.println("Entree invalide!");
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
		System.out.println("2 - Ajouter une infraction a un dossier");
		System.out.println("Veuillez selectionner un choix a l'aide d'un chiffre (1 ou 2)");
	}
	
	private static void infractionsMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2){
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
			System.out.println("Entree invalide! Veuillez selectionner l'une des options suivantes:");
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
		try {
			System.out.println("ADD INFRACTION TO A FILE");
		
			int idFile = returnFileID();
			int idInfraction = returnInfractionID();
		
			System.out.println("Numero d'identification du dossier = " + idFile + " ||| Numero d'identification de l'infraction = " + idInfraction);
		
			dossier.ajouterInfractionAuDossier(idFile, idInfraction);
			printInfractionList();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int returnInfractionID(){
		System.out.println("Veuillez entrer le numero d'identification de l'infraction:");
		int idInfraction;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == Integer.MIN_VALUE){
			System.out.println("Entree invalide!");
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
			System.out.println(dos._toString());	
		}
	}
	
	private static void printReactionsList(){
		CollectionReaction creact = reaction.reactions();
		for(int i = 0; i < creact.size(); i++){
			Reaction react = creact.getReaction(i);
			System.out.println(react._toString());	
		}
	}
	
	private static void printInfractionList(){
		CollectionInfraction cinfra = infraction.infractions();
		for(int i = 0; i < cinfra.size(); i++){
			Infraction inf = cinfra.getInfraction(i);
			System.out.println(inf._toString());
		}
	}
	
	private static void clearConsole(){
		for (int i = 0; i < 5; ++i) {
			System.out.println();
		}
	}
}

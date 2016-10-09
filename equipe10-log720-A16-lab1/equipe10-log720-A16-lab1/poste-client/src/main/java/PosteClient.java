import java.util.Scanner;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ca.etsmtl.log720.lab1.BanqueDossiers;
import ca.etsmtl.log720.lab1.BanqueDossiersHelper;
import ca.etsmtl.log720.lab1.BanqueInfractions;
import ca.etsmtl.log720.lab1.BanqueInfractionsHelper;
import ca.etsmtl.log720.lab1.CollectionDossier;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;

public class PosteClient {
	
	public static String selectedStringValue = "";
	public static int selectedManager = 0;
	public static int selectedIntegerValue = 0;
	public static Scanner enteredValue = new Scanner(System.in);
	
	public static BanqueDossiers dossier;
	public static BanqueInfractions infraction;

	public static void main(String[] args) {
		try{
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));

			NameComponent[] dossierServer = new NameComponent[] { new NameComponent(
					"Dossier", "service") };
			
			NameComponent[] infractionServer = new NameComponent[] { new NameComponent(
					"Infraction", "service") };
			
			dossier = BanqueDossiersHelper
					.narrow(nc.resolve(dossierServer));
			
			infraction = BanqueInfractionsHelper
					.narrow(nc.resolve(infractionServer));
			
			System.out.println("Client du poste: ");
			displayMainMenu();
			System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1, 2, 3 ou 4)");
			mainMenu();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void displayMainMenu(){
		System.out.println("1 - Ajouter un dossier à la banque de dossiers");
		System.out.println("2 - Ajouter une infraction à la banque d'infractions");
		System.out.println("3 - Visualiser la liste de l'ensemble des dossiers");
		System.out.println("4 - Visualiser la liste de l'ensemble des infractions");
	}
	
	private static void mainMenu(){
		
		selectedManager = integerSelector();
		
		while(selectedManager != 1 && selectedManager != 2 && selectedManager != 3 && selectedManager != 4){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = integerSelector();	
		}
		
		switch(selectedManager){
		case 1:
			displayAddFilesMenu();
			addFilesMenu();
			//enteredValue.close();
			break;
		case 2:
			displayAddInfractionsMenu();
			addInfractionsMenu();
			//enteredValue.close();
			break;
		case 3:
			displayFilesMenu();
			displayListFilesMenu();
			//enteredValue.close();
			break;
		case 4:
			displayInfractionsMenu();
			displayListInfractionsMenu();
			//enteredValue.close();
			break;	
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayMainMenu();
			selectedManager = integerSelector();
			break;
		}	
	}
	
	/***********************************************************************************************/
	private static void displayAddFilesMenu(){
		clearConsole();
		System.out.println("Gestionnaire d'ajout de dossiers");
		System.out.println("1 - Ajouter un dossier");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1)");
	}
	
	private static void addFilesMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayAddFilesMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
		case 1:
			addFileFunction();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayAddFilesMenu();
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void addFileFunction(){
		System.out.println("ADD FILE");
		
		String lastName = returnLastName();
		String firstName = returnFirstName();
		String carPlate = returnCarPlate();
		String driverLicenseNumber = returnDriverLicense();
		
		System.out.println("Prénom = " + firstName + " ||| Nom de famille = " + lastName + " ||| Plaque de la voiture = " + carPlate + " ||| Numéro du permis = " + driverLicenseNumber);
		//dossier.ajouterDossier(lastName, firstName, carPlate, driverLicenseNumber);
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
	private static void displayAddInfractionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire d'ajout d'infractions");
		System.out.println("1 - Ajouter une infraction à un dossier");
		System.out.println("2 - Ajouter une ou des infractions à la liste des infractions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1 ou 2)");
	}
	
	private static void addInfractionsMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1 && selectedIntegerValue != 2 ){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayAddInfractionsMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
		case 1:
			addInfractionToFileFunction();
			break;
		case 2:
			addInfractionsToInfractionsListFunction();
			break;	
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayAddInfractionsMenu();
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void addInfractionToFileFunction(){
		System.out.println("ADD INFRACTION TO FILE");
		
		int idFile = returnFileID();
		int idInfraction = returnInfractionID();
		
		System.out.println("Numéro d'identification du dossier = " + idFile + " ||| Numéro d'identification de l'infraction = " + idInfraction);
		//dossier.ajouterInfractionAuDossier(idFile, idInfraction);
		printInfractionList();
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
	
	private static int returnInfractionID(){
		System.out.println("Veuillez entrer le numéro d'identification de l'infraction :");
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
	
	private static void addInfractionsToInfractionsListFunction(){
		System.out.println("ADD INFRACTIONS TO INFRACTIONS LIST");
		
		String infractionName = returnInfractionName();
		int infractionGravity = returnInfractionGravity();
		
		System.out.println("Nom de l'infraction = " + infractionName + " ||| Niveau de l'infraction = " + infractionGravity);
		//infraction.ajouterInfraction(infractionName, infractionGravity);
		printInfractionList();
	}
	
	private static String returnInfractionName(){
		System.out.println("Veuillez entrer le nom de l'infraction:");
		String infractionName;
		
		selectedStringValue = "";
		selectedStringValue = stringSelector();
		while(selectedStringValue == ""){
			System.out.println("Entrée invalide!");
			returnInfractionName();
		}
		
		infractionName = selectedStringValue;
		
		return infractionName;
	}
	
	private static int returnInfractionGravity(){
		System.out.println("Veuillez entrer la gravité de l'infraction:");
		int infractionGravity;
		
		selectedIntegerValue = 0;
		selectedIntegerValue = integerSelector();
		while(selectedIntegerValue == 0){
			System.out.println("Entrée invalide!");
			returnInfractionGravity();
		}
		
		infractionGravity = selectedIntegerValue;
		
		return infractionGravity;
	}
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayFilesMenu(){
		clearConsole();
		System.out.println("Gestionnaire des dossiers");
		System.out.println("1 - Liste des dossiers");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1)");
	}
	
	private static void displayListFilesMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayFilesMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
		case 1:
			listFilesFunction();
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
	/***********************************************************************************************/
	
	/***********************************************************************************************/
	private static void displayInfractionsMenu(){
		clearConsole();
		System.out.println("Gestionnaire des infractions");
		System.out.println("1 - Liste des infractions");
		System.out.println("Veuillez sélectionner un choix à l'aide d'un chiffre (1)");
	}
	
	private static void displayListInfractionsMenu(){
		
		selectedIntegerValue = integerSelector();
		
		while(selectedIntegerValue != 1){
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayListInfractionsMenu();
			selectedIntegerValue = integerSelector();	
		}
		
		switch(selectedIntegerValue){
		case 1:
			listInfractionsFunction();
			break;
		default:
			System.out.println("Entrée invalide! Veuillez sélectionner l'une des options suivantes:");
			displayListInfractionsMenu();
			selectedIntegerValue = integerSelector();
			break;
		}
	}
	
	private static void listInfractionsFunction(){
		System.out.println("LIST INFRACTIONS");
		printInfractionList();
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
			return "";	
		}
	}
	
	private static void printFilesList(){
		CollectionDossier cdos = dossier.dossiers();
		for(int i = 0; i < cdos.size(); i++){
			Dossier dos = cdos.getDossier(i);
			System.out.println(dos.toString());	
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

package dossier;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionDossierPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.DossierHelper;

public class CollectionDossierImpl extends CollectionDossierPOA{
	
	private ArrayList<DossierImpl> dossierList;
	
	public CollectionDossierImpl() {
		dossierList = new ArrayList<DossierImpl>();
	}

	public Dossier getDossier(int index) {
		try {
			DossierImpl dossier = dossierList.get(index);
			POA rootpoa = Server._DossierPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(dossier);
			return DossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionDossier: " + e);
			return null;
		}
	}

	public int size() {
		return dossierList.size();
	}
	
	public ArrayList<DossierImpl> collectionDossier() {
		return dossierList;
	}
}

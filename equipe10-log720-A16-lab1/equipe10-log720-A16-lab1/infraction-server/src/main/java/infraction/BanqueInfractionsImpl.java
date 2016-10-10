package infraction;
import java.io.Serializable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueInfractionsPOA;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.CollectionInfractionHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;


public class BanqueInfractionsImpl extends BanqueInfractionsPOA implements Serializable{

	private CollectionInfractionImpl _collectionInfraction;

	public BanqueInfractionsImpl() {
		_collectionInfraction = new CollectionInfractionImpl();
	}

	public void ajouterInfraction(String description, int niveau)
			throws NiveauHorsBornesException {
		InfractionImpl infraction = new InfractionImpl(description,_collectionInfraction.size(), niveau);
		_collectionInfraction.InfractionList().add(infraction);		
	}

	public CollectionInfraction infractions() {
		try {
			POA rootpoa = Server._infractionPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(_collectionInfraction);
			return CollectionInfractionHelper.narrow(obj);
		} catch(Exception e) {
			System.out.println("Erreur retour de l'objet CollectionInfraction : " + e);
			return null;
		}

	}

	public Infraction trouverInfractionParId(int idInfraction) {
		try {
			return infractions().getInfraction(idInfraction);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionInfraction : " + e);
			return null;
		}
	}

	public CollectionInfraction trouverInfractionsParDossier(Dossier mydossier) {
		try {
			System.out.println("SADASDASDASDASDASDASDASDASDAS");

			CollectionInfractionImpl infractions = new CollectionInfractionImpl();
			for(Integer idInfraction: mydossier.getListeInfraction()) {
				infractions.InfractionList().add(_collectionInfraction.InfractionList().get(idInfraction));
			}
			POA rootpoa = Server._infractionPOA;
			org.omg.CORBA.Object obj;
			
			obj = rootpoa
					.servant_to_reference(infractions);
			return CollectionInfractionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionInfraction : " + e);
			return null;
		} 
	}

}

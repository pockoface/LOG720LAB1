package dossier;
import java.io.Serializable;
import java.util.Hashtable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueDossiersPOA;
import ca.etsmtl.log720.lab1.CollectionDossier;
import ca.etsmtl.log720.lab1.CollectionDossierHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.DossierHelper;
import ca.etsmtl.log720.lab1.InvalidIdException;
import ca.etsmtl.log720.lab1.NoPermisExisteDejaException;

public class BanqueDossiersImpl extends BanqueDossiersPOA implements Serializable{
	private CollectionDossierImpl _collectionDossierImpl;
	private Hashtable<String,String> _plaqueList;

	public BanqueDossiersImpl() {
		_collectionDossierImpl = new CollectionDossierImpl();
		_plaqueList = new Hashtable<String,String>();
	}

	public void ajouterDossier(String nom, String prenom, String permis, String noPlaque) throws NoPermisExisteDejaException{
		if(_plaqueList.containsKey(permis)){
			throw new NoPermisExisteDejaException();
		}
		_plaqueList.put(permis, permis);
		_collectionDossierImpl.collectionDossier().add(new DossierImpl(_collectionDossierImpl.size(), 0, nom, prenom, permis, noPlaque));
	}

	public void ajouterInfractionAuDossier(int idDossier, int idInfration) throws InvalidIdException {
		dossiers().getDossier(idDossier).ajouterInfractionAListe(idInfration);
	}

	public void ajouterReactionAuDossier(int idDossier, int idReaction) throws InvalidIdException {
		dossiers().getDossier(idDossier).ajouterReactionAListe(idReaction);
		
	}

	public CollectionDossier dossiers() {
		try {
			POA rootpoa = Server._DossierPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(_collectionDossierImpl);
			return CollectionDossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionDossier : " + e);
			return null;
		}
	}

	public Dossier trouverDossierParId(int idDossier) {
		try {
			DossierImpl dossier = null;
			for(DossierImpl dos: _collectionDossierImpl.collectionDossier()) {
				if(dos.id() == idDossier) {
					dossier = dos;
				}
			}
			
			if(dossier == null) { throw new Exception(); }
			
			 POA rootpoa = Server._DossierPOA;
				org.omg.CORBA.Object obj = rootpoa
						.servant_to_reference(dossier);
			return DossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur dossier inexistant: " + idDossier);
			return null;
		}

	}

	public Dossier trouverDossierParPermis(String idPermis) {
		try {
			DossierImpl dossier = null;
			for(DossierImpl dos: _collectionDossierImpl.collectionDossier()) {
				if(dos.noPermis().equalsIgnoreCase(idPermis)) {
					dossier = dos;
				}
			}
			
			if(dossier == null) { throw new Exception(); }
			
			 POA rootpoa = Server._DossierPOA;
				org.omg.CORBA.Object obj = rootpoa
						.servant_to_reference(dossier);
			return DossierHelper.narrow(obj);
			
		} catch (Exception e) {
			System.out.println("Erreur permis inexistant: " + idPermis);
			return null;	
		}
		
	}

	public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
		try {
			 CollectionDossierImpl collectionDossier = new CollectionDossierImpl();
			 for(DossierImpl dos: _collectionDossierImpl.collectionDossier()) {
				 if(dos.nom().equalsIgnoreCase(nom) && dos.prenom().equalsIgnoreCase(prenom)) {
					 collectionDossier.collectionDossier().add(dos);
				 }
			 }
			 
			 if(collectionDossier.collectionDossier().isEmpty()) { throw new Exception(); }
			 
			 POA rootpoa = Server._DossierPOA;
				org.omg.CORBA.Object obj = rootpoa
						.servant_to_reference(collectionDossier);
			return CollectionDossierHelper.narrow(obj);
			 
		} catch (Exception e) {
			System.out.println("Erreur nom et prenome inexistant: " + nom + " " + prenom);
			return null;	
		}
	}

	public CollectionDossier trouverDossiersParPlaque(String idPlaque) {
		try {
			CollectionDossierImpl collectionDossier = new CollectionDossierImpl();
			for(DossierImpl dos: _collectionDossierImpl.collectionDossier()) {
				if(dos.noPlaque().equalsIgnoreCase(idPlaque)){
					collectionDossier.collectionDossier().add(dos);
				}
			}
			
			if(collectionDossier.collectionDossier().isEmpty()) { throw new Exception(); }
			
			POA rootpoa = Server._DossierPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(collectionDossier);
			return CollectionDossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur la plaque est inexistant: " + idPlaque);
			return null;
		}
	}

}

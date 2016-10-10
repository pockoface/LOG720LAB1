package dossier;
import java.io.Serializable;
import java.util.ArrayList;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ca.etsmtl.log720.lab1.BanqueInfractions;
import ca.etsmtl.log720.lab1.BanqueInfractionsHelper;
import ca.etsmtl.log720.lab1.DossierPOA;

public class DossierImpl extends DossierPOA implements Serializable{
	
	private int _id;
	private int _niveau;
	private String _nom;
	private String _prenom;
	private String _noPermis;
	private String _noPlaque;
	private ArrayList<Integer> _infractions;
	private ArrayList<Integer> _reactions;

	public DossierImpl(int _id, int _niveau, String _nom, String _prenom, String _noPermis, String _noPlaque) {
		super();
		this._id = _id;
		this._niveau = _niveau;
		this._nom = _nom;
		this._prenom = _prenom;
		this._noPermis = _noPermis;
		this._noPlaque = _noPlaque;
		_reactions = new ArrayList<Integer>();
		_infractions = new ArrayList<Integer>();
	}	
	
	public String _toString() {
		return "id: " + _id + "\nniveau: " + _niveau + "\nnom: " + _nom + "\nprenom: " + _prenom + "\nnoPermis: " + _noPermis + "\nnoPlaque: " + _noPlaque;
	}
		
	public void ajouterInfractionAListe(int idInfraction) {
		System.out.println("Infraction id: " + idInfraction + " au dossier " + _id );
		_infractions.add(idInfraction);
		evaluateFolderLevel();
	}

	private void evaluateFolderLevel() {
		_niveau = Integer.MIN_VALUE;
		try{
			NamingContextExt nc = NamingContextExtHelper.narrow(Server.orb
					.resolve_initial_references("NameService"));
			NameComponent[] name = new NameComponent[] { new NameComponent(
					"Infraction", "service") };
			BanqueInfractions binfraction = BanqueInfractionsHelper.narrow(nc.resolve(name));
			
			for(Integer _infractionId: _infractions){
				if(binfraction.trouverInfractionParId(_infractionId).niveau() > _niveau){
					_niveau = binfraction.trouverInfractionParId(_infractionId).niveau();
				}
			}
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet BanqieInfractions : " + e);
		}
	}

	public void ajouterReactionAListe(int idReaction) {
		System.out.println("Reaction id: " + idReaction + " au dossier " + _id );
		_reactions.add(idReaction);
	}

	public int[] getListeInfraction() {
		return arraylistToInt(_infractions);
	}

	public int[] getListeReaction() {
		return arraylistToInt(_reactions);
	}

	public int id() {
		return _id;
	}

	public int niveau() {
		return _niveau;
	}

	public String noPermis() {
		return _noPermis;
	}

	public String noPlaque() {
		return _noPlaque;
	}

	public String nom() {
		return _nom;
	}

	public String prenom() {
		return _prenom;
	}
	
	private int[] arraylistToInt(ArrayList<Integer> e) {
		int[] result = new int[e.size()];
		for(int i = 0; i < e.size(); i++) {
			result[i] = e.get(i);
		}
		return result;
	}

}

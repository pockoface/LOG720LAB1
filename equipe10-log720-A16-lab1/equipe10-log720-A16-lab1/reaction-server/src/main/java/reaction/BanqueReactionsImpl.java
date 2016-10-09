package reaction;
import java.io.Serializable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueReactionsPOA;
import ca.etsmtl.log720.lab1.CollectionReaction;
import ca.etsmtl.log720.lab1.CollectionReactionHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Reaction;

public class BanqueReactionsImpl extends BanqueReactionsPOA implements Serializable{
	
	CollectionReactionImpl _collectionReaction;

	public BanqueReactionsImpl() {
		super();
		_collectionReaction = new CollectionReactionImpl();
	}

	public void ajouterReaction(String reaction, int gravite) {
		_collectionReaction.reactionList().add(new ReactionImpl(_collectionReaction.size(), gravite, reaction));
	}

	public CollectionReaction reactions() {
		try {
			POA rootpoa = Server._ReactionPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(_collectionReaction);
			return CollectionReactionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionReaction : " + e);
			return null;
		}
	}

	public Reaction trouverReactionParId(int idReaction) {
		try {
			return reactions().getReaction(idReaction);
		} catch (Exception e) {
			System.out.println("Erreur reaction inexistant: " + idReaction);
			return null;
		}
	}

	public CollectionReaction trouverReactionsParDossier(Dossier mydossier) {
		try {
			CollectionReactionImpl reactions = new CollectionReactionImpl();
			for(Integer idReaction: mydossier.getListeReaction()) {
				reactions.reactionList().add(_collectionReaction.reactionList().get(idReaction));
			}
			POA rootpoa = Server._ReactionPOA;
			org.omg.CORBA.Object obj;

			obj = rootpoa
					.servant_to_reference(reactions);
			return CollectionReactionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionInfraction : " + e);
			return null;
		} 
	}

}

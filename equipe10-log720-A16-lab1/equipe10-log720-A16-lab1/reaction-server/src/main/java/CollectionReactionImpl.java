import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionReactionPOA;
import ca.etsmtl.log720.lab1.Reaction;
import ca.etsmtl.log720.lab1.ReactionHelper;

public class CollectionReactionImpl extends CollectionReactionPOA{
	
	private ArrayList<ReactionImpl> reactionList;
	
	public CollectionReactionImpl() {
		reactionList = new ArrayList<ReactionImpl>();
	}

	public Reaction getReaction(int index) {
		try {
			ReactionImpl reaction = reactionList.get(index);
			POA rootpoa = Server._ReactionPOA;
			org.omg.CORBA.Object obj = rootpoa
					.servant_to_reference(reaction);
			return ReactionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionDossier: " + e);
			return null;
		}
	}

	public int size() {
		return reactionList.size();
	}
	
	public ArrayList<ReactionImpl> reactionList() {
		return reactionList;
	}

}

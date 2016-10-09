package infraction;
import java.io.Serializable;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionInfractionPOA;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.InfractionHelper;;

public class CollectionInfractionImpl extends CollectionInfractionPOA implements Serializable{
	
	private ArrayList<InfractionImpl> _infractionList;
	
	public CollectionInfractionImpl() {
		_infractionList = new ArrayList<InfractionImpl>();
	}
	
	public Infraction getInfraction(int index) {
		try {
			InfractionImpl infraction = _infractionList.get(index);
			POA rootpoa = Server._infractionPOA;
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(infraction);
			return InfractionHelper.narrow(obj);
		} catch(Exception e) {
			System.out.println("Erreur retour de l'objet infraction : " + e);
			return null;
		}
	}

	public int size() {
		return _infractionList.size();
	}
	
	public ArrayList<InfractionImpl> InfractionList() {
		return _infractionList;
	}

}

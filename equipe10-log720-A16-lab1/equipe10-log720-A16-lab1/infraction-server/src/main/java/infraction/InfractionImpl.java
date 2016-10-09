package infraction;
import ca.etsmtl.log720.lab1.InfractionPOA;

public class InfractionImpl extends InfractionPOA{

	private String _description;
	private int _id;
	private int _niveau;
	
	public InfractionImpl(String _description, int _id, int _niveau) {
		super();
		this._description = _description;
		this._id = _id;
		this._niveau = _niveau;
	}

	public String _toString() {
		return "infraction.id: " + _id + " infraction.niveau: " + _niveau + " infraction.description: " + _description;
	}

	public String description() {
		return _description;
	}

	public int id() {
		return _id;
	}

	public int niveau() {
		return _niveau;
	}
	
	public void description(String arg) {
		_description = arg;
		System.out.println("Changement de infraction.description a: " + _description);
	}

	public void id(int arg) {
		_id = arg;
		System.out.println("Changement de infraction.id a: " + _id);
	}

	public void niveau(int arg) {
		_niveau = arg;
		System.out.println("Changement de infraction.niveau a: " + _niveau);
	}

}

package reaction;
import ca.etsmtl.log720.lab1.ReactionPOA;

public class ReactionImpl extends ReactionPOA{
	
	private int _id;
	private int _niveau;
	private String _description;
	
	public ReactionImpl(int _id, int _niveau, String _description) {
		super();
		this._id = _id;
		this._niveau = _niveau;
		this._description = _description;
	}
	
	public String _toString() {
		return " id: " + _id + " niveau: " + _niveau + " description: " + _description;
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

}
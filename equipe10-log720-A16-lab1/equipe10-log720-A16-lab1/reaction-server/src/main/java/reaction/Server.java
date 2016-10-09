package reaction;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Server {
	protected static org.omg.PortableServer.POA _ReactionPOA;
	private final static String FILE_NAME = "reaction.sab";
	private static org.omg.PortableServer.Servant servant;

	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			
			org.omg.CORBA.Object o;

			// Initialize POA
			_ReactionPOA = org.omg.PortableServer.POAHelper
					.narrow(orb.resolve_initial_references("RootPOA"));
			_ReactionPOA.the_POAManager().activate();

			// Initialize servant (Remote Object), convert to CORBA reference
			servant = load();
			o = _ReactionPOA.servant_to_reference(servant);

			// Register Rermote Object with naming context
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name = new NameComponent[] { new NameComponent(
					"Reaction", "service") };
			nc.rebind(name, o);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				 try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
					out.writeObject(servant);
					out.close();
					System.out.println("Instance de BanqueReactionsImpl sauvegarder");
				} catch (Exception e) {
					System.out.println("[ERREUR]Instance de BanqueReactionsImpl non sauvegarder");
				}
				 
			}
		});
		
		// Start the ORB main thread
		orb.run();
	}
	
	private static BanqueReactionsImpl load(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
			BanqueReactionsImpl bri = (BanqueReactionsImpl) in.readObject();
			in.close();
			System.out.println("Instance de BanqueReactionsImpl charger");
			return bri;
		} catch (Exception e) {
			System.out.println("Nouvelle instance de BanqueReactionsImpl");
			return new BanqueReactionsImpl();
		}
	}
}

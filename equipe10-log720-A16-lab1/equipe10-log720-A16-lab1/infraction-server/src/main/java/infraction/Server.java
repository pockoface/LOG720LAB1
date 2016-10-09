package infraction;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Server {
	protected static org.omg.PortableServer.POA _infractionPOA;
	private final static String FILE_NAME = "infraction.sab";
	private static 	org.omg.PortableServer.Servant servant;


	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			org.omg.CORBA.Object o;

			// Initialize POA
			_infractionPOA = org.omg.PortableServer.POAHelper
					.narrow(orb.resolve_initial_references("RootPOA"));
			_infractionPOA.the_POAManager().activate();

			// Initialize servant (Remote Object), convert to CORBA reference
			servant = load();
			o = _infractionPOA.servant_to_reference(servant);

			// Register Rermote Object with naming context
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name = new NameComponent[] { new NameComponent(
					"Infraction", "service") };
			nc.rebind(name, o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Start the ORB main thread
		orb.run();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				 try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
					out.writeObject(servant);
					out.close();
					System.out.println("Instance de BanqueInfractionsImpl non sauvegarder");
				} catch (Exception e) {
					System.out.println("[ERREUR]Instance de BanqueInfractionsImpl non sauvegarder");
				}
				 
			}
		});
	}
	
	private static BanqueInfractionsImpl load(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
			BanqueInfractionsImpl bri = (BanqueInfractionsImpl) in.readObject();
			in.close();
			System.out.println("Instance de BanqueInfractionsImpl charger");
			return bri;
		} catch (Exception e) {
			System.out.println("Nouvelle instance de BanqueInfractionsImpl");
			return new BanqueInfractionsImpl();
		}
	}
}

package org.agetac.server.db;

import java.io.File;

import org.agetac.common.model.impl.*;
import org.agetac.common.model.impl.Vehicule.CategorieVehicule;
import org.agetac.common.model.impl.Vehicule.EtatVehicule;

public class DbUtils {
	public static void populateTestDb() {
		deleteDir(new File("db"));

		// Create a sample intervention.
		Intervention inter = new Intervention();
		PersistenceManagerProxy.getInstance().put(inter);

		for (int i = 0; i < 10; i++) {
			Vehicule v = new Vehicule(new Position(0, 0),
					CategorieVehicule.BEA, "Rennes", EtatVehicule.ALERTE,
					new Groupe(), "");

			PersistenceManagerProxy.getInstance().put(v);
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}
}

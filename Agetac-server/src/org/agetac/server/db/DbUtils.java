package org.agetac.server.db;

import java.io.File;

import javax.jdo.JDOHelper;
import javax.jdo.ObjectState;

import org.agetac.common.model.impl.*;
import org.agetac.common.model.impl.Vehicule.CategorieVehicule;
import org.agetac.common.model.impl.Vehicule.EtatVehicule;

public class DbUtils {
	public static void populateTestDb() {
		deleteDir(new File("db"));

		// Create a sample intervention.
		Intervention inter = new Intervention();

		// Create some vehicles and attach them to the intervention.
		Vehicule v = new Vehicule(new Position(0, 0), CategorieVehicule.BEA,
				"Rennes", EtatVehicule.ALERTE, new Groupe(), "");

		inter.getVehicules().add(v);

		// Persist the intervention and the vehicle
		// (persistence-by-reachability).
		SimpleDAO.getInstance().add(inter);

		// Create some independent vehicles.
		for (int i = 0; i < 10; i++) {
			Vehicule vehicule = new Vehicule(new Position(0, 0),
					CategorieVehicule.BEA, "Rennes",
					EtatVehicule.DISPO_CASERNE, new Groupe(), "");

			SimpleDAO.getInstance().add(vehicule);
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

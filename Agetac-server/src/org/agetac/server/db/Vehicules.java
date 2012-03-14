package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.common.model.impl.Vehicule;



/**
 * Représentation mémoire de tous les véhicules
 * Aucun stockage persistant.
 * Cette base de donnée doit être thread-safe. 
 */

public class Vehicules {
	  /** Table des intervention indicés par leur uniqueID */
	  private Map<String, Vehicule> uniqueID2Vehicule = new ConcurrentHashMap<String, Vehicule>();
	  
	  /** Singleton */
	  private static Vehicules theInstance = new Vehicules();
	  
	  /** Assure qu'aucun client ne puisse instancier cette classe */
	  private Vehicules() {
	    // do nothing. 
	  }
	  
	  /** 
	   * Retourne l'instance du singleton.
	   * @return L'instance de Vehicules.
	   */
	  public static Vehicules getInstance() {
	    return theInstance;
	  }
	  
	  /**
	   * Ajoute une intervention dans le dépos.
	   * Note : Ecrase l'ancien agent si la clé est déjà présente.
	   * @param agent L'agent à ajouter.
	   */
	  public synchronized void addVehicule(Vehicule v) {
		  uniqueID2Vehicule.put(v.getUniqueID(), v);
		  System.out.println("DB add :" + this.getVehicules().toString());
	  }
	  
	  /**
	   * Retourne l'instance d'Agent associé au uniqueID s'il est présent, null si non trouvé.
	   * @param uniqueID L'uniqueID à trouver
	   * @return L'instance de l'Agent ou null.
	   */
	  public synchronized Vehicule getVehicule(String uniqueID) {

		  System.out.println(this.getVehicules().toString());
	    return uniqueID2Vehicule.get(uniqueID);
	    
	  }
	  
	  /**
	   * Assure que l'intervention associer a uniqueID n'est plus présente dans le dépos. 
	   * @param uniqueID l'uniqueID a supprimer si présent.
	   */
	  public synchronized void deleteAgent(String uniqueID) {
		  uniqueID2Vehicule.remove(uniqueID);

		  System.out.println(this.getVehicules().toString());
	  }


	  /**
	   * Retourne les valeurs de la collection a cet instant.
	   * @return Collection d'instance d'intervention  
	   */
	  public synchronized Collection<Vehicule> getVehicules () {
	    return uniqueID2Vehicule.values();
	  }

}

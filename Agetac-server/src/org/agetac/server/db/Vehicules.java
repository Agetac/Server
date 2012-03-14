package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.common.model.impl.Vehicule;



/**
 * Repr�sentation m�moire de tous les v�hicules
 * Aucun stockage persistant.
 * Cette base de donn�e doit �tre thread-safe. 
 */

public class Vehicules {
	  /** Table des intervention indic�s par leur uniqueID */
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
	   * Ajoute une intervention dans le d�pos.
	   * Note : Ecrase l'ancien agent si la cl� est d�j� pr�sente.
	   * @param agent L'agent � ajouter.
	   */
	  public synchronized void addVehicule(Vehicule v) {
		  uniqueID2Vehicule.put(v.getUniqueID(), v);
		  System.out.println("DB add :" + this.getVehicules().toString());
	  }
	  
	  /**
	   * Retourne l'instance d'Agent associ� au uniqueID s'il est pr�sent, null si non trouv�.
	   * @param uniqueID L'uniqueID � trouver
	   * @return L'instance de l'Agent ou null.
	   */
	  public synchronized Vehicule getVehicule(String uniqueID) {

		  System.out.println(this.getVehicules().toString());
	    return uniqueID2Vehicule.get(uniqueID);
	    
	  }
	  
	  /**
	   * Assure que l'intervention associer a uniqueID n'est plus pr�sente dans le d�pos. 
	   * @param uniqueID l'uniqueID a supprimer si pr�sent.
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

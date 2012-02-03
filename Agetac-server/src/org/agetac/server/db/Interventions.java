package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Intervention;


/**
 * Représentation mémoire de toutes les interventions
 * Aucun stockage persistant.
 * Cette base de donnée doit être thread-safe. 
 */

public class Interventions {
  /** Table des intervention indicés par leur uniqueID */
  private Map<String, Intervention> uniqueID2Intervention = new ConcurrentHashMap<String, Intervention>();
  
  /** Singleton */
  private static Interventions theInstance = new Interventions();
  
  /** Assure qu'aucun client ne puisse instancier cette classe */
  private Interventions() {
    // do nothing. 
  }
  
  /** 
   * Retourne l'instance du singleton.
   * @return L'instance de Interventions.
   */
  public static Interventions getInstance() {
    return theInstance;
  }
  
  /**
   * Ajoute une intervention dans le dépos.
   * Note : Ecrase l'ancienne intervention si la clé est déjà présente.
   * @param intervention L'intervention à ajouter.
   */
  public synchronized void addIntervention(Intervention intervention) {
	  uniqueID2Intervention.put(intervention.getUniqueID(), intervention);
  }
  
  /**
   * Retourne l'instance d'intervention associé au uniqueID s'il est présent, null si non trouvé.
   * @param uniqueID L'uniqueID à trouver
   * @return L'instance de l'intervention ou null.
   */
  public synchronized Intervention getIntervention(String uniqueID) {
    return uniqueID2Intervention.get(uniqueID);
  }
  
  /**
   * Assure que l'intervention associer a uniqueID n'est plus présente dans le dépos. 
   * @param uniqueID l'uniqueID a supprimer si présent.
   */
  public synchronized void deleteIntervention(String uniqueID) {
	  uniqueID2Intervention.remove(uniqueID);
  }


  /**
   * Retourne les valeurs de la collection a cet instant.
   * @return Collection d'instance d'intervention  
   */
  public synchronized Collection<Intervention> getInterventions () {
    return uniqueID2Intervention.values();
  }
}

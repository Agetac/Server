package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Intervention;


/**
 * Repr�sentation m�moire de toutes les interventions
 * Aucun stockage persistant.
 * Cette base de donn�e doit �tre thread-safe. 
 */

public class Interventions {
  /** Table des intervention indic�s par leur uniqueID */
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
   * Ajoute une intervention dans le d�pos.
   * Note : Ecrase l'ancienne intervention si la cl� est d�j� pr�sente.
   * @param intervention L'intervention � ajouter.
   */
  public synchronized void addIntervention(Intervention intervention) {
	  uniqueID2Intervention.put(intervention.getUniqueID(), intervention);
  }
  
  /**
   * Retourne l'instance d'intervention associ� au uniqueID s'il est pr�sent, null si non trouv�.
   * @param uniqueID L'uniqueID � trouver
   * @return L'instance de l'intervention ou null.
   */
  public synchronized Intervention getIntervention(String uniqueID) {
    return uniqueID2Intervention.get(uniqueID);
  }
  
  /**
   * Assure que l'intervention associer a uniqueID n'est plus pr�sente dans le d�pos. 
   * @param uniqueID l'uniqueID a supprimer si pr�sent.
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

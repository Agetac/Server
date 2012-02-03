package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Agent;



/**
 * Représentation mémoire de tous les agents
 * Aucun stockage persistant.
 * Cette base de donnée doit être thread-safe. 
 */

public class Agents {
  /** Table des intervention indicés par leur uniqueID */
  private Map<String, Agent> uniqueID2Agent = new ConcurrentHashMap<String, Agent>();
  
  /** Singleton */
  private static Agents theInstance = new Agents();
  
  /** Assure qu'aucun client ne puisse instancier cette classe */
  private Agents() {
    // do nothing. 
  }
  
  /** 
   * Retourne l'instance du singleton.
   * @return L'instance de Agents.
   */
  public static Agents getInstance() {
    return theInstance;
  }
  
  /**
   * Ajoute une intervention dans le dépos.
   * Note : Ecrase l'ancien agent si la clé est déjà présente.
   * @param agent L'agent à ajouter.
   */
  public synchronized void addAgent(Agent agent) {
	  uniqueID2Agent.put(agent.getUniqueID(), agent);
	  System.out.println("DB add :" + this.getAgents().toString());
  }
  
  /**
   * Retourne l'instance d'Agent associé au uniqueID s'il est présent, null si non trouvé.
   * @param uniqueID L'uniqueID à trouver
   * @return L'instance de l'Agent ou null.
   */
  public synchronized Agent getAgent(String uniqueID) {

	  System.out.println(this.getAgents().toString());
    return uniqueID2Agent.get(uniqueID);
    
  }
  
  /**
   * Assure que l'intervention associer a uniqueID n'est plus présente dans le dépos. 
   * @param uniqueID l'uniqueID a supprimer si présent.
   */
  public synchronized void deleteAgent(String uniqueID) {
	  uniqueID2Agent.remove(uniqueID);

	  System.out.println(this.getAgents().toString());
  }


  /**
   * Retourne les valeurs de la collection a cet instant.
   * @return Collection d'instance d'intervention  
   */
  public synchronized Collection<Agent> getAgents () {
    return uniqueID2Agent.values();
  }
}

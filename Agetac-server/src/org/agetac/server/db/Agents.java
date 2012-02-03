package org.agetac.server.db;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Agent;



/**
 * Repr�sentation m�moire de tous les agents
 * Aucun stockage persistant.
 * Cette base de donn�e doit �tre thread-safe. 
 */

public class Agents {
  /** Table des intervention indic�s par leur uniqueID */
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
   * Ajoute une intervention dans le d�pos.
   * Note : Ecrase l'ancien agent si la cl� est d�j� pr�sente.
   * @param agent L'agent � ajouter.
   */
  public synchronized void addAgent(Agent agent) {
	  uniqueID2Agent.put(agent.getUniqueID(), agent);
	  System.out.println("DB add :" + this.getAgents().toString());
  }
  
  /**
   * Retourne l'instance d'Agent associ� au uniqueID s'il est pr�sent, null si non trouv�.
   * @param uniqueID L'uniqueID � trouver
   * @return L'instance de l'Agent ou null.
   */
  public synchronized Agent getAgent(String uniqueID) {

	  System.out.println(this.getAgents().toString());
    return uniqueID2Agent.get(uniqueID);
    
  }
  
  /**
   * Assure que l'intervention associer a uniqueID n'est plus pr�sente dans le d�pos. 
   * @param uniqueID l'uniqueID a supprimer si pr�sent.
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

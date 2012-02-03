package org.agetac.server.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Caserne;
import org.agetac.observer.Observer;
import org.agetac.observer.Subject;

public class Casernes implements Subject{
	/** Liste des observer **/
	private Collection<Observer> observers = new ArrayList<Observer>();
	
	/** Table des intervention indicés par leur uniqueID */
	private Map<String, Caserne> uniqueID2Caserne = new ConcurrentHashMap<String, Caserne>();

	/** Singleton */
	private static Casernes theInstance = new Casernes();

	/** Assure qu'aucun client ne puisse instancier cette classe */
	private Casernes() {
		// do nothing.
	}

	/**
	 * Retourne l'instance du singleton.
	 * 
	 * @return L'instance de Casernes.
	 */
	public static Casernes getInstance() {
		return theInstance;
	}

	/**
	 * Ajoute une intervention dans le dépos. Note : Ecrase l'ancien caserne si
	 * la clé est déjà présente.
	 * 
	 * @param caserne
	 *            L'caserne à ajouter.
	 */
	public synchronized void addCaserne(Caserne caserne) {
		uniqueID2Caserne.put(caserne.getUniqueID(), caserne);
		notifyObserver();
	}

	/**
	 * Retourne l'instance d'Caserne associé au uniqueID s'il est présent, null
	 * si non trouvé.
	 * 
	 * @param uniqueID
	 *            L'uniqueID à trouver
	 * @return L'instance de l'Caserne ou null.
	 */
	public synchronized Caserne getCaserne(String uniqueID) {
		return uniqueID2Caserne.get(uniqueID);
	}

	/**
	 * Assure que l'intervention associer a uniqueID n'est plus présente dans le
	 * dépos.
	 * 
	 * @param uniqueID
	 *            l'uniqueID a supprimer si présent.
	 */
	public synchronized void deleteCaserne(String uniqueID) {
		uniqueID2Caserne.remove(uniqueID);
		notifyObserver();
	}

	/**
	 * Retourne les valeurs de la collection a cet instant.
	 * 
	 * @return Collection d'instance d'intervention
	 */
	public synchronized List<Caserne> getCasernes() {
		List<Caserne> lm = new ArrayList<Caserne>();
		Iterator<Caserne> it = uniqueID2Caserne.values().iterator();
		while(it.hasNext()){
			lm.add(it.next());
		}
		return lm;
	}

	@Override
	public void attach(Observer o) { observers.add(o); }
	@Override
	public void detach(Observer o) { observers.remove(o); }
	@Override
	public void notifyObserver() {
		for(Observer o:observers){
			o.update(this);
		}
	}
}

package org.agetac.server.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.model.impl.Source;

public class Sources{
	
	
	/** Table des intervention indicés par leur uniqueID */
	private Map<String, Source> uniqueID2Source = new ConcurrentHashMap<String, Source>();

	/** Singleton */
	private static Sources theInstance = new Sources();

	/** Assure qu'aucun client ne puisse instancier cette classe */
	private Sources() {
		// do nothing.
	}

	/**
	 * Retourne l'instance du singleton.
	 * 
	 * @return L'instance de Messages.
	 */
	public static Sources getInstance() {
		return theInstance;
	}

	/**
	 * Ajoute une intervention dans le dépos. Note : Ecrase l'ancien message si
	 * la clé est déjà présente.
	 * 
	 * @param message
	 *            L'message à ajouter.
	 */
	public synchronized void addSource(Source source) {
		uniqueID2Source.put(source.getUniqueID(), source);
	}

	/**
	 * Retourne l'instance d'Message associé au uniqueID s'il est présent, null
	 * si non trouvé.
	 * 
	 * @param uniqueID
	 *            L'uniqueID à trouver
	 * @return L'instance de l'Message ou null.
	 */
	public synchronized Source getSource(String uniqueID) {

		System.out.println(this.getSources().toString());
		
		return uniqueID2Source.get(uniqueID);

	}

	/**
	 * Assure que l'intervention associer a uniqueID n'est plus présente dans le
	 * dépos.
	 * 
	 * @param uniqueID
	 *            l'uniqueID a supprimer si présent.
	 */
	public synchronized void deleteSource(String uniqueID) {
		uniqueID2Source.remove(uniqueID);
	}

	/**
	 * Retourne les valeurs de la collection a cet instant.
	 * 
	 * @return Collection d'instance d'intervention
	 */
	public synchronized List<Source> getSources() {
		List<Source> ls = new ArrayList<Source>();
		Iterator<Source> it = uniqueID2Source.values().iterator();
		while(it.hasNext()){
			ls.add(it.next());
		}
		return ls;
	}

}

package org.agetac.server.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.agetac.common.Message;
import org.agetac.observer.Observer;
import org.agetac.observer.Subject;

/**
 * Repr�sentation m�moire de tous les messages Aucun stockage persistant. Cette
 * base de donn�e doit �tre thread-safe.
 */

public class Messages implements Subject {
	
	/** Liste des observer **/
	private Collection<Observer> observers = new ArrayList<Observer>();
	
	/** Table des intervention indic�s par leur uniqueID */
	private Map<String, Message> uniqueID2Message = new ConcurrentHashMap<String, Message>();

	/** Singleton */
	private static Messages theInstance = new Messages();

	/** Assure qu'aucun client ne puisse instancier cette classe */
	private Messages() {
		// do nothing.
	}

	/**
	 * Retourne l'instance du singleton.
	 * 
	 * @return L'instance de Messages.
	 */
	public static Messages getInstance() {
		return theInstance;
	}

	/**
	 * Ajoute une intervention dans le d�pos. Note : Ecrase l'ancien message si
	 * la cl� est d�j� pr�sente.
	 * 
	 * @param message
	 *            L'message � ajouter.
	 */
	public synchronized void addMessage(Message message) {
		uniqueID2Message.put(message.getUniqueID(), message);
		notifyObserver();
		System.out.println("DB add :" + this.getMessages().toString());
	}

	/**
	 * Retourne l'instance d'Message associ� au uniqueID s'il est pr�sent, null
	 * si non trouv�.
	 * 
	 * @param uniqueID
	 *            L'uniqueID � trouver
	 * @return L'instance de l'Message ou null.
	 */
	public synchronized Message getMessage(String uniqueID) {

		System.out.println(this.getMessages().toString());
		
		return uniqueID2Message.get(uniqueID);

	}

	/**
	 * Assure que l'intervention associer a uniqueID n'est plus pr�sente dans le
	 * d�pos.
	 * 
	 * @param uniqueID
	 *            l'uniqueID a supprimer si pr�sent.
	 */
	public synchronized void deleteMessage(String uniqueID) {
		uniqueID2Message.remove(uniqueID);
		notifyObserver();
		System.out.println(this.getMessages().toString());
	}

	/**
	 * Retourne les valeurs de la collection a cet instant.
	 * 
	 * @return Collection d'instance d'intervention
	 */
	public synchronized List<Message> getMessages() {
		List<Message> lm = new ArrayList<Message>();
		Iterator<Message> it = uniqueID2Message.values().iterator();
		while(it.hasNext()){
			lm.add(it.next());
		}
		return lm;
	}

	@Override
	public void attach(Observer o) {
		observers.add(o);

	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);

	}

	@Override
	public void notifyObserver() {
		for(Observer o:observers){
			o.update(this);
		}
	}
}

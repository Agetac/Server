package org.agetac.client.api;

import java.util.List;
import org.agetac.client.exception.BadResponseException;
import org.agetac.model.exception.InvalidJSONException;
import org.agetac.model.impl.Action;
import org.agetac.model.impl.Cible;
import org.agetac.model.impl.Implique;
import org.agetac.model.impl.Message;
import org.agetac.model.impl.Source;
import org.agetac.model.impl.Vehicule;
import org.json.JSONException;

public interface InterventionApi {

	public Message getMessage(String msgId) throws BadResponseException;
	public List<Message> getMessages() throws BadResponseException;
	public void putMessage(Message msg) throws JSONException, BadResponseException;
	public void postMessage(Message msg) throws JSONException, BadResponseException;
	public void deleteMessage(Message msg) throws BadResponseException;
	

	public Vehicule getVehicule(String vId) throws BadResponseException;
	public List<Vehicule> getVehicules() throws BadResponseException;
	public void putVehicule(Vehicule v) throws BadResponseException;
	public void postVehicule(Vehicule v) throws JSONException, BadResponseException;
	public void deleteVehicule(Vehicule v) throws BadResponseException;

	public Source getSource(String sId) throws BadResponseException;
	public List<Source> getSources() throws BadResponseException;
	public void putSource(Source s) throws JSONException, BadResponseException;
	public void postSource(Source s) throws JSONException, BadResponseException;
	public void deleteSource(Source s) throws BadResponseException;
	

	public Cible getCible(String cId) throws InvalidJSONException, BadResponseException;
	public List<Cible> getCibles() throws BadResponseException;
	public void putCible(Cible c) throws JSONException, BadResponseException;
	public void postCible(Cible c) throws JSONException, BadResponseException;
	public void deleteCible(Cible c) throws BadResponseException;
	
	public Action getAction(String aId) throws InvalidJSONException, BadResponseException;
	public List<Action> getActions() throws BadResponseException;
	public void putAction(Action a) throws JSONException, BadResponseException;
	public void postAction(Action c) throws JSONException, BadResponseException;
	public void deleteAction(Action a) throws BadResponseException;
	
	public Implique getImplique(String iId) throws InvalidJSONException, BadResponseException;
	public List<Implique> getImpliques() throws BadResponseException;
	public void putImplique(Implique i) throws JSONException, BadResponseException;
	public void postImplique(Implique c) throws JSONException, BadResponseException;
	public void deleteImplique(Implique a) throws BadResponseException;

}

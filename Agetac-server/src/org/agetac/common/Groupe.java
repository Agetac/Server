package org.agetac.common;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Groupe implements IJsonable{
	private Agent chef;
	private List<Agent> membre;
	private List<Moyen> moyens;

	public Groupe(Agent chef, List<Agent> membre, List<Moyen> moyens) {
		this.chef = chef;
		this.membre = membre;
		this.moyens = moyens;
	}
	
	public Groupe(JSONObject json) {

		try {
			this.chef = new Agent(json.getJSONObject("chef"));
			
			JSONArray jsar = json.getJSONArray("membre");
			for (int i=0; i< jsar.length(); i++){
				membre.add(new Agent(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("moyen");
			for (int i=0; i< jsar.length(); i++){
				moyens.add(new Moyen(jsar.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(List<Moyen> moyens) {
		this.moyens = moyens;
	}

	public Agent getChef() {
		return chef;
	}

	public void setChef(Agent chef) {
		this.chef = chef;
	}

	public List<Agent> getMembre() {
		return membre;
	}

	public void setMembre(List<Agent> membre) {
		this.membre = membre;
	}
	
	public JSONObject toJson(){
	    JSONObject json = new JSONObject();
	    try {
		    json.put("chef", chef);
		    json.put("membre",membre);
		    json.put("moyens",moyens);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("test "+json.toString());
		return json;

	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Groupe(json);
	}
}

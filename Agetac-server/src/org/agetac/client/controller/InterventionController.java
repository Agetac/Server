package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.agetac.client.api.ServerConnection;
import org.agetac.client.model.AgentModel;
import org.agetac.client.model.CaserneModel;
import org.agetac.client.model.InterventionModel;
import org.agetac.client.model.MessageModel;
import org.agetac.client.model.SourceModel;
import org.agetac.client.model.VehiculeModel;
import org.agetac.client.view.AgentView;
import org.agetac.client.view.CaserneView;
import org.agetac.client.view.InterventionView;
import org.agetac.client.view.MessageView;
import org.agetac.client.view.SourceView;
import org.agetac.client.view.VehiculeView;
import org.agetac.common.api.InterventionConnection;
import org.agetac.common.exception.BadResponseException;
import org.agetac.common.exception.InvalidJSONException;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Message;
import org.agetac.common.model.impl.Position;
import org.agetac.server.db.Interventions;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;

public class InterventionController implements ActionListener {

	private InterventionView view;
	private InterventionModel model;
	private ServerConnection serv;
	
	/**
	 * Constructeur de InterventionController
	 */
	public InterventionController(InterventionView view, InterventionModel model) {
		this.view = view;
		this.model = model;
		 serv = this.model.serv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter une intervention")) {
			
			Intervention inter = new Intervention();
			inter.setPosition(view.getPosition());
			inter.setName(view.getName());
			
			JsonRepresentation interRepresentation;
			try {
				interRepresentation = new JsonRepresentation(serv.putResource("intervention", null, new JsonRepresentation(inter.toJSON())));
				inter = new Intervention(interRepresentation.getJsonObject());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BadResponseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidJSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
			
			
			
			model.addIntervention(inter,interCon);
			view.resetTxtFields();
			System.out.println(Interventions.getInstance().getInterventions());
			//}
		}
		else if (e.getActionCommand().equals("Supprimer une intervention")) {
			Intervention inter = model.getInter(view.getSelectedLine());
			try {
				serv.deleteResource("intervention",inter.getUniqueID());
			} catch (BadResponseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.removeIntervention(view.getSelectedLine());

		}
		else if (e.getActionCommand().equals("Voir une intervention")) {
			if (view.getSelectedLine()!=-1){
			InterventionConnection interCon = model.getInterCon(view.getSelectedLine());
			
			MessageModel msgModel = new MessageModel(interCon);
			MessageView msgView = new MessageView(msgModel);
			AgentModel agentModel = new AgentModel();
			AgentView agentView = new AgentView(agentModel);
			VehiculeModel vehiculeModel = new VehiculeModel(interCon);
			VehiculeView vehiculeView = new VehiculeView(vehiculeModel);
			CaserneModel casModel = new CaserneModel();
			CaserneView casView = new CaserneView(casModel);
			SourceModel srcModel = new SourceModel(interCon);
			SourceView srcView = new SourceView(srcModel);
			}
		}
	}

}

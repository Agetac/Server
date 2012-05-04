package org.agetac.client;

import org.agetac.client.model.InterventionModel;
import org.agetac.client.view.InterventionView;
import org.agetac.common.client.AgetacClient;

public class ClientIHM {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		AgetacClient c = new AgetacClient("localhost", 8888);
		InterventionModel interModel = new InterventionModel(c);
		InterventionView interView = new InterventionView(interModel);

	}

}

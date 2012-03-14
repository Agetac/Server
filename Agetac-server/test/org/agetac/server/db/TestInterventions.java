package org.agetac.server.db;

import org.agetac.common.model.impl.Intervention;
import org.junit.Test;

public class TestInterventions {
	/**
	 * Check to make sure the Interventions class can be manipulated correctly.
	 */
	@Test
	public void testInterventionRepository() {
		// Get the singleton instance of the repository.
		Interventions target = Interventions.getInstance();

		// Add an intervention to the repository.
		Intervention intervention = new Intervention();
		target.addIntervention(intervention);
	}
}

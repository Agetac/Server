package org.agetac.server.db;

import static org.junit.Assert.*;

import java.util.Collection;

import org.agetac.model.impl.Agent;
import org.agetac.model.impl.Aptitude;
import org.junit.Test;


public class TestAgents {

	  /**
	   * Check to make sure the Agents class can be manipulated correctly.
	   */
	  @Test
	  public void testAgentRepository() {
	    // Get the singleton instance of the repository.
	    Agents agents = Agents.getInstance();

	    // Add a agent to the repository.
	    String uniqueID = "np";
	    Agent agent = new Agent(uniqueID, "Noel", Aptitude.CDG, null);
	    agents.addAgent(agent);

	    // Get the agent out of the repository and make sure it's the same one.
	    assertEquals("Checking retrieval", agent, agents.getAgent(uniqueID));

	    // Check to see that retrieving an unknown agent returns null.
	    assertNull("Checking unknown retrieval", agents.getAgent("foo"));
	    
	    // Get all of the agents, see that there's only one.
	    Collection<Agent> snapshot = agents.getAgents();
	    assertEquals("Checking snapshot", 1, snapshot.size());
	    
	    // Delete the agent, make sure it's gone.
	    agents.deleteAgent(uniqueID);
	    assertNull("Checking deleted agent", agents.getAgent(uniqueID));
	  }
	}

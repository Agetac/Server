package org.agetac.server.db;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceManagerUtils {
	public static PersistenceManager getPM() {
		PersistenceManagerFactory pmf = JDOHelper
				.getPersistenceManagerFactory("jdo.properties");
		return pmf.getPersistenceManager();
	}
}
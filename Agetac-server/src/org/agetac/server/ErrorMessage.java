package org.agetac.server;

public class ErrorMessage {


	public String toString() {
		StringBuffer sb = new StringBuffer();
		//  Hmmm.. OK.
		sb.append("error:");
		sb.append("An error occured");
		return sb.toString();
	}
}

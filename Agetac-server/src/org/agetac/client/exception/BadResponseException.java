package org.agetac.client.exception;

import org.restlet.Response;

public class BadResponseException extends Exception {
	public BadResponseException(Response r){
		System.out.println("La requete n'a pas été un succès");
		System.out.println("Code : " + r.getStatus().getCode() +" "+ r.getStatus().getName());
		System.out.println("Description : "+ r.getStatus().getDescription());
	}
}

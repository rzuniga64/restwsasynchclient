package com.restws.client;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CheckProcessingClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restwsasync/services/checkprocessingservice/checks");
		AsyncInvoker invoker = target.request().async();

		Future<Boolean> response = invoker.post(Entity.entity(new ChecksList(), MediaType.APPLICATION_XML),
				Boolean.class);

		try {
			System.out.println(response.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {

//			if (e.getCause() instanceof BadRequestException) {
//				BadRequestException bre = (BadRequestException) e.getCause();
//				System.out.println("Please send a valid list of checks");
//			}
		}
	}
}

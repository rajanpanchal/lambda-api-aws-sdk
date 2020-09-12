package net.rajanpanchal.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		try {
			LambdaLogger logger = context.getLogger();
			logger.log("requestEventString:" + requestEvent.toString());
			logger.log("query string param:" + requestEvent.getQueryStringParameters());
			logger.log("headers:" + requestEvent.getHeaders());
			// fetching the value send in the request body
			String message = requestEvent.getBody();
			// Request request = gson.fromJson(message, Request.class);
			logger.log("message body:" + message);

			// setting up the response message
			responseEvent.setBody("Hello from Lambda!");
			responseEvent.setStatusCode(200);

			return responseEvent;

		} catch (Exception ex) {
			responseEvent.setBody("Invalid Response");
			responseEvent.setStatusCode(500);
			return responseEvent;
		}
	}
}
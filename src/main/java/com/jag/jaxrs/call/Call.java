package com.jag.jaxrs.call;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import com.jag.jaxrs.call.util.DefaultStatusHandler;
import com.jag.jaxrs.call.util.StatusHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Call<Input, Output> {
    private final Client httpClient;
    private final StatusHandler errorHandler;

    public Call() {
        this.httpClient = ClientBuilder.newClient();
        this.errorHandler = new DefaultStatusHandler();
    }

    Output call(Input input) {
        WebTarget target = httpClient.target(buildUri(input));
        Response response = call(target);
        Family statusFamily = Family.familyOf(response.getStatus());
        if (statusFamily != Family.SUCCESSFUL) {
            errorHandler.handle(response.getStatus());
        }
        return deserialize(response);
    }

    abstract URI buildUri(Input input);

    abstract Response call(WebTarget target);

    abstract Output deserialize(Response response);
}

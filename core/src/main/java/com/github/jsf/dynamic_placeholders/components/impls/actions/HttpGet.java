package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpGet extends Action {
    public static final UName ID = new UName("http_get", "http", "fetch");
    public static final UName URL = new UName("url", "u");
    public static final UName TIMEOUT = new UName("timeout", "t");

    public HttpGet(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String urlStr = as(URL, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + URL + " is needed for \"" + ID + "\" action"));
        int timeoutSeconds = as(TIMEOUT, Number.class).orElse(5).intValue();

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(timeoutSeconds))
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlStr))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error fetching URL: " + urlStr + " --> " + e.getMessage(), e);
        }
    }
}
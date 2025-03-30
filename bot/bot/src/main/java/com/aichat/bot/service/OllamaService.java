package com.aichat.bot.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    private final HttpClient client;

    // Inject the URL from application.properties
    @Value("${ollama.chat.url}")
    private String url;

    // Regex pattern to extract the "content" token from each JSON snippet
    private final Pattern pattern = Pattern.compile("\"content\"\\s*:\\s*\"(.*?)\"");

    public OllamaService() {
        this.client = HttpClient.newHttpClient();
    }

    public String getAssistantResponse(String prompt) {
        // Build the JSON payload including the model and prompt.
        String jsonPayload = String.format(
                "{\"model\": \"llama3.2:1b\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                prompt.replace("\"", "\\\"")
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        try {
            // Send the request and process the streaming response line by line.
            HttpResponse<Stream<String>> response = client.send(
                    request, HttpResponse.BodyHandlers.ofLines()
            );

            StringBuilder assistantResponse = new StringBuilder();
            // Extract tokens from every line using regex.
            response.body().forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String token = matcher.group(1);
                    assistantResponse.append(token);
                }
            });

            return assistantResponse.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: Unable to get response from Ollama.";
        }
    }
}
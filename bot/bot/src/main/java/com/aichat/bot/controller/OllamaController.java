package com.aichat.bot.controller;

import com.aichat.bot.model.ChatRequest;
import com.aichat.bot.service.OllamaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

    private final OllamaService ollamaService;

    @Autowired
    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest chatRequest) {
        return ollamaService.getAssistantResponse(chatRequest.getPrompt());
    }
}
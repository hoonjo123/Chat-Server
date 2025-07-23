package joney.chatserver.chat.controller;

import joney.chatserver.chat.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {
    private final ChatService chatService;
}

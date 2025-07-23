package joney.chatserver.chat.controller;

import joney.chatserver.chat.dto.ChatMessageReqDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class StompController {


    //방법 1. MessageMapping(수신)과 sendTo(topic에 메시지 전달 ) 한꺼번에 처리


    //브로커 관련 코드
//    @MessageMapping("/{roomId}") // 클라이언트에서 특성 publish/roomId형태로 메시지를 발행시 MessageMapping 수신
//    @SendTo("/topic/{roomId}") //해당 룸Id에 메시지를 발행해서 구독중인 클라이언트에게 메시지를 전송
//    // => 해당 url을 구독하기만 하면, 메시지 수신 가능
//
//    // DestinationVariable : @MessageMapping 어노테이션으로 정의된 Websocket Controller 내에서만 사용됨.
//    public String sendMessage(@DestinationVariable Long roomId, String message){
//        System.out.println(message);
//        return message;
//    }

    //방법 2. MessageMapping어노테이션만 활용
    private final SimpMessagingTemplate messageTemplate;

    public StompController(SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @MessageMapping("/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, ChatMessageReqDto chatMessageReqDto){
        System.out.println(chatMessageReqDto.getMessage());
        messageTemplate.convertAndSend("/topic/" + roomId, chatMessageReqDto);
    }
}

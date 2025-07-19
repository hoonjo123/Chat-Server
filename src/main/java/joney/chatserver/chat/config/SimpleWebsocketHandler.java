package joney.chatserver.chat.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//connect로 웹소켓 연결요청이 들어왔을때 이를 처리할 클래스
@Component
public class SimpleWebsocketHandler extends TextWebSocketHandler {

    // 연결된 세션 관리 : 스레드 safe한 set을 사용 -> 여러명이 연결해도 안전
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    //연결이 되면 어떻게 할건데? -> 사용자의 정보를 메모리에 등록
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Connected : " + session.getId());
    }

    //사용자들에게 메세지를 보내주는 메서드
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("received message : " + payload);
        // set에 담겨있는 모든 User에게 메세지를 보내주는 것. ( 우선은 심플하게 구현 )
        for (WebSocketSession s : sessions){
            if (s.isOpen()){
                s.sendMessage(new TextMessage(payload));
            }
        }
    }

    //연결이 끊기면 세션을 메모리에서 삭제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("disconnected!!");
    }

}

package joney.chatserver.chat.config;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* 디버깅 목적으로만 사용예정
   사용자 < -----------> 서버(세션정보를 메모리에서 관리)
   최초의 이벤트가 발생했을 때, catch하는 역할 ( connect, disconnect )
 */

/**
 * 스프링과 stomp는 기본적으로 세션관리를 자동(내부적)으로 처리한다.
 * 연결해제 이벤트를 기록하고 연결된 세션 수를 실시간으로 확인 할 목적으로 이벤트 리스너를 생성한다.
 * */

/**
 * 프론트앤드의 코드에서 크게 세가지의 시나리오가 존재한다.
 * 1. connect
 * 2. 화면을 완전히 껐을 때
 * 3. 화면을 다른곳으로 이동했을 때
 * */

@Component
public class StompEventListener {

    private final Set<String> sessions = ConcurrentHashMap.newKeySet();

    @EventListener
    public void connectHandle(SessionConnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        sessions.add(accessor.getSessionId());
        System.out.println("connect s.Id :" + accessor.getSessionId());
        System.out.println("total session :" + sessions.size());
    }

    @EventListener
    public void disconnectHandle(SessionDisconnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        sessions.remove(accessor.getSessionId());
        System.out.println("disconnect s.Id :" + accessor.getSessionId());
        System.out.println("total session :" + sessions.size());
    }
}

package joney.chatserver.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer{
    
    private final StompHandler StompHandler;

    public StompWebSocketConfig(StompHandler stompHandler) {
        StompHandler = stompHandler;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect")
                .setAllowedOrigins("http://localhost:3000")
                //ws://가 아니라 http:// 앤드포인트를 사용할 수 있게 해주는 sockJS라이브러리를 통한 요청을 허용하는 설정.
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // /publish/1 <= {room} 과 같이 메시지를 발행해야 함을 설정
        // /publish 형태로 시작하는 url패턴으로 메시지가 발행되면 @controller 객체의 @MessageMapping메서드로 라우팅(전달)
        registry.setApplicationDestinationPrefixes("/publish");

        // /topic/1과 같이 메시지를 수신해야 함을 설정
        registry.enableSimpleBroker("/topic");
        // rabbitMQ같이 외장 브로커를 사용하고 싶다면? .enableStompBrokerRelay사용
    }

    // 사용자 요청 -> 필터에서 그냥 통과해주고 있음 config로 바로 이동 -> 로그인도 안했는데
    // 세션 객체를 만든다? 문제가 있다. intercepter로 사용자 요청을 낚아챔.
    // 별도의 로직을 만들 필요가 있음.

    // 웹소켓요청(connect, subscribe, disconnect) 등의 요청시에는 http header등 http메시지를 넣어올 수 있고,
    // 이를 interceptor를 통해 가로채 토큰등을 검증할 수 있음.
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(StompHandler);
    }

}

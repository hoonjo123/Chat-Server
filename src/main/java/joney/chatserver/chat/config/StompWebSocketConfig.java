package joney.chatserver.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer{

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
    }

}

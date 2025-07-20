//package joney.chatserver.chat.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final SimpleWebsocketHandler simpleWebsocketHandler;
//
//    public WebSocketConfig(SimpleWebsocketHandler simpleWebsocketHandler) {
//        this.simpleWebsocketHandler = simpleWebsocketHandler;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        //웹소켓을 처리할 handler 정의
//        // /connect url로 websocket연결 요청이 들어오면, 핸들러 클래스가 처리
//
//        // 핸들러 클래스가 처리하는 로직 이해
//        // 메모리에 A, B, C 등록 (set) -> A가 메세지 발행시, B와 C에 전송
//        // /connect는 http요청이 아님 -> 원래같으면 요청이 들어왔을 때, filter에서 Authentication객체 확인을 검증하게 되는데,
//        // http요청이 아니므로, 해당 작업이 불가능 -> 아무리 토큰을 넣어서 보낸다고 할지라도
//        // filter에서 /connect요청이 들어올경우 제외처리를 해주어야 함.
//        // 해당부분은 Websocket 영역(STOMP구현시)에서 별도로 처리해주도록 한다.
//
//        // http요청이 아니기 때문에 filter에서 cors설정이 유효하지 않음. 해당부분도 별도로 해주어야한다.
//
//        registry.addHandler(simpleWebsocketHandler,"/connect")
//                // securityconfig에서의 cors예외는 http요청에 대한 예외.
//                // 따라서 websocket 프로토콜에 대한 요청에 대해서는 별도의 cors설정이 필요하다.
//                .setAllowedOrigins("http://localhost:3000");
//    }
//}

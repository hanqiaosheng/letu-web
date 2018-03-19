package org.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(TipsHandler(), "/webSocketServer.socket").setAllowedOrigins("*");
		//registry.addHandler(TipsHandler(), "/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();
	
	}

	@Bean
	public WebSocketHandler TipsHandler() {
		return new TipsHandler();
	}
//	@Bean
//	public WebSocketHandler NumHandler() {
//		return new NumHandler();
//	}
}
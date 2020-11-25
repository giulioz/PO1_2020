package it.unive.dais.po1.web.config;

import it.unive.dais.po1.web.GameWSController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(gameHandler(), "/ws")
            .setAllowedOrigins("*");
  }

  @Bean
  public WebSocketHandler gameHandler() {
    return new GameWSController();
  }
}

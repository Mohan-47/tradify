package com.mo.tradify.websocket;

import com.mo.tradify.domain.dto.FinnhubResponseDto;
import com.mo.tradify.services.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class FinnhubInboundHandler extends TextWebSocketHandler {
    private WebSocketSession session;

    private final TradeService tradeService;
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
        log.info("Connection established: {}", session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received: {}", message.getPayload());
        try {
            FinnhubResponseDto response = objectMapper.readValue(message.getPayload(), FinnhubResponseDto.class);
            tradeService.processFinnhubTrades(response);
        } catch (Exception e) {
            log.error("Error while handling text message: {}", e.getMessage());
        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Transport error: {}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed: {}", closeStatus);
    }

    public void subscribeToSymbol(String symbol) throws Exception {
        log.info("Subscribing to symbol: {}", symbol);
        if (session != null && session.isOpen()) {
            try {
                String message = String.format("{\"type\":\"subscribe\",\"symbol\":\"%s\"}", symbol);
                session.sendMessage(new TextMessage(message));
                log.info("Subscribed to symbol: {}", symbol);
            } catch (Exception e) {
                log.error("Failed to subscribe to symbol: {}", symbol, e);
            }
        }
    }

    public void unsubscribeFromSymbol(String symbol) throws Exception {
        log.info("Unsubscribing to symbol: {}", symbol);
        if (session != null && session.isOpen()) {
            try {
                String message = String.format("{\"type\":\"unsubscribe\",\"symbol\":\"%s\"}", symbol);
                session.sendMessage(new TextMessage(message));
                log.info("Unsubscribed to symbol: {}", symbol);
            } catch (Exception e) {
                log.error("Failed to unsubscribe to symbol: {}", symbol, e);
            }
        }
    }

    public void closeSession() throws Exception {
        log.info("Closing session: {}", session.getId());
        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (Exception e) {
                log.error("Failed to close session", e);
            }
        }
    }
}

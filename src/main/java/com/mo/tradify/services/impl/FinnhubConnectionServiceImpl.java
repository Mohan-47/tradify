package com.mo.tradify.services.impl;

import com.mo.tradify.exceptions.WebsocketConnectionException;
import com.mo.tradify.services.FinnhubConnectionService;
import com.mo.tradify.websocket.FinnhubInboundHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class FinnhubConnectionServiceImpl implements FinnhubConnectionService {

    private final String finnhubUrl;
    private final FinnhubInboundHandler finnhubInboundHandler;
    private final WebSocketClient webSocketClient;

    public FinnhubConnectionServiceImpl(@Value("${finnhub.ws.url}") String finnhubUrl, FinnhubInboundHandler finnhubInboundHandler) {
        this.finnhubUrl = finnhubUrl;
        this.finnhubInboundHandler = finnhubInboundHandler;
        this.webSocketClient = new StandardWebSocketClient();
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void connect() {
        log.info("Connecting to Finnhub Websocket Client");
        webSocketClient
            .execute(finnhubInboundHandler, finnhubUrl)
            .whenComplete((finnhubResponse, throwable) -> {
                if (throwable != null) {
                    throw new WebsocketConnectionException("Failed to connect to Finnhub",  throwable);
                }
               log.info("Connected to Finnhub Websocket Client");
            });
    }

    @Override
    public void disconnect() throws Exception {
        log.info("Disconnecting from Finnhub Websocket Client");
        finnhubInboundHandler.closeSession();
    }
}

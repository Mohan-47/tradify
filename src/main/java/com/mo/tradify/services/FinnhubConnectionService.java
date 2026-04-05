package com.mo.tradify.services;

public interface FinnhubConnectionService {
    void connect();
    void disconnect() throws Exception;
}

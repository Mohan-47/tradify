package com.mo.tradify.services;

import java.util.List;

public interface WatchlistService {
    List<String> getSymbols(String username);
    void addSymbol(String username, String symbol);
    void removeSymbol(String username, String symbol);
}

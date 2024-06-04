package com.unifacs.transitsystem.service;

public interface TokenBlacklist {

    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}

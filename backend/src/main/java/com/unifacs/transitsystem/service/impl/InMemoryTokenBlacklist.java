package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.service.TokenBlacklist;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InMemoryTokenBlacklist implements TokenBlacklist {

    // not the best method as it's just a basic logout service and goes in memory, should implement a caching database
    private Set<String> blacklist = new HashSet<>();

    @Override
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}

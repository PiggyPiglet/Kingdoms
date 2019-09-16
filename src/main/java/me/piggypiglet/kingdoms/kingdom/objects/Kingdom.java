package me.piggypiglet.kingdoms.kingdom.objects;

import me.piggypiglet.framework.utils.SearchUtils;

import java.util.List;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Kingdom implements SearchUtils.Searchable {
    private final String name;
    private final List<UUID> players;

    public Kingdom(String name, List<UUID> players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<UUID> getPlayers() {
        return players;
    }
}

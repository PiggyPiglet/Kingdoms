package me.piggypiglet.kingdoms.kingdom.objects;

import me.piggypiglet.framework.utils.SearchUtils;

import java.util.List;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Kingdom implements SearchUtils.Searchable {
    private String name;
    private final UUID uuid;
    private List<UUID> players;

    public Kingdom(String name, UUID uuid, List<UUID> players) {
        this.name = name;
        this.uuid = uuid;
        this.players = players;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPlayer(UUID player) {
        players.add(player);
    }

    public void removePlayer(UUID player) {
        players.remove(player);
    }
}

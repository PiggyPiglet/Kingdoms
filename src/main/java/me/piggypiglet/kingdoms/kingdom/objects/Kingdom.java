package me.piggypiglet.kingdoms.kingdom.objects;

import me.piggypiglet.framework.utils.SearchUtils;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.Map;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Kingdom implements SearchUtils.Searchable {
    private String name;
    private final UUID uuid;
    private Map<UUID, Ranks> players;

    public Kingdom(String name, UUID uuid, Map<UUID, Ranks> players) {
        this.name = name;
        this.uuid = uuid;
        this.players = players;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<UUID, Ranks> getPlayers() {
        return players;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(UUID player, Ranks rank) {
        players.put(player, rank);
    }

    public void removePlayer(UUID player) {
        players.remove(player);
    }
}

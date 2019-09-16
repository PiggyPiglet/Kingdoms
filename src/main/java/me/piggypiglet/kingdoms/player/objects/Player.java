package me.piggypiglet.kingdoms.player.objects;

import me.piggypiglet.framework.utils.SearchUtils;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Player implements SearchUtils.Searchable {
    private final String name;
    private final UUID uuid;

    public Player(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }
}

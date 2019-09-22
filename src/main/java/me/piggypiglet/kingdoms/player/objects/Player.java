package me.piggypiglet.kingdoms.player.objects;

import me.piggypiglet.framework.utils.SearchUtils;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Player implements SearchUtils.Searchable {
    private String name;
    private final UUID uuid;
    private UUID kingdom;
    private Ranks rank;

    public Player(String name, UUID uuid, UUID kingdom, Ranks rank) {
        this.name = name;
        this.uuid = uuid;
        this.kingdom = kingdom;
        this.rank = rank;
    }

    @Override
    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getKingdom() {
        return kingdom;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKingdom(UUID kingdom) {
        this.kingdom = kingdom;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }
}

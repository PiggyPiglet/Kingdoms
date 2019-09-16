package me.piggypiglet.kingdoms.player;

import com.google.inject.Singleton;
import me.piggypiglet.framework.mysql.manager.MySQLManager;
import me.piggypiglet.kingdoms.player.db.PlayersTable;
import me.piggypiglet.kingdoms.player.objects.Player;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class PlayerManager extends MySQLManager<Player> {
    public PlayerManager() {
        super(new PlayersTable());
    }

    @Override
    public void setup() {}

    @Override
    protected void populate(List<Player> list) {}

    public void add(org.bukkit.entity.Player player) {
        items.add(new Player(player.getName(), player.getUniqueId()));
    }
}
package me.piggypiglet.kingdoms.player.db;

import co.aikar.idb.DbRow;
import me.piggypiglet.framework.mysql.table.Table;
import me.piggypiglet.framework.utils.map.KeyValueSet;
import me.piggypiglet.kingdoms.player.objects.Player;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PlayersTable extends Table<Player> {
    public PlayersTable() {
        super("players");
    }

    @Override
    protected Player rowToType(DbRow dbRow) {
        return new Player(dbRow.getString("name"), UUID.fromString(dbRow.getString("uuid")));
    }

    @Override
    protected KeyValueSet typeToRow(Player player) {
        return KeyValueSet.builder()
                .key("name").value(player.getName())
                .key("uuid").value(player.getUuid())
                .build();
    }
}
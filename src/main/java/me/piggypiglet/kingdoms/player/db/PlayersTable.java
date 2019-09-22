package me.piggypiglet.kingdoms.player.db;

import co.aikar.idb.DbRow;
import me.piggypiglet.framework.mysql.table.Table;
import me.piggypiglet.framework.utils.map.KeyValueSet;
import me.piggypiglet.kingdoms.player.objects.Player;
import me.piggypiglet.kingdoms.ranks.Ranks;

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
        return new Player(
                dbRow.getString("name"),
                UUID.fromString(dbRow.getString("uuid")),
                UUID.fromString(dbRow.getString("kingdom")),
                Ranks.valueOf(dbRow.getString("rank").toUpperCase())
        );
    }

    @Override
    protected KeyValueSet typeToRow(Player player) {
        return KeyValueSet.builder()
                .key("name").value(player.getName())
                .key("uuid").value(player.getUuid().toString())
                .key("kingdom").value(player.getKingdom().toString())
                .key("rank").value(player.getRank().name())
                .build();
    }

    @Override
    protected KeyValueSet saveLocation(Player player) {
        return KeyValueSet.builder()
                .key("uuid").value(player.getUuid().toString())
                .build();
    }
}
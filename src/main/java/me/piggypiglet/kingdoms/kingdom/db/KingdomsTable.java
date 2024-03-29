package me.piggypiglet.kingdoms.kingdom.db;

import co.aikar.idb.DbRow;
import me.piggypiglet.framework.mysql.table.Table;
import me.piggypiglet.framework.utils.map.KeyValueSet;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class KingdomsTable extends Table<Kingdom> {
    public KingdomsTable() {
        super("kingdoms");
    }

    @Override
    protected Kingdom rowToType(DbRow dbRow) {
        return new Kingdom(
                dbRow.getString("name"),
                UUID.fromString(dbRow.getString("uuid")),
                Arrays.stream(dbRow.getString("players").split(","))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(s -> UUID.fromString(s[0]), s -> Ranks.valueOf(s[1].toUpperCase())))
        );
    }

    @Override
    protected KeyValueSet typeToRow(Kingdom kingdom) {
        return KeyValueSet.builder()
                .key("name").value(kingdom.getName())
                .key("uuid").value(kingdom.getUuid().toString())
                .key("players").value(
                        kingdom.getPlayers().entrySet().stream()
                                .map(e -> e.getKey() + ":" + e.getValue())
                                .collect(Collectors.joining(","))
                )
                .build();
    }

    @Override
    protected KeyValueSet saveLocation(Kingdom kingdom) {
        return KeyValueSet.builder()
                .key("uuid").value(kingdom.getUuid().toString())
                .build();
    }
}

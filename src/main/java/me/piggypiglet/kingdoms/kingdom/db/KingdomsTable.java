package me.piggypiglet.kingdoms.kingdom.db;

import co.aikar.idb.DbRow;
import me.piggypiglet.framework.mysql.table.Table;
import me.piggypiglet.framework.utils.map.KeyValueSet;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;

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
        return new Kingdom(dbRow.getString("name"), Arrays.stream(dbRow.getString("players").split(",")).map(UUID::fromString).collect(Collectors.toList()));
    }

    @Override
    protected KeyValueSet typeToRow(Kingdom kingdom) {
        return KeyValueSet.builder()
                .key("name").value(kingdom.getName())
                .key("players").value(kingdom.getPlayers())
                .build();
    }
}

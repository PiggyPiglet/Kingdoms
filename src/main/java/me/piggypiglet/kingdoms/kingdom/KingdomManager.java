package me.piggypiglet.kingdoms.kingdom;

import com.google.inject.Singleton;
import me.piggypiglet.framework.mysql.manager.MySQLManager;
import me.piggypiglet.kingdoms.kingdom.db.KingdomsTable;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class KingdomManager extends MySQLManager<Kingdom> {
    public KingdomManager() {
        super(new KingdomsTable());
    }

    @Override
    protected void populate(List<Kingdom> list) {}
}

package me.piggypiglet.kingdoms.kingdom;

import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.framework.mysql.manager.MySQLManager;
import me.piggypiglet.kingdoms.kingdom.db.KingdomsTable;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class KingdomManager extends MySQLManager<Kingdom> {
    public static final Kingdom WILDERNESS = new Kingdom("Wilderness", UUID.nameUUIDFromBytes("wilderness".getBytes()), new ArrayList<>());

    private final Map<UUID, Kingdom> kingdoms = new HashMap<>();

    public KingdomManager() {
        super(new KingdomsTable());
    }

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .clazz(String.class, s -> {
                    List<Kingdom> kingdoms = items.stream().filter(k -> k.getName().equals(s)).collect(Collectors.toList());

                    return kingdoms.size() > 0 ? kingdoms.get(0) : WILDERNESS;
                })
                .clazz(UUID.class, kingdoms::get)
                .interfaze(Player.class, p -> kingdoms.getOrDefault(p.getUniqueId(), WILDERNESS))
                .build();
    }

    @Override
    protected void populate() {
        kingdoms.putAll(items.stream().collect(Collectors.toMap(Kingdom::getUuid, k -> k)));
    }

    @Override
    protected void insert(Kingdom kingdom) {
        kingdoms.put(kingdom.getUuid(), kingdom);
    }

    @Override
    protected void delete(Kingdom kingdom) {
        kingdoms.remove(kingdom.getUuid());
    }

    @Override
    protected Collection<Kingdom> retrieveAll() {
        return items;
    }

    public void addPlayer(UUID kingdom, UUID player) {
        kingdoms.get(kingdom).addPlayer(player);
    }
}

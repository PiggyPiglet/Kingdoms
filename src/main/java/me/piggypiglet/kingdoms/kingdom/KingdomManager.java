package me.piggypiglet.kingdoms.kingdom;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.framework.mysql.manager.MySQLManager;
import me.piggypiglet.framework.task.Task;
import me.piggypiglet.kingdoms.kingdom.db.KingdomsTable;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.ranks.Ranks;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class KingdomManager extends MySQLManager<Kingdom> {
    public static final Kingdom WILDERNESS = new Kingdom("Wilderness", UUID.nameUUIDFromBytes("wilderness".getBytes()), new HashMap<>());

    @Inject private Task task;

    private final Map<UUID, Kingdom> kingdoms = new HashMap<>();
    private final Map<UUID, UUID> invites = ExpiringMap.builder()
            .expiration(60, TimeUnit.SECONDS)
            .expirationPolicy(ExpirationPolicy.ACCESSED)
            .build();

    public KingdomManager() {
        super(new KingdomsTable());
    }

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .clazz(String.class, s -> items.stream().filter(k -> k.getName().equals(s)).findAny())
                        .getter(o -> o.orElse(WILDERNESS))
                        .exists(Optional::isPresent)
                        .bundle()
                .clazz(UUID.class)
                        .getter(kingdoms::get)
                        .exists(kingdoms::containsKey)
                        .bundle()
                .interfaze(Player.class, p -> items.stream().filter(k -> k.getPlayers().containsKey(p.getUniqueId())).findAny())
                        .getter(o -> o.orElse(WILDERNESS))
                        .exists(Optional::isPresent)
                        .bundle()
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

    public void addPlayer(UUID kingdom, UUID player, Ranks rank) {
        kingdoms.get(kingdom).setPlayer(player, rank);
    }

    public boolean invite(me.piggypiglet.kingdoms.player.objects.Player player, me.piggypiglet.kingdoms.player.objects.Player target) {
        if (!invites.containsKey(target.getUuid())) {
            invites.put(target.getUuid(), player.getKingdom());
            return true;
        }

        return false;
    }

    public Map<UUID, UUID> getInvites() {
        return invites;
    }
}

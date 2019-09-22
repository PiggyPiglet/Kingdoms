package me.piggypiglet.kingdoms.player;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.framework.mysql.manager.MySQLManager;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.player.db.PlayersTable;
import me.piggypiglet.kingdoms.player.objects.Player;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class PlayerManager extends MySQLManager<Player> {
    @Inject private KingdomManager kingdomManager;

    private final Map<UUID, Player> players = new HashMap<>();

    public PlayerManager() {
        super(new PlayersTable());
        options.autoPopulate(false);
    }

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .clazz(UUID.class)
                        .getter(players::get)
                        .exists(players::containsKey)
                        .bundle()
                .interfaze(org.bukkit.entity.Player.class, org.bukkit.entity.Player::getUniqueId)
                        .getter(players::get)
                        .exists(players::containsKey)
                        .bundle()
                .build();
    }

    @Override
    protected void insert(Player player) {
        players.put(player.getUuid(), player);
    }

    @Override
    protected void delete(Player player) {
        players.remove(player.getUuid());
    }

    @Override
    protected Collection<Player> retrieveAll() {
        return items;
    }

    public void edit(UUID player, UUID kingdom) {
        players.get(player).setKingdom(kingdom);
    }

    public void add(org.bukkit.entity.Player player) {
        final UUID uuid = player.getUniqueId();
        final Kingdom kingdom = kingdomManager.get(player);

        add(new Player(player.getName(), uuid, kingdom.getUuid(), kingdom.getPlayers().getOrDefault(uuid, Ranks.NONE)));
    }

    public boolean isInWilderness(Player player) {
        return player.getKingdom().equals(KingdomManager.WILDERNESS.getUuid());
    }
}
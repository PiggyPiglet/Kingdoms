package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.BukkitCommand;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.player.PlayerManager;
import me.piggypiglet.kingdoms.player.objects.Player;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CreateCommand extends BukkitCommand {
    @Inject private PlayerManager playerManager;
    @Inject private KingdomManager kingdomManager;

    public CreateCommand() {
        super("create");
        options.playerOnly(true).usage("<name>").description("Create a kingdom.").permissions("kingdoms.create");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        final UUID uuid = UUID.fromString(user.getId());

        if (args.length > 0) {
            if (kingdomManager.exists(args[0])) {
                user.sendMessage("There is already a kingdom called %s.", args[0]);
                return true;
            }

            final Player player = playerManager.get(uuid);

            if (!player.getKingdom().equals(KingdomManager.WILDERNESS.getUuid())) {
                user.sendMessage("You are already in a kingdom.");
                return true;
            }

            final UUID kingdom = UUID.nameUUIDFromBytes((args[0] + uuid).getBytes());
            final Map<UUID, Ranks> rank = new HashMap<>();
            rank.put(uuid, Ranks.OWNER);
            player.setRank(Ranks.OWNER);

            playerManager.edit(uuid, kingdom);
            kingdomManager.add(new Kingdom(
                    args[0],
                    kingdom,
                    rank
            ));
            user.sendMessage("Made a kingdom called %s with you in it.", args[0]);

            return true;
        }

        return false;
    }
}

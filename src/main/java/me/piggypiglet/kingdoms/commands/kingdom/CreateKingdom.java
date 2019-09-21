package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.player.PlayerManager;

import java.util.Collections;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CreateKingdom extends BukkitCommand {
    @Inject private PlayerManager playerManager;
    @Inject private KingdomManager kingdomManager;

    public CreateKingdom() {
        super("create");
        options.playerOnly(true).usage("<name>").description("Create a kingdom.").permissions("kingdoms.create");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        UUID uuid = ((BukkitUser) user).getAsPlayer().getUniqueId();

        if (args.length > 0) {
            if (!kingdomManager.get(args[0]).equals(KingdomManager.WILDERNESS)) {
                user.sendMessage("There is already a kingdom called %s.", args[0]);
                return true;
            }

            if (!playerManager.get(uuid).getKingdom().equals(KingdomManager.WILDERNESS.getUuid())) {
                user.sendMessage("You are already in a kingdom.");
                return true;
            }

            UUID kingdom = UUID.nameUUIDFromBytes((args[0] + uuid).getBytes());

            playerManager.edit(uuid, kingdom);
            kingdomManager.add(new Kingdom(
                    args[0],
                    kingdom,
                    Collections.singletonList(uuid))
            );
            user.sendMessage("Made a kingdom called %s with you in it.", args[0]);

            return true;
        }

        return false;
    }
}

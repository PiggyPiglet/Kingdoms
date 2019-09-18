package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.player.PlayerManager;
import org.bukkit.entity.Player;

import java.util.Collections;

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
        Player player = ((BukkitUser) user).getAsPlayer();

        if (args.length > 0) {
            playerManager.

            kingdomManager.add(new Kingdom(args[0], Collections.singletonList(player.getUniqueId())));
            user.sendMessage("Made a kingdom called %s with you in it.", args[0]);

            return true;
        }

        return false;
    }
}

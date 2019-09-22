package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class InfoCommand extends Command {
    @Inject private KingdomManager kingdomManager;

    public InfoCommand() {
        super("info");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        String message;

        if (args.length > 0) {
            message = info(kingdomManager.search(args[0]).get(0));
        } else {
            message = info(kingdomManager.get(((BukkitUser) user).getAsPlayer()));
        }

        user.sendMessage(message);

        return true;
    }

    private String info(Kingdom kingdom) {
        return String.format("name: %s\nuuid: %s\nplayers: %s", kingdom.getName(), kingdom.getUuid(), kingdom.getPlayers());
    }
}

package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class FindKingdom extends Command {
    @Inject private KingdomManager kingdomManager;

    public FindKingdom() {
        super("find");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        if (args.length > 0) {
            List<Kingdom> results = kingdomManager.search(args[0]);

            if (results.size() > 0) {
                Kingdom kingdom = results.get(0);
                user.sendMessage("name: %s\nmembers: %s", kingdom.getName(), kingdom.getPlayers().stream().map(UUID::toString).collect(Collectors.joining(", ")));
            } else {
                user.sendMessage("&cNo kingdoms found.");
            }

            return true;
        }

        return false;
    }
}

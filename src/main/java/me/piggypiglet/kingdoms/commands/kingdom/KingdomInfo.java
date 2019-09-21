package me.piggypiglet.kingdoms.commands.kingdom;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class KingdomInfo extends Command {
    @Inject private KingdomManager kingdomManager;

    public KingdomInfo() {
        super("info");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        if (args.length > 0) {


            return true;
        }

        return false;
    }
}

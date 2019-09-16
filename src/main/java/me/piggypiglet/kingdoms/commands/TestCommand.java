package me.piggypiglet.kingdoms.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.player.PlayerManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    @Inject private PlayerManager playerManager;

    public TestCommand() {
        super("player find");
        options.usage("<player name>");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        if (args.length > 0) {
            user.sendMessage(playerManager.search(args[0]).toString());

            return true;
        }
        return false;
    }
}

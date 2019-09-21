package me.piggypiglet.kingdoms.commands.kingdom;

import me.piggypiglet.framework.commands.Command;
import me.piggypiglet.framework.user.User;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    @Override
    protected boolean execute(User user, String[] strings) {
        user.sendMessage(UUID.nameUUIDFromBytes("wilderness".getBytes()).toString());
        return true;
    }
}

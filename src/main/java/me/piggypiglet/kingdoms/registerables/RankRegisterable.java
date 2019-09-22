package me.piggypiglet.kingdoms.registerables;

import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.kingdoms.ranks.Ranks;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RankRegisterable extends StartupRegisterable {
    @Override
    protected void execute() {
        requestStaticInjections(Ranks.RankProcessor.class);
    }
}

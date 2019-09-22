package me.piggypiglet.kingdoms.ranks;

import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.utils.annotations.files.Config;

import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Ranks {
    OWNER,
    ADMIN,
    MOD,
    TRUSTED,
    MEMBER,
    GUEST,
    NONE;

    public boolean hasPermissions(Permissions... permissions) {
        if (this == OWNER) {
            return true;
        }

        return Arrays.stream(permissions).allMatch(p -> RankProcessor.hasPermission(toString().toLowerCase(), p.name().toLowerCase()));
    }

    public static class RankProcessor {
        @Inject @Config private static FileConfiguration config;

        private static boolean hasPermission(String rank, String permission) {
            return config.getStringList("permissions." + permission).contains(rank);
        }
    }
}
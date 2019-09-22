package me.piggypiglet.kingdoms;

import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.mysql.MySQLAddon;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.framework.utils.annotations.registerable.RegisterableData;
import me.piggypiglet.framework.utils.map.KeyValueSet;
import me.piggypiglet.kingdoms.registerables.RankRegisterable;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Kingdoms extends JavaPlugin {
    @Override
    public void onEnable() {
        Framework.builder()
                .main(JavaPlugin.class, this)
                .pckg("me.piggypiglet.kingdoms")
                .startup(new RegisterableData(RankRegisterable.class))
                .commandPrefix("kingdoms")
                .fileDir(getDataFolder().getPath())
                .file(true, "config", "/config.yml", "config.yml", Config.class)
                .config(MySQLAddon.class, "config", KeyValueSet.builder()
                        .key("user").value("mysql.credentials.user")
                        .key("password").value("mysql.credentials.password")
                        .key("db").value("mysql.credentials.db")
                        .key("host").value("mysql.credentials.host")
                        .key("save-interval").value("mysql.save-interval")
                        .key("tables").value("mysql.tables")
                        .build().getMap())
                .build()
                .init();
    }
}

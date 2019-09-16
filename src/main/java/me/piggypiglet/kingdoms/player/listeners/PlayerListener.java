package me.piggypiglet.kingdoms.player.listeners;

import com.google.inject.Inject;
import me.piggypiglet.kingdoms.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PlayerListener implements Listener {
    @Inject private PlayerManager playerManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        playerManager.add(e.getPlayer());
    }
}

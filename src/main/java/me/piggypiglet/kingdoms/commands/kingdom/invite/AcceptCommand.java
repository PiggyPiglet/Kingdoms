package me.piggypiglet.kingdoms.commands.kingdom.invite;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.BukkitCommand;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.kingdom.objects.Kingdom;
import me.piggypiglet.kingdoms.player.PlayerManager;
import me.piggypiglet.kingdoms.ranks.Ranks;

import java.util.Map;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class AcceptCommand extends BukkitCommand {
    @Inject private PlayerManager playerManager;
    @Inject private KingdomManager kingdomManager;

    public AcceptCommand() {
        super("accept");
        options.playerOnly(true).usage("").description("Accept a pending invite.").permissions("kingdoms.accept");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        final UUID player = UUID.fromString(user.getId());
        final Map<UUID, UUID> invites = kingdomManager.getInvites();

        if (invites.containsKey(player)) {
            Kingdom kingdom = kingdomManager.get(invites.get(player));
            playerManager.get(player).setKingdom(kingdom.getUuid());
            kingdom.setPlayer(player, Ranks.GUEST);
            playerManager.get(player).setRank(Ranks.GUEST);
            invites.remove(player);
            user.sendMessage("You are now apart of %s.", kingdom.getName());
        } else {
            user.sendMessage("You do not have any pending invites.");
        }

        return true;
    }
}
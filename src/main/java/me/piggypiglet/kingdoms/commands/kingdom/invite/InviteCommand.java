package me.piggypiglet.kingdoms.commands.kingdom.invite;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.user.User;
import me.piggypiglet.kingdoms.kingdom.KingdomManager;
import me.piggypiglet.kingdoms.player.PlayerManager;
import me.piggypiglet.kingdoms.player.objects.Player;
import me.piggypiglet.kingdoms.ranks.Permissions;
import org.bukkit.Bukkit;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class InviteCommand extends BukkitCommand {
    @Inject private PlayerManager playerManager;
    @Inject private KingdomManager kingdomManager;

    public InviteCommand() {
        super("invite");
        options.playerOnly(true).usage("<player name>").description("Invite a player to your kingdom.").permissions("kingdoms.invite");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        if (args.length > 0) {
            final Player player = playerManager.get(UUID.fromString(user.getId()));

            if (playerManager.isInWilderness(player)) {
                user.sendMessage("You do not belong to a kingdom.");
                return true;
            }

            Player target = playerManager.search(args[0]).get(0);

            if (!target.getName().equalsIgnoreCase(args[0])) {
                user.sendMessage("No player found with the name %s, did you mean %s?", args[0], target.getName());
                return true;
            }

            if (!playerManager.isInWilderness(target)) {
                user.sendMessage("%s user is already in a kingdom.", target.getName());
                return true;
            }

            if (player.getRank().hasPermissions(Permissions.INVITE)) {
                if (kingdomManager.invite(player, target)) {
                    user.sendMessage("Sent an invite to %s.", target.getName());
                    new BukkitUser(Bukkit.getServer().getPlayer(target.getUuid()))
                            .sendMessage("You've received an invite from %s to %s. Run /kingdoms accept to accept.",
                                    player.getName(),
                                    kingdomManager.get(player.getKingdom()).getName()
                            );
                } else {
                    user.sendMessage("%s already has a pending invitation.", target.getName());
                }
            } else {
                user.sendMessage("You do not have permission to invite players to your kingdom.");
            }

            return true;
        }

        return false;
    }
}

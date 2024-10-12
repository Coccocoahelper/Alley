package me.emmy.alley.party.listener;

import me.emmy.alley.Alley;
import me.emmy.alley.party.Party;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.util.chat.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Remi
 * @project Alley
 * @date 5/25/2024
 */
public class PartyListener implements Listener {

    @EventHandler
    private void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(event.getPlayer().getUniqueId());

        if (event.getMessage().startsWith("#") || event.getMessage().startsWith("!")) {
            if (profile.getParty() == null) {
                event.getPlayer().sendMessage(CC.translate("&cYou're not in a party."));
                return;
            }

            event.setCancelled(true);
            if (!profile.getProfileData().getProfileSettingData().isPartyMessagesEnabled()) {
                event.getPlayer().sendMessage(CC.translate("&cYou have party messages disabled."));
                return;
            }

            Party party = Alley.getInstance().getPartyRepository().getPartyByMember(event.getPlayer().getUniqueId());
            profile.getParty().notifyParty(party.getChatFormat().replace("{player}", event.getPlayer().getName()).replace("{message}", event.getMessage().substring(1)));
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

        if (profile.getParty() != null) {
            if (profile.getParty().getLeader().equals(player)) {
                profile.getParty().disbandParty();
                return;
            }

            profile.getParty().leaveParty(player);
        }
    }
}
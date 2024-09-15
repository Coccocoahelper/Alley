package me.emmy.alley.profile.settings.playersettings.command.toggle;

import me.emmy.alley.Alley;
import me.emmy.alley.locale.Locale;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.util.chat.CC;
import me.emmy.alley.api.command.BaseCommand;
import me.emmy.alley.api.command.Command;
import me.emmy.alley.api.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Alley
 * @date 25/05/2024 - 23:35
 */

public class TogglePartyMessagesCommand extends BaseCommand {
    @Override
    @Command(name = "togglepartymessages")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());
        profile.getProfileData().getProfileSettingData().setPartyMessagesEnabled(!profile.getProfileData().getProfileSettingData().isPartyMessagesEnabled());

        player.sendMessage(CC.translate(Locale.TOGGLED_PARTY_MESSAGES.getMessage().replace("{status}", profile.getProfileData().getProfileSettingData().isPartyMessagesEnabled() ? "&aenabled" : "&cdisabled")));
    }
}

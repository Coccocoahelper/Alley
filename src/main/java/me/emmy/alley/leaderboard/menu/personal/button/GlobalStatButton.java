package me.emmy.alley.leaderboard.menu.personal.button;

import me.emmy.alley.Alley;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.profile.data.impl.ProfileFFAData;
import me.emmy.alley.util.item.ItemBuilder;
import me.emmy.alley.api.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Remi
 * @project Alley
 * @date 5/26/2024
 */
public class GlobalStatButton extends Button {

    /**
     * Gets the item to display in the menu.
     *
     * @param player the player viewing the menu
     * @return the item to display
     */
    @Override
    public ItemStack getButtonItem(Player player) {
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());
        return new ItemBuilder(Material.NETHER_STAR)
                .name("&b&lGlobal")
                .lore(
                        "",
                        "&b&lRanked Kit Statistics",
                        "&f● &bWins: &f" + profile.getProfileData().getRankedWins(),
                        "&f● &bLosses: &f" + profile.getProfileData().getRankedLosses(),
                        "&f● &bElo: &f" + profile.getProfileData().getProfileDivisionData().getGlobalElo(),
                        "",
                        "&b&lUnranked Kit Statistics",
                        "&f● &bWins: &f" + profile.getProfileData().getUnrankedWins(),
                        "&f● &bLosses: &f" + profile.getProfileData().getUnrankedLosses(),
                        "",
                        "&b&lFFA Statistics",
                        "&f● &bKills: &f" + profile.getProfileData().getFfaData().values().stream().mapToInt(ProfileFFAData::getKills).sum(),
                        "&f● &bDeaths: &f" + profile.getProfileData().getFfaData().values().stream().mapToInt(ProfileFFAData::getDeaths).sum(),
                        ""

                )
                .build();
    }
}

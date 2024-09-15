package me.emmy.alley.leaderboard.menu.personal.button;

import lombok.AllArgsConstructor;
import me.emmy.alley.Alley;
import me.emmy.alley.kit.Kit;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.profile.data.impl.ProfileKitData;
import me.emmy.alley.util.item.ItemBuilder;
import me.emmy.alley.api.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Remi
 * @project Alley
 * @date 5/26/2024
 */
@AllArgsConstructor
public class KitStatButton extends Button {

    private final Kit kit;

    @Override
    public ItemStack getButtonItem(Player player) {
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());
        ProfileKitData profileKitData = profile.getProfileData().getKitData().get(kit.getName());

        return new ItemBuilder(kit.getIcon())
                .name("&b&l" + kit.getDisplayName())
                .durability(kit.getIconData())
                .lore(
                        "",
                        "&f● &bWins: &f" + profileKitData.getWins(),
                        "&f● &bLosses: &f" + profileKitData.getLosses(),
                        "&f● &bElo: &f" + profileKitData.getElo(),
                        ""
                )
                .build();
    }
}

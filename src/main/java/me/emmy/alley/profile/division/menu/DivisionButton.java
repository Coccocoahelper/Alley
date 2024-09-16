package me.emmy.alley.profile.division.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.emmy.alley.Alley;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.profile.division.AbstractDivision;
import me.emmy.alley.profile.division.enums.EnumDivisionLevel;
import me.emmy.alley.profile.division.enums.EnumDivisionTier;
import me.emmy.alley.util.item.ItemBuilder;
import me.emmy.alley.api.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Remi
 * @project Alley
 * @date 6/2/2024
 */
@Getter
@AllArgsConstructor
public class DivisionButton extends Button {

    private final AbstractDivision division;

    @Override
    public ItemStack getButtonItem(Player player) {
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());
        AbstractDivision abstractDivision = Alley.getInstance().getDivisionRepository().getDivision(profile.getProfileData().getProfileDivisionData().getDivision());
        int eloNeeded = division.getEloMin() - profile.getProfileData().getProfileDivisionData().getGlobalElo();
        return new ItemBuilder(division.getIcon())
                .durability(division.getDurability())
                .name("&b&l" + division.getName())
                .lore(
                        "",
                        "&b&lYour Progress",
                        "&f● &bGlobal Elo: &f" + profile.getProfileData().getProfileDivisionData().getGlobalElo(),
                        "&f● &bTier: &f" + abstractDivision.getTier().getName(),
                        "&f● &bLevel: &f" + abstractDivision.getLevel().getName(),
                        "&f● &bNext Division: &f" + abstractDivision.getNextDivisionAndLevel(),
                        "&f● &bProgress: &f" + abstractDivision.generateProgressBar(profile.getProfileData().getProfileDivisionData().getGlobalElo()),
                        "",
                        "&b&lDivision Information",
                        "&f● &bTier: &f" + division.getTier().getName(),
                        "&f● &bLevel: &f" + division.getLevel().getName(),
                        "&f● &bElo Range: &f" + division.getEloMin() + " - " + division.getEloMax(),
                        "&f● &bDescription: &f" + division.getDescription(),
                        "&f● &bElo Required: &f" + eloNeeded,
                        ""

                )
                .build();
    }
}

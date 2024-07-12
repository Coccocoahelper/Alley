package me.emmy.alley.leaderboard.command;

import me.emmy.alley.leaderboard.menu.leaderboard.LeaderboardMenu;
import me.emmy.alley.leaderboard.menu.personal.StatisticsMenu;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Alley
 * @date 19/05/2024 - 11:29
 */
public class LeaderboardCommand extends BaseCommand {
    @Override
    @Command(name = "leaderboard", aliases = {"leaderboards", "lb"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        new StatisticsMenu().openMenu(player);
    }
}

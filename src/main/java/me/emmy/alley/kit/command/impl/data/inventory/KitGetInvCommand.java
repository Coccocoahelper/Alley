package me.emmy.alley.kit.command.impl.data.inventory;

import me.emmy.alley.Alley;
import me.emmy.alley.kit.Kit;
import me.emmy.alley.locale.Locale;
import me.emmy.alley.utils.chat.CC;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Practice
 * @date 28/04/2024 - 22:25
 */
public class KitGetInvCommand extends BaseCommand {
    @Override
    @Command(name = "kit.getinventory", aliases = "kit.getinv",permission = "alley.admin")
    public void onCommand(CommandArgs command) {
        Player sender = command.getPlayer();
        String[] args = command.getArgs();

        if (command.length() < 1) {
            sender.sendMessage(CC.translate("&cUsage: /kit getinventory (kitName)"));
            return;
        }

        String kitName = args[0];
        Kit kit = Alley.getInstance().getKitRepository().getKit(kitName);

        if (kit == null) {
            sender.sendMessage(CC.translate(Locale.KIT_NOT_FOUND.getMessage()));
            return;
        }

        sender.getInventory().setContents(kit.getInventory());
        sender.getInventory().setArmorContents(kit.getArmor());
        sender.sendMessage(CC.translate(Locale.KIT_INVENTORY_GIVEN.getMessage().replace("{kit-name}", kitName)));
    }
}

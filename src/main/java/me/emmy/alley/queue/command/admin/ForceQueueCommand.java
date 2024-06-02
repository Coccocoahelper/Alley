package me.emmy.alley.queue.command.admin;

import me.emmy.alley.Alley;
import me.emmy.alley.hotbar.enums.HotbarType;
import me.emmy.alley.kit.Kit;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.queue.Queue;
import me.emmy.alley.utils.PlayerUtil;
import me.emmy.alley.utils.chat.CC;
import me.emmy.alley.utils.command.BaseCommand;
import me.emmy.alley.utils.command.Command;
import me.emmy.alley.utils.command.CommandArgs;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author Remi
 * @project Alley
 * @date 5/26/2024
 */
public class ForceQueueCommand extends BaseCommand {
    @Command(name = "queue.force", permission = "alley.admin")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length != 3) {
            player.sendMessage(CC.translate("&cUsage: /queue force <player> <kit> <ranked>"));
            return;
        }

        Player target = player.getServer().getPlayer(args[0]);
        String kitType = args[1];
        boolean ranked = Boolean.parseBoolean(args[2]);

        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found."));
            return;
        }

        Kit kit = Alley.getInstance().getKitRepository().getKit(kitType);
        if (kit == null) {
            player.sendMessage(CC.translate("&cKit not found."));
            return;
        }
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(target.getUniqueId());
        for (Queue queue : Alley.getInstance().getQueueRepository().getQueues()) {
            if (queue.getKit().equals(kit) && queue.isRanked() == ranked) {
                queue.addPlayer(target, queue.isRanked() ? profile.getProfileData().getKitData().get(queue.getKit().getName()).getElo() : 0);
                PlayerUtil.reset(target);
                target.playSound(target.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.5F);
                Alley.getInstance().getHotbarRepository().applyHotbarItems(target, HotbarType.QUEUE);
                player.sendMessage(CC.translate("&aYou've added &b" + target.getName() + " &ato the &b" + queue.getQueueType() + " &aqueue."));
                return;
            }
        }
    }
}

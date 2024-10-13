package me.emmy.alley.essential.spawn.listener;

import me.emmy.alley.Alley;
import me.emmy.alley.profile.Profile;
import me.emmy.alley.profile.enums.EnumProfileState;
import me.emmy.alley.util.chat.CC;
import me.emmy.alley.util.chat.Logger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author Emmy
 * @project Alley
 * @date 25/05/2024 - 16:20
 */
public class SpawnListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

        if (profile.getState().equals(EnumProfileState.LOBBY) || profile.getState().equals(EnumProfileState.EDITING) || profile.getState().equals(EnumProfileState.WAITING)) {
            Logger.log("&c" + player.getName() + " tried to place a block in the lobby");
            if (player.getGameMode() == GameMode.CREATIVE) {
                Logger.log("&c" + player.getName() + " tried to place a block in the lobby in creative mode");
                return;
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

        if (player.getGameMode() == GameMode.SURVIVAL
                && (profile.getState().equals(EnumProfileState.LOBBY)
                || profile.getState().equals(EnumProfileState.EDITING)
                || profile.getState().equals(EnumProfileState.WAITING))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

        if (player.getGameMode() == GameMode.SURVIVAL
                && (profile.getState().equals(EnumProfileState.LOBBY)
                || profile.getState().equals(EnumProfileState.EDITING)
                || profile.getState().equals(EnumProfileState.WAITING))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());
        if (profile.getState().equals(EnumProfileState.LOBBY) || profile.getState().equals(EnumProfileState.EDITING) || profile.getState().equals(EnumProfileState.WAITING)) {
            if (player.getGameMode() == GameMode.CREATIVE) {
                Bukkit.getConsoleSender().sendMessage(CC.translate("&c" + player.getName() + " broke a block but is in creative."));
                return;
            }
            Bukkit.getConsoleSender().sendMessage(CC.translate("&c" + player.getName() + " tried to break a block in the lobby"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onHunger(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

            if (profile.getState().equals(EnumProfileState.LOBBY)
                    || profile.getState().equals(EnumProfileState.EDITING)
                    || profile.getState().equals(EnumProfileState.WAITING)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

            if (player.getGameMode() == GameMode.SURVIVAL
                    && (profile.getState().equals(EnumProfileState.LOBBY)
                    || profile.getState().equals(EnumProfileState.EDITING)
                    || profile.getState().equals(EnumProfileState.WAITING))) {
                if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
                    event.setCancelled(true);
                }

                if (event.getSlotType() == InventoryType.SlotType.CRAFTING || event.isShiftClick() || event.getClick().isKeyboardClick()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(event.getPlayer().getUniqueId());

        if (profile.getState().equals(EnumProfileState.LOBBY)
                || profile.getState().equals(EnumProfileState.EDITING)
                || profile.getState().equals(EnumProfileState.WAITING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onHangingPlace(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        Profile profile = Alley.getInstance().getProfileRepository().getProfile(player.getUniqueId());

        if (profile.getState().equals(EnumProfileState.LOBBY) || profile.getState().equals(EnumProfileState.EDITING) || profile.getState().equals(EnumProfileState.WAITING)) {
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            event.setCancelled(true);
        }
    }
}
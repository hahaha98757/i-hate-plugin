package kr.hahaha98757.ihateplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class EventListener implements Listener {

    @EventHandler
    public void onEntityDeath(@NotNull final EntityDeathEvent event) {
        final LivingEntity target = event.getEntity();
        final Player killer = event.getEntity().getKiller();

        if (target instanceof Villager) {
            if (killer == null) return;
            NBTUtils.addNBT(killer);
            return;
        }
        if (target instanceof EnderDragon || target instanceof Wither) {
            if (killer == null) return;
            NBTUtils.addNBT(killer, -1);
            return;
        }

        if (!(target instanceof Player targetPlayer)) return;

        final int status = NBTUtils.getNBT(targetPlayer);

        NBTUtils.setNBT(targetPlayer, 0);

        if (killer == null) return;

        if (status <= 0) NBTUtils.addNBT(killer, 1 - status);
        else NBTUtils.addNBT(killer, -status);
    }

    @EventHandler
    public void onPlayerTick(@NotNull final PlayerTickEvent event) {
        updatePlayerScore();
        final Player player = event.player;

        if (NBTUtils.getNBT(player) == -3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2, 0, true, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 0, true, false, true));
        }
        if (NBTUtils.getNBT(player) >= 4)
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 2, 0, true, false, true));
        if (NBTUtils.getNBT(player) >= 5)
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 0, true, false, true));
        if (NBTUtils.getNBT(player) >= 10)
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2, 0, true, false, true));
    }

    @EventHandler
    public void onPlayerDeath(@NotNull final PlayerDeathEvent event) {
        if (event.getPlayer().getKiller() == null) return;
        final ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);

        final ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) if (meta instanceof SkullMeta skullMeta) {
            skullMeta.setOwningPlayer(event.getPlayer());
            itemStack.setItemMeta(skullMeta);
        }
        event.getDrops().add(itemStack);
    }

    @EventHandler
    public void onPlayerJoin(@NotNull final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        for (Player serverPlayer : IHatePlugin.instance.getServer().getOnlinePlayers()) {
            final Scoreboard scoreboard = serverPlayer.getScoreboard();
            final Objective objective = scoreboard.registerNewObjective("EvilStatus", Criteria.DUMMY, Component.text("악명"));
            objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

            final Score score = objective.getScore(player.getName());
            score.setScore(NBTUtils.getNBT(player));
            serverPlayer.setScoreboard(scoreboard);
        }
    }

    private void updatePlayerScore() {
        for (Player player : IHatePlugin.instance.getServer().getOnlinePlayers()) {
            final Objective objective = player.getScoreboard().getObjective("EvilStatus");
            if (objective != null)
                objective.getScore(player.getName()).setScore(NBTUtils.getNBT(player));
        }
    }

}
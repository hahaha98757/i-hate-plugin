package kr.hahaha98757.ihateplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public final class PlayerTickEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public final Player player;

    public PlayerTickEvent(@NotNull final Player player) {
        this.player = player;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public static void register(@NotNull JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) plugin.getServer().getPluginManager().callEvent(new PlayerTickEvent(player));
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}

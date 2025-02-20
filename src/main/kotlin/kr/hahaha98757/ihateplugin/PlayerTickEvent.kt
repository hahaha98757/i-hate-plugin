package kr.hahaha98757.ihateplugin

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class PlayerTickEvent(val player: Player): Event() {
    companion object {
        private val HANDLERS = HandlerList()

        @Suppress("unused")
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        fun register(plugin: JavaPlugin) {
            object: BukkitRunnable() {
                override fun run() {
                    for (player in plugin.server.onlinePlayers) plugin.server.pluginManager.callEvent(PlayerTickEvent(player))
                }
            }.runTaskTimer(plugin, 0, 1)
        }
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}
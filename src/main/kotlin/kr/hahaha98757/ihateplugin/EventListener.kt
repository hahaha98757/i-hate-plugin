package kr.hahaha98757.ihateplugin

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.entity.Wither
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import kotlin.math.max
import kotlin.math.min

class EventListener: Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val target = event.entity
        val killer = event.entity.killer

        if (target is Villager) {
            if (killer == null) return
            NBTUtils.addNBT(killer)
            return
        }
        if (target is EnderDragon || target is Wither) {
            if (killer == null) return
            NBTUtils.addNBT(killer, -1)
            return
        }

        if (target !is Player) return

        val status = NBTUtils.getNBT(target)

        NBTUtils.setNBT(target, 0)

        if (killer == null) return

        if (status <= 0) NBTUtils.addNBT(killer, 1 - status)
        else NBTUtils.addNBT(killer, -status)
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.damage > 3e38) return
        if (event.entity !is Player) return
        if (NBTUtils.getNBT(event.entity as Player) < 1) return
        if (NBTUtils.getNBT(event.entity as Player) < 3) event.damage = max(event.damage * 1.1, ++event.damage)
        else event.damage = max(event.damage * 1.25, event.damage + 2.5)
    }

    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player) return
        if (NBTUtils.getNBT(event.damager as Player) < 2) return
        if (NBTUtils.getNBT(event.damager as Player) < 3) event.damage = min(event.damage * 0.9, --event.damage)
        else event.damage = min(event.damage * 0.75, event.damage - 2.5)
    }

    @EventHandler
    fun onPlayerTick(event: PlayerTickEvent) {
        updatePlayerScore()
        val player = event.player

        if (NBTUtils.getNBT(player) == -3) {
            player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 2, 0, true, false, true))
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 0, true, false, true))
        }
        if (NBTUtils.getNBT(player) >= 4)
            player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 2, 0, true, false, true))
        if (NBTUtils.getNBT(player) >= 5)
            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 2, 0, true, false, true))
        if (NBTUtils.getNBT(player) >= 10)
            player.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 2, 0, true, false, true))
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        if (event.player.killer == null) return
        val itemStack = ItemStack(Material.PLAYER_HEAD, 1)

        itemStack.itemMeta = itemStack.itemMeta?.also { if (it is SkullMeta) it.owningPlayer = event.player }
        event.drops.add(itemStack)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        for (serverPlayer in IHatePlugin.instance.server.onlinePlayers) {
            val scoreboard = serverPlayer.scoreboard
            val objective = scoreboard.registerNewObjective("EvilStatus", Criteria.DUMMY, Component.text("악명"))
            objective.displaySlot = DisplaySlot.PLAYER_LIST

            val score = objective.getScore(player.name)
            score.score = NBTUtils.getNBT(player)
            serverPlayer.scoreboard = scoreboard
        }
    }

    private fun updatePlayerScore() {
        for (player in IHatePlugin.instance.server.onlinePlayers)
            player.scoreboard.getObjective("EvilStatus")?.getScore(player.name)?.score = NBTUtils.getNBT(player)
    }
}
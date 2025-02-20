package kr.hahaha98757.ihateplugin

import org.bukkit.plugin.java.JavaPlugin

class IHatePlugin: JavaPlugin() {
    companion object {
        lateinit var instance: JavaPlugin
    }

    init {
        instance = this
    }

    override fun onEnable() {
        PlayerTickEvent.register(this)
        server.pluginManager.registerEvents(EventListener(), this)
        logger.info("I Hate Plugin v2.0.0 enable")
    }

    override fun onDisable() {
        logger.info("I Hate Plugin v2.0.0 disable")
    }
}

package kr.hahaha98757.ihateplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class IHatePlugin extends JavaPlugin {

    public static JavaPlugin instance;

    public IHatePlugin() {
        instance = this;
    }

    @Override
    public void onEnable() {
        PlayerTickEvent.register(this);
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("I Hate Plugin v2.0.0 enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("I Hate Plugin v2.0.0 disable");
    }
}
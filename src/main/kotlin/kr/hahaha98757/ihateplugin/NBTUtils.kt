package kr.hahaha98757.ihateplugin

import org.bukkit.entity.Player
import org.json.JSONObject
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object NBTUtils {

    private fun getPlayerDataFile(player: Player): File {
        val dataFolder = File("plugins/JustNormalPlugin/playerData")
        if (!dataFolder.exists()) dataFolder.mkdirs()
        return File(dataFolder, "${player.uniqueId}.json")
    }

    fun setNBT(player: Player, value: Int) {
        val file = getPlayerDataFile(player)
        val json = JSONObject()
        json.put("EvilStatus", value)

        FileWriter(file).use { it.write(json.toString()) }
    }

    fun getNBT(player: Player): Int {
        val file = getPlayerDataFile(player)
        if (!file.exists()) return 0

        val json = JSONObject(FileReader(file).readText())
        return json.optInt("EvilStatus", 0)
    }

    fun addNBT(player: Player, value: Int = 1) = setNBT(player, getNBT(player) + value)
}
package kr.hahaha98757.ihateplugin;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.*;

public final class NBTUtils {

    @NotNull
    private static File getPlayerDataFile(@NotNull final Player player) {
        final File dataFolder = new File("plugins/JustNormalPlugin/playerData");
        if (!dataFolder.exists()) //noinspection ResultOfMethodCallIgnored
            dataFolder.mkdirs();
        return new File(dataFolder, player.getUniqueId() + ".json");
    }

    public static void setNBT(@NotNull final Player player, final int value) {
        final File file = getPlayerDataFile(player);
        final JSONObject json = new JSONObject();
        json.put("EvilStatus", value);

        try (final FileWriter writer = new FileWriter(file)) {
            writer.write(json.toString());
        } catch (IOException ignored) {
        }
    }

    public static int getNBT(@NotNull final Player player) {
        final File file = getPlayerDataFile(player);
        if (!file.exists()) return 0;

        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            final StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) content.append(line);

            final JSONObject json = new JSONObject(content.toString());
            return json.optInt("EvilStatus", 0);
        } catch (IOException e) {
            return 0;
        }
    }

    public static void addNBT(@NotNull final Player player, final int value) {
        setNBT(player, getNBT(player) + value);
    }

    public static void addNBT(@NotNull final Player player) {
        setNBT(player, getNBT(player) + 1);
    }
}

package org.tortel.blissfulcore

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.tortel.blissfulcore.Commands.ChangeTeamCommand
import org.tortel.blissfulcore.Commands.EmojisCommand
import org.tortel.blissfulcore.Events.OnPlayerChat

class Main : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic

        val bukkitRunnable = object: BukkitRunnable() { // runs every second
            override fun run() {
               Functions.GetScores()

                Functions.ReturnPlacements()

               // Bukkit.broadcastMessage("${Bukkit.getBukkitVersion()} ${Bukkit.getVersion()}")

                // Bukkit.broadcastMessage(Component.text(" §x§0§0§f§f§f§f~").toString())

            }
        }.runTaskTimer(this, 1, 40) // every 2 seconds.

        getCommand("changeofflineplayerteam")?.setExecutor(ChangeTeamCommand())
        getCommand("emojis")?.setExecutor(EmojisCommand())
        Bukkit.getPluginManager().registerEvents(OnPlayerChat(), this)
    }

}

package org.tortel.blissfulcore.Events


import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.inventory.InventoryHolder
import org.bukkit.scoreboard.Team
import org.tortel.blissfulcore.Functions


class OnPlayerChat : Listener {
    @EventHandler
    fun onPlayerChatted(event: PlayerChatEvent) {
        val player = event.player
        val MSG = event.message
        var Format = event.format
        val recipents = event.recipients
        val inv : InventoryHolder = player
        //player.sendMessage("You Typed $MSG, with Format $Format, recipents $recipents")
       // if (!MSG.lowercase().contains(":")) return

       //val newMSG = EmojiUnicode.module.returnMSGwithUniCode(MSG, player)

        val newComponent = Functions.emojiModule.returnMSGwithUniCode(MSG, player)
        //var midpart = TextComponent(*TextComponent.fromLegacyText("yeh"))
        //midpart.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText("neh"))))

        //player.sendMessage(newMSG)
        // we need to get the first : in the emoji.
        val teamprefix = getteam(player)?.prefix
        val teamcolor = getteam(player)?.color
        val teamsuffix = getteam(player)?.suffix
        var playercomponent = TextComponent("${teamprefix}${teamcolor}${player.name}${teamsuffix}: ")

       playercomponent.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, ComponentBuilder("{name:${player.name} , type: Player , id: ${player.uniqueId}}").create())//BaseComponent("{name: ${player.name} , type: $player , id: ${player.uniqueId} }"))

        event.recipients.forEach { recipient ->
            recipient.spigot().sendMessage(playercomponent, newComponent) //midpart , TextComponent(*TextComponent.fromLegacyText("$newMSG"))
        }

        // Cancel the original chat event
        event.isCancelled = true
        //event.setMessage(newMSG)
    }

    fun getteam(player: Player) : Team? {
        return  player.scoreboard.getPlayerTeam(Bukkit.getOfflinePlayer(player.name))!!
    }
}

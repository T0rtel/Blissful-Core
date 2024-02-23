package org.tortel.blissfulcore.Commands


import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.tortel.blissfulcore.Functions

class EmojisCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        var Text = TextComponent("Emojis: ")
        val emojiTable = Functions.emojiModule.emojiTable
        for (thing in emojiTable){
            val newtext = TextComponent(thing.value + " ")
            newtext.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent(*TextComponent.fromLegacyText(thing.key))))

            Text.addExtra(newtext)
        }
        sender.spigot().sendMessage(Text)


        return false
    }
}
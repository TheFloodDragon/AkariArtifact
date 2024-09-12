package com.github.cpjinan.plugin.akariartifact.module.ui.internal.command

import com.github.cpjinan.plugin.akariartifact.core.utils.CommandUtil
import com.github.cpjinan.plugin.akariartifact.module.ui.api.UIAPI
import com.github.cpjinan.plugin.akariartifact.module.ui.api.UIAPI.openUI
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandContext
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.command.suggest
import taboolib.module.chat.colored
import taboolib.module.lang.sendLang

object UICommand {
    val ui = subCommand {
        literal("open").dynamic("id") {
            suggest { UIAPI.getUINames() }
            execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                sender.cast<Player>().openUI(context["id"])
                sender.sendLang(
                    "UI-Open",
                    sender.name,
                    context["id"]
                )
            }
        }.player("player") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                context.player().cast<Player>().openUI(context["id"])
                sender.sendLang(
                    "UI-Open",
                    context.player().cast<Player>().name,
                    context["id"]
                )
            }
        }.dynamic("options") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, content: String ->
                val args = CommandUtil.parseOptions(content.split(" "))
                var silent = false

                for ((k, _) in args) {
                    when (k.lowercase()) {
                        "silent" -> silent = true
                    }
                }

                context.player().cast<Player>().openUI(context["id"])
                if (!silent) sender.sendLang(
                    "UI-Open",
                    context.player().cast<Player>().name,
                    context["id"]
                )
            }
        }

        literal("list") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, _: String ->
                sender.sendLang("UI-List")
                sender.sendMessage(
                    UIAPI.getUINames().joinToString(separator = "&7, ".colored()) { "&f${it}".colored() })
            }
        }
    }
}
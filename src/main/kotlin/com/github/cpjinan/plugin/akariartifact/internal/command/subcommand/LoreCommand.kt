package com.github.cpjinan.plugin.akariartifact.internal.command.subcommand

import com.github.cpjinan.plugin.akariartifact.utils.CommandUtil
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandContext
import taboolib.common.platform.command.int
import taboolib.common.platform.command.subCommand
import taboolib.module.chat.colored
import taboolib.module.lang.sendLang
import taboolib.platform.util.isNotAir
import taboolib.platform.util.modifyLore

object LoreCommand {
    val clipboard: HashMap<Player, String> = hashMapOf()

    val lore = subCommand {
        literal("check") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, _: String ->
                sender.castSafely<Player>().let {
                    val item = it?.inventory?.itemInMainHand
                    if (item.isNotAir()) {
                        sender.sendLang("Check-Lore")
                        item.itemMeta?.lore?.forEachIndexed { index, content ->
                            sender.sendMessage("&7${index + 1} &8| $content".colored())
                        }
                    } else sender.sendLang("Air-In-Hand")
                }
            }
        }

        literal("add") {
            dynamic("lore") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                add(context["lore"].colored())
                            }
                            sender.sendLang("Add-Lore", context["lore"].colored())
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                add(context["lore"].colored())
                            }
                            if (!silent) sender.sendLang("Add-Lore", context["lore"].colored())
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("remove") {
            int("line") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                removeAt(context.int("line") - 1)
                            }
                            sender.sendLang("Remove-Lore", context.int("line"))
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                removeAt(context.int("line") - 1)
                            }
                            if (!silent) sender.sendLang("Remove-Lore", context.int("line"))
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("set") {
            int("line").dynamic("lore") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                set(context.int("line") - 1, context["lore"].colored())
                            }
                            sender.sendLang("Set-Lore", context.int("line"), context["lore"].colored())
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                set(context.int("line") - 1, context["lore"].colored())
                            }
                            if (!silent) sender.sendLang("Set-Lore", context.int("line"), context["lore"].colored())
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("insert") {
            int("line").dynamic("lore") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                add(context.int("line"), context["lore"].colored())
                            }
                            sender.sendLang(
                                "Insert-Lore",
                                context.int("line"),
                                context.int("line") + 1,
                                context["lore"].colored()
                            )
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                add(context.int("line"), context["lore"].colored())
                            }
                            if (!silent) sender.sendLang(
                                "Insert-Lore",
                                context.int("line"),
                                context.int("line") + 1,
                                context["lore"].colored()
                            )
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("clear") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, _: String ->
                sender.castSafely<Player>().let {
                    val item = it?.inventory?.itemInMainHand
                    if (item.isNotAir()) {
                        item.modifyLore {
                            clear()
                        }
                        sender.sendLang("Clear-Lore")
                    } else sender.sendLang("Air-In-Hand")
                }
            }
        }.dynamic("options") {
            execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, content: String ->
                val args = CommandUtil.parseOptions(content.split(" "))
                var silent = false

                for ((k, _) in args) {
                    when (k.lowercase()) {
                        "silent" -> silent = true
                    }
                }

                sender.castSafely<Player>().let {
                    val item = it?.inventory?.itemInMainHand
                    if (item.isNotAir()) {
                        item.modifyLore {
                            clear()
                        }
                        if (!silent) sender.sendLang("Clear-Lore")
                    } else sender.sendLang("Air-In-Hand")
                }
            }
        }

        literal("clone") {
            int("lineA").int("lineB") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                set(context.int("lineB") - 1, get(context.int("lineA") - 1))
                            }
                            sender.sendLang("Clone-Lore", context.int("lineA"), context.int("lineB"))
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                set(context.int("lineB") - 1, get(context.int("lineA") - 1))
                            }
                            if (!silent) sender.sendLang("Clone-Lore", context.int("lineA"), context.int("lineB"))
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("copy") {
            int("line") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                val value = get(context.int("line") - 1)
                                clipboard[it] = value
                                sender.sendLang("Copy-Lore", context.int("line"), value)
                            }
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                val value = get(context.int("line") - 1)
                                clipboard[it] = value
                                if (!silent) sender.sendLang("Copy-Lore", context.int("line"), value)
                            }
                        } else sender.sendLang("Air-In-Hand")
                    }

                }
            }
        }

        literal("paste") {
            int("line") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                clipboard[it]?.colored()?.let { value ->
                                    if (clipboard.isNotEmpty()) {
                                        set(context.int("line"), value)
                                        sender.sendLang("Paste-Lore", context.int("line"), value)
                                    } else sender.sendLang("Clipboard-Empty")
                                }
                            }
                        } else sender.sendLang("Air-In-Hand")
                    }
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

                    sender.castSafely<Player>().let {
                        val item = it?.inventory?.itemInMainHand
                        if (item.isNotAir()) {
                            item.modifyLore {
                                clipboard[it]?.colored()?.let { value ->
                                    if (clipboard.isNotEmpty()) {
                                        set(context.int("line"), value)
                                        if (!silent) sender.sendLang("Paste-Lore", context.int("line"), value)
                                    } else if (!silent) sender.sendLang("Clipboard-Empty")
                                }
                            }
                        } else sender.sendLang("Air-In-Hand")
                    }
                }
            }
        }

        literal("clipboard") {

            literal("check") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        if (clipboard[it]?.isNotEmpty() == true) {
                            sender.sendLang(
                                "Clipboard-Check",
                                clipboard[it]!!
                            )
                        } else sender.sendLang("Clipboard-Empty")
                    }
                }
            }

            literal("set") {
                dynamic("value") {
                    execute<ProxyCommandSender> { sender: ProxyCommandSender, context: CommandContext<ProxyCommandSender>, _: String ->
                        sender.castSafely<Player>().let {
                            clipboard[it!!] = context["value"]
                        }
                        sender.sendLang("Clipboard-Set", context["value"])
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

                        sender.castSafely<Player>().let {
                            clipboard[it!!] = context["value"]
                        }

                        if (!silent) sender.sendLang("Clipboard-Set", context["value"])
                    }
                }
            }

            literal("clear") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, _: String ->
                    sender.castSafely<Player>().let {
                        clipboard[it!!] = ""
                        sender.sendLang("Clipboard-Clear")
                    }
                }
            }.dynamic("options") {
                execute<ProxyCommandSender> { sender: ProxyCommandSender, _: CommandContext<ProxyCommandSender>, content: String ->
                    val args = CommandUtil.parseOptions(content.split(" "))
                    var silent = false

                    for ((k, _) in args) {
                        when (k.lowercase()) {
                            "silent" -> silent = true
                        }
                    }

                    sender.castSafely<Player>().let {
                        clipboard[it!!] = ""
                    }

                    if (!silent) sender.sendLang("Clipboard-Clear")
                }
            }

        }
    }
}
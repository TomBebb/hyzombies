package com.tomb.hyzombies.commands

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.tomb.hyzombies.State
import com.tomb.hyzombies.ui.MyUI
import java.util.concurrent.CompletableFuture


/**
 * /hyz help - Show available commands
 */
class StartCommand : AbstractPlayerCommand("zombies_start", "Start zombies") {


    override fun execute(
        context: CommandContext,
        store: Store<EntityStore?>,
        ref: Ref<EntityStore?>,
        playerRef: PlayerRef,
        world: World
    ) {
        val player = context.senderAs<Player>(Player::class.java)
        State.reset()
        CompletableFuture.runAsync {
            player.hudManager.setCustomHud(playerRef, MyUI(playerRef))
        }
    }
}

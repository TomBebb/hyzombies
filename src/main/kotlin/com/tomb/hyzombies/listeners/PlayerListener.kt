package com.tomb.hyzombies.listeners

import com.hypixel.hytale.event.EventRegistry
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.tomb.hyzombies.State
import com.tomb.hyzombies.ui.MyUI
import java.util.logging.Level


/**
 * Listener for player connection events.
 */
class PlayerListener {

    companion object {
        private val LOGGER = HytaleLogger.forEnclosingClass()
    }

    /**
     * Register all player event listeners.
     */
    fun register(eventBus: EventRegistry) {
        // PlayerConnectEvent
        try {
            eventBus.registerGlobal<String, PlayerReadyEvent>(PlayerReadyEvent::class.java, ::onPlayerReady)
            LOGGER.at(Level.INFO).log("[HyZombies] Registered PlayerConnectEvent listener")
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).withCause(e).log("[HyZombies] Failed to register PlayerConnectEvent")
        }

        // PlayerDisconnectEvent
        try {
            eventBus.registerGlobal<Void, PlayerDisconnectEvent>(
                PlayerDisconnectEvent::class.java,
                ::onPlayerDisconnect
            )
            LOGGER.at(Level.INFO).log("[HyZombies] Registered PlayerDisconnectEvent listener")
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).withCause(e).log("[HyZombies] Failed to register PlayerDisconnectEvent")
        }
    }

    private fun onPlayerReady(event: PlayerReadyEvent) {
        val playerRef = event.playerRef.store.getComponent<PlayerRef>(event.playerRef, PlayerRef.getComponentType());
        val player = event.player;

        player.hudManager.setCustomHud(playerRef!!, MyUI(playerRef))
        LOGGER.at(Level.INFO).log("[HyZombies] Player %s ready  \\ to world", player.toString())

        // TODO: Add your player join logic here
    }


    private fun onPlayerDisconnect(event: PlayerDisconnectEvent) {
        val playerName = event.playerRef?.username ?: "Unknown"
        State.playerScores.remove(event.playerRef.uuid)

        LOGGER.at(Level.INFO).log("[HyZombies] Player %s disconnected", playerName)

        // TODO: Add your player leave logic here
    }
}
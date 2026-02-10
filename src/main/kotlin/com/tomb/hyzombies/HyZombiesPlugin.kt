package com.tomb.hyzombies

import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.event.EventRegistry
import com.hypixel.hytale.logger.HytaleLogger
import com.tomb.hyzombies.commands.StartCommand
import com.tomb.hyzombies.listeners.PlayerListener
import java.util.logging.Level

/**
 * HyZombies - A Hytale server plugin.
 */
class HyZombiesPlugin(init: JavaPluginInit) : JavaPlugin(init) {

    companion object {
        private val LOGGER = HytaleLogger.forEnclosingClass()

        @JvmStatic
        var instance: HyZombiesPlugin? = null
            private set
    }

    init {
        instance = this
    }

    override fun setup() {
        LOGGER.at(Level.INFO).log("[HyZombies] Setting up...")

        // Register commands
        registerCommands()

        // Register event listeners
        registerListeners()

        LOGGER.at(Level.INFO).log("[HyZombies] Setup complete!")
    }

    private fun registerCommands() {
        try {
            commandRegistry.registerCommand(StartCommand())
            LOGGER.at(Level.INFO).log("[HyZombies] Registered /hyz command")
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).withCause(e).log("[HyZombies] Failed to register commands")
        }
    }

    private fun registerListeners() {
        val eventBus: EventRegistry = eventRegistry

        try {
            PlayerListener().register(eventBus)
            LOGGER.at(Level.INFO).log("[HyZombies] Registered player event listeners")
        } catch (e: Exception) {
            LOGGER.at(Level.WARNING).withCause(e).log("[HyZombies] Failed to register listeners")
        }
    }

    override fun start() {
        LOGGER.at(Level.INFO).log("[HyZombies] Started!")
        LOGGER.at(Level.INFO).log("[HyZombies] Use /hyz help for commands")
    }

    override fun shutdown() {
        LOGGER.at(Level.INFO).log("[HyZombies] Shutting down...")
        instance = null
    }
}
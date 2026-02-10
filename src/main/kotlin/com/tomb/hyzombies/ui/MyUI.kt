package com.tomb.hyzombies.ui

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef

class MyUI(playerRef: PlayerRef) : CustomUIHud(playerRef) {
    override fun build(p0: UICommandBuilder) {
        p0.append("hyzombies/Dashboard.ui")
    }
}
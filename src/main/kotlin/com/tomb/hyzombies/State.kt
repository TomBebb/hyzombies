package com.tomb.hyzombies

import java.util.Dictionary
import java.util.UUID

class State {

    public companion object {

        val zombiesPerRoundInc = 2
        val maxZombies = 1

        val playerScores: HashMap<UUID, Int> = HashMap<UUID, Int>()


        fun getTotalRoundZombies(): Int {
            return round * zombiesPerRoundInc
        }


        public fun reset() {
            round = 1
        }

        var round = 1;


    }
}
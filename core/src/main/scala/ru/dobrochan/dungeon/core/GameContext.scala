package ru.dobrochan.dungeon.core

import ru.dobrochan.dungeon.core.models.Model
import ru.dobrochan.dungeon.core.views.View

class GameContext(val id: Int, val player: Player, val model: Model, val view: View)
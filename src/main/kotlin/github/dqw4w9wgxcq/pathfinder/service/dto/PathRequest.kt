package github.dqw4w9wgxcq.pathfinder.service.dto

import github.dqw4w9wgxcq.pathfinder.domain.Agent
import github.dqw4w9wgxcq.pathfinder.domain.Position

data class PathRequest(val start: Position, val finish: Position, val agent: Agent?)
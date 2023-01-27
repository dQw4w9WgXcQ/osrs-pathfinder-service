package github.dqw4w9wgxcq.pathfinder.service.dto

import github.dqw4w9wgxcq.pathfinder.commons.domain.Agent
import github.dqw4w9wgxcq.pathfinder.commons.domain.Position

data class PathRequest(val start: Position, val finish: Position, val agent: Agent?)
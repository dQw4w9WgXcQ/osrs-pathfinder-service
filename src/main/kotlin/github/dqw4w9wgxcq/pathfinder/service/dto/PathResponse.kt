package github.dqw4w9wgxcq.pathfinder.service.dto

import github.dqw4w9wgxcq.pathfinder.domain.Position
import github.dqw4w9wgxcq.pathfinder.graph.domain.PathStep

data class PathResponse(val start: Position, val finish: Position, val steps: List<PathStep>?)
package github.dqw4w9wgxcq.pathfinder.service

import github.dqw4w9wgxcq.pathfinder.domain.Agent
import github.dqw4w9wgxcq.pathfinder.pathfinding.Pathfinding
import github.dqw4w9wgxcq.pathfinder.pathfinding.store.GraphStore
import github.dqw4w9wgxcq.pathfinder.pathfinding.store.LinkStore
import github.dqw4w9wgxcq.pathfinder.service.dto.PathRequest
import github.dqw4w9wgxcq.pathfinder.service.dto.PathResponse
import org.springframework.stereotype.Service

@Service
class PathfindingService {
    private val pathfinding: Pathfinding

    init {
        val linkStore = LinkStore.load(this::class.java.getResourceAsStream("/static/links.zip"))
        val graphStore = GraphStore.load(this::class.java.getResourceAsStream("/static/graph.zip"), linkStore.links)

        pathfinding = Pathfinding.create(graphStore)
    }

    fun findPath(pathRequest: PathRequest): PathResponse {
        val agent = pathRequest.agent ?: Agent(1, emptyMap(), emptyList())

        val result = pathfinding.findPath(pathRequest.start, pathRequest.finish, agent)

        return PathResponse(result.start, result.finish, result.steps)
    }
}
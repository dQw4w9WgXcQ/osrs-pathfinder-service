package github.dqw4w9wgxcq.pathfinder.service

import github.dqw4w9wgxcq.pathfinder.domain.Agent
import github.dqw4w9wgxcq.pathfinder.graph.Pathfinding
import github.dqw4w9wgxcq.pathfinder.graph.domain.ComponentGrid
import github.dqw4w9wgxcq.pathfinder.graph.domain.PathStep
import github.dqw4w9wgxcq.pathfinder.graph.linkdistances.LinkDistanceCache
import github.dqw4w9wgxcq.pathfinder.graph.localpaths.LocalPathCache
import github.dqw4w9wgxcq.pathfinder.graph.store.GraphStore
import github.dqw4w9wgxcq.pathfinder.graph.store.LinkStore
import github.dqw4w9wgxcq.pathfinder.service.dto.PathRequest
import github.dqw4w9wgxcq.pathfinder.service.dto.PathResponse
import org.springframework.stereotype.Service

@Service
class PathfinderService {
    private val componentGrid: ComponentGrid
    private val pathfinding: Pathfinding

    init {
        val linkStore = LinkStore.load(this::class.java.getResourceAsStream("/static/links.zip"))
        val graphStore = GraphStore.load(this::class.java.getResourceAsStream("/static/graph.zip"), linkStore.links)

        componentGrid = ComponentGrid(graphStore.planes)
        val componentGraph = graphStore.componentGraph
        val linkDistances = LinkDistanceCache(componentGrid, componentGraph)
        val localPaths = LocalPathCache(componentGrid)

        pathfinding = Pathfinding(componentGrid, componentGraph, linkDistances, localPaths)
    }

    fun findPath(pathRequest: PathRequest): PathResponse {
        val agent = pathRequest.agent ?: Agent(1, emptyMap(), emptyList())

        val start = Pathfinding.findClosestNotBlocked(componentGrid.planes(), pathRequest.start)
        val finish = Pathfinding.findClosestNotBlocked(componentGrid.planes(), pathRequest.finish)

        val path = pathfinding.findPath(pathRequest.start, pathRequest.finish, agent)

        return PathResponse(start, finish, path as List<PathStep>?)
    }
}
package github.dqw4w9wgxcq.pathfinder.service

import github.dqw4w9wgxcq.pathfinder.service.dto.PathRequest
import github.dqw4w9wgxcq.pathfinder.service.dto.PathResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class OsrsPathfinderServiceApplication(val pathfindingService: PathfindingService) {
    @CrossOrigin
    @PostMapping("/")
    fun index(@RequestBody req: PathRequest): ResponseEntity<PathResponse> {
        val res = pathfindingService.findPath(req)
        return ResponseEntity.ok(res)
    }
}

fun main(args: Array<String>) {
    runApplication<OsrsPathfinderServiceApplication>(*args)
}
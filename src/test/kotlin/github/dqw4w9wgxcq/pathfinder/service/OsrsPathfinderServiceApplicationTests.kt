package github.dqw4w9wgxcq.pathfinder.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OsrsPathfinderServiceApplicationTests {
    private val log = LoggerFactory.getLogger(OsrsPathfinderServiceApplicationTests::class.java)

    @Autowired
    private lateinit var pathfinderService: PathfinderService

    @Test
    fun contextLoads() {
        assertThat(pathfinderService).isNotNull
    }

}

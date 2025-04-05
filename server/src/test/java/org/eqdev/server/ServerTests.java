package org.eqdev.server;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class ServerTests {

    Log log = org.apache.commons.logging.LogFactory.getLog(ServerTests.class);

	@Test
	void contextLoads() {
        // Log test successful
        log.info("Context load successful");
	}
}
package org.eqdev;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationContextTest {
    Logger logger = Logger.getLogger(ApplicationContextTest.class.getName());
    
    @Test
    void contextLoads() {
        logger.info("Application context loaded successfully.");
        System.out.println("Application context loaded successfully.");
    }
}
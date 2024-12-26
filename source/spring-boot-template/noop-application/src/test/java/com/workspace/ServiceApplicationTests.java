/**
 * Description: Template Controller.
 *
 * @author: Ashwin Padmakumar
 * @since: 23/07/21
 * @version: 0.1
 */

package com.workspace;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceApplicationTests {

  public static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

  @Test
  void contextLoads() {
    logger.info("Running the test case");
    assertTrue(true);
  }

}

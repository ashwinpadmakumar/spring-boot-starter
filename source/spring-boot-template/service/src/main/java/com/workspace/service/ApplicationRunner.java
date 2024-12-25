/**
 * Description: Class Description.
 *
 * @author: Ashwin Padmakumar
 * @since: 2022-10-16
 * @version: 0.1
 */

package com.workspace.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.workspace.service.properties.TestProperties;

@Component
public class ApplicationRunner implements CommandLineRunner {

  public static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

  private final TestProperties testProperties;

  public ApplicationRunner(TestProperties testProperties) {
    this.testProperties = testProperties;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.debug("Environment: {}", testProperties.getEnv());
  }
}

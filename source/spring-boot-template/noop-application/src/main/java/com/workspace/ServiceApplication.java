/**
 * Description: Template Controller.
 *
 * @author: Ashwin Padmakumar
 * @since: 23/07/21
 * @version: 0.1
 */

package com.workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {

  public static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ServiceApplication.class, args);
    logger.debug("Starting the service application");
  }

}

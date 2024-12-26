/**
 * Description: Class Description.
 *
 * @author: Ashwin Padmakumar
 * @since: 2022-10-16
 * @version: 0.1
 */

package com.workspace.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "test-properties")
public class TestProperties {

  private String env;

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }
}

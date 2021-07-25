/**
 * Description: Template Controller.
 *
 * @author: Ashwin Padmakumar
 * @since: 23/07/21
 * @version: 0.1
 */

package com.workspace.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/noop")
@Slf4j
public class TemplateController {

  @GetMapping
  public ResponseEntity<String> noop() {
    return new ResponseEntity<>("Hello Everyone", HttpStatus.OK);
  }
}

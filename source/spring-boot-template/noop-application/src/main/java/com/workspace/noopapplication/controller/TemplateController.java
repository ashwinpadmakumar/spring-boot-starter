/**
 * Description: Class Description.
 *
 * @author: Ashwin Padmakumar
 * @since: 2022-10-16
 * @version: 0.1
 */


package com.workspace.noopapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/noop")
public class TemplateController {

  @GetMapping
  public ResponseEntity<String> noop() {
    return new ResponseEntity<>("No Operation", HttpStatus.OK);
  }
}

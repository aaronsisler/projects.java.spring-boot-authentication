package com.ebsolutions.projects.java.springboot.authentication.test;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

  @GetMapping
  public ResponseEntity<List<String>> get() {
    return ResponseEntity.ok(Collections.singletonList("This is only a test!"));
  }
}

package za.ac.nwu.as.web.sb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("")
    public ResponseEntity<String> greet() {
        return new ResponseEntity<>("Hi there!", HttpStatus.OK);
    }

    @GetMapping("demo/{name}")
    public ResponseEntity<String> sayMyName(@PathVariable String name) {
        return new ResponseEntity<>(String.format("Hello %s", name), HttpStatus.OK);
    }
}

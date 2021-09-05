package za.ac.nwu.as.web.sb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.as.domain.service.GeneralResponse;

@RestController
@RequestMapping("member")
public class MemberController {

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse<String>> fetchAll() {
        return new ResponseEntity<>(new GeneralResponse<>(true, "Hello world!"), HttpStatus.OK);
    }
}

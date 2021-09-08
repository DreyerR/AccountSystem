package za.ac.nwu.as.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchMemberFlow;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    private final FetchMemberFlow fetchMemberFlow;

    @Autowired
    public MemberController(FetchMemberFlow fetchMemberFlow) {
        this.fetchMemberFlow = fetchMemberFlow;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse<List<MemberDto>>> fetchAll() {
        List<MemberDto> members = fetchMemberFlow.fetchAllMembers();
        GeneralResponse<List<MemberDto>> response = new GeneralResponse<>(true, members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

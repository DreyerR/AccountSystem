package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchMemberFlow;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private final FetchMemberFlow fetchMemberFlow;

    @Autowired
    public MemberController(FetchMemberFlow fetchMemberFlow) {
        this.fetchMemberFlow = fetchMemberFlow;
    }

    @GetMapping()
    @ApiOperation(value = "Fetches all the members", notes = "Returns a list of members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Members Returned Successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<MemberDto>>> fetchAll() {
        List<MemberDto> members = fetchMemberFlow.fetchAllMembers();
        GeneralResponse<List<MemberDto>> response = new GeneralResponse<>(true, members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Fetches a member by ID", notes = "Returns a single member specified by an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member Returned Successfully", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Member Not Found", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> fetchById(@PathVariable Integer id) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        MemberDto memberDto = fetchMemberFlow.findMemberById(id);

        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, memberDto);

        if (null != memberDto)
            httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(response, httpStatus);
    }
}

package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CurrencyFlow;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    private final CurrencyFlow currencyFlow;

    @Autowired
    public CurrencyController(CurrencyFlow currencyFlow) {
        this.currencyFlow = currencyFlow;
    }

    @PutMapping("/add/{memberId}")
    @ApiOperation(value = "Updates a member's currency", notes = "Update currency based on the member ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Added Currency", response = GeneralResponse.class),
            @ApiResponse(code = 204, message = "Could Not Add Currency", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<String>> addCurrency(@PathVariable Integer memberId,
                                                       @RequestParam(name = "amount")BigDecimal amount) {
        boolean isSuccessful = currencyFlow.addCurrency(memberId, amount);
        String message;
        HttpStatus status;
        if (isSuccessful) {
            message = "Currency added successfully";
            status = HttpStatus.OK;
        }
        else {
            message = "Currency could not be added";
            status = HttpStatus.NO_CONTENT;
        }
        GeneralResponse<String> generalResponse = new GeneralResponse<>(isSuccessful, message);
        return new ResponseEntity<>(generalResponse, status);
    }
}

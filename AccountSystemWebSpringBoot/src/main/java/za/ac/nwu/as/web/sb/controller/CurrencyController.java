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
    @ApiOperation(value = "Adds currency to a member's account", notes = "Add currency based on the member ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Added Currency", response = GeneralResponse.class),
            @ApiResponse(code = 204, message = "Could Not Add Currency", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<String>> addCurrency(@PathVariable Integer memberId,
                                                       @RequestParam(name = "amount")BigDecimal amount) {
        return new ResponseEntity<>(currencyFlow.addCurrency(memberId, amount), HttpStatus.OK);
    }

    @PutMapping("/sub/{memberId}")
    @ApiOperation(value = "Subtracts currency from a member's account", notes = "Subtract currency based on the member ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Subtracted Currency", response = GeneralResponse.class),
            @ApiResponse(code = 204, message = "Could Not Subtract Currency", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<String>> subtractCurrency(@PathVariable Integer memberId,
                                                                    @RequestParam(name = "amount")BigDecimal amount) {
        return new ResponseEntity<>(currencyFlow.subtractCurrency(memberId, amount), HttpStatus.OK);
    }
}

package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchCurrencyFlow;
import za.ac.nwu.as.logic.flow.ModifyCurrencyFlow;
import za.ac.nwu.as.logic.flow.impl.FetchCurrencyFlowImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);
    private final ModifyCurrencyFlow modifyCurrencyFlow;
    private final FetchCurrencyFlow fetchCurrencyFlow;

    @Autowired
    public CurrencyController(ModifyCurrencyFlow modifyCurrencyFlow, FetchCurrencyFlow fetchCurrencyFlow) {
        this.modifyCurrencyFlow = modifyCurrencyFlow;
        this.fetchCurrencyFlow = fetchCurrencyFlow;
    }

    @PutMapping("/add/{memberId}")
    @ApiOperation(value = "Adds currency to a member's account", notes = "Add currency based on the member ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Added Currency", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<String>> addCurrency(
            @PathVariable Integer memberId,
            @RequestParam(name = "amount")BigDecimal amount,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        LOGGER.info("The value of date is {}", date);
        return new ResponseEntity<>(modifyCurrencyFlow.addCurrency(memberId, amount, date), HttpStatus.OK);
    }

    @PutMapping("/sub/{memberId}")
    @ApiOperation(value = "Subtracts currency from a member's account", notes = "Subtract currency based on the member ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Subtracted Currency", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<String>> subtractCurrency(@PathVariable Integer memberId,
                                                                    @RequestParam(name = "amount")BigDecimal amount) {
        return new ResponseEntity<>(modifyCurrencyFlow.subtractCurrency(memberId, amount), HttpStatus.OK);
    }

    @GetMapping("/{currencyId}")
    @ApiOperation(value = "Returns a currency object", notes = "Returns a currency based on an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned Currency Successfully "),
            @ApiResponse(code = 404, message = "Currency Not Found")
    })
    public ResponseEntity<GeneralResponse<CurrencyDto>> fetchCurrencyById(@PathVariable Integer currencyId) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        CurrencyDto currencyDto = fetchCurrencyFlow.fetchCurrencyById(currencyId);
        if (null != currencyDto)
            httpStatus = HttpStatus.OK;

        GeneralResponse<CurrencyDto> generalResponse = new GeneralResponse<>(true, currencyDto);
        return new ResponseEntity<>(generalResponse, httpStatus);
    }

    @GetMapping("")
    @ApiOperation(value = "Returns a member's currency object", notes = "Returns a currency object based on a member's ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned Currency Successfully"),
            @ApiResponse(code = 404, message = "Currency Not Found")
    })
    public ResponseEntity<GeneralResponse<CurrencyDto>> fetchCurrencyByMemberId(
            @RequestParam(name = "memberId")Integer memberId) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        CurrencyDto currencyDto = fetchCurrencyFlow.fetchCurrencyByMemberId(memberId);
        if (null != currencyDto)
            httpStatus = HttpStatus.OK;

        GeneralResponse<CurrencyDto> generalResponse = new GeneralResponse<>(true, currencyDto);
        return new ResponseEntity<>(generalResponse, httpStatus);
    }

    @PutMapping("/currency-type")
    @ApiOperation(value = "Updates currency types", notes = "Updates all the currency types to a new one")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully Updated CurrencyType Names"),
            @ApiResponse(code = 404, message = "CurrencyType Name(s) Not Found")
    })
    public ResponseEntity<GeneralResponse<String>> updateCurrencyTypesByName(
            @RequestParam(name = "fromCT") String fromCT,
            @RequestParam(name = "toCT") String toCT) {

        HttpStatus httpStatus = HttpStatus.OK;
        String message = String.format("Successfully updated currency types from %s to %s",
                fromCT.toUpperCase(), toCT.toUpperCase());

        int isSuccessful = modifyCurrencyFlow.updateCurrencyTypes(fromCT.toUpperCase(), toCT.toUpperCase());

        if (-1 == isSuccessful) {
            message = "One or more currency type names could not be found (method)";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        else if (0 == isSuccessful) {
            message = "Unable to update: One or more currency types not found (database)";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        GeneralResponse<String> generalResponse = new GeneralResponse<>(isSuccessful >= 1, message);
        return new ResponseEntity<>(generalResponse, httpStatus);
    }
}

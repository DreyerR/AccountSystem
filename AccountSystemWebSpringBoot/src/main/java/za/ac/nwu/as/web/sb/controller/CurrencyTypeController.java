package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchCurrencyTypeFlow;

import java.util.List;

@RestController
@RequestMapping("currency-type")
public class CurrencyTypeController {

    private final FetchCurrencyTypeFlow fetchCurrencyTypeFlow;

    @Autowired
    public CurrencyTypeController(FetchCurrencyTypeFlow fetchCurrencyTypeFlow) {
        this.fetchCurrencyTypeFlow = fetchCurrencyTypeFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Fetches all currency types", notes = "Returns a list of all the currency types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Currency Types Returned Successfully", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<CurrencyTypeDto>>> fetchAllCurrencyTypes() {
        List<CurrencyTypeDto> currencyTypes = fetchCurrencyTypeFlow.fetchAllCurrencyTypes();
        GeneralResponse<List<CurrencyTypeDto>> response = new GeneralResponse<>(true, currencyTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Saves a new currency type to the database", notes = "Returns the newly created object")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Saved Currency Type", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<CurrencyTypeDto>> saveCurrencyType(@RequestBody CurrencyTypeDto currencyTypeDto) {
        CurrencyTypeDto savedObject = fetchCurrencyTypeFlow.saveCurrencyType(currencyTypeDto);
        GeneralResponse<CurrencyTypeDto> response = new GeneralResponse<>(true, savedObject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

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
import za.ac.nwu.as.logic.flow.CreateCurrencyTypeFlow;
import za.ac.nwu.as.logic.flow.FetchCurrencyTypeFlow;
import za.ac.nwu.as.logic.flow.ModifyCurrencyTypeFlow;

import java.util.List;

@RestController
@RequestMapping("currency-type")
public class CurrencyTypeController {

    private final FetchCurrencyTypeFlow fetchCurrencyTypeFlow;
    private final CreateCurrencyTypeFlow createCurrencyTypeFlow;
    private final ModifyCurrencyTypeFlow modifyCurrencyTypeFlow;

    @Autowired
    public CurrencyTypeController(FetchCurrencyTypeFlow fetchCurrencyTypeFlow, CreateCurrencyTypeFlow createCurrencyTypeFlow,
                                  ModifyCurrencyTypeFlow modifyCurrencyTypeFlow) {
        this.fetchCurrencyTypeFlow = fetchCurrencyTypeFlow;
        this.createCurrencyTypeFlow = createCurrencyTypeFlow;
        this.modifyCurrencyTypeFlow = modifyCurrencyTypeFlow;
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

    @PostMapping("")
    @ApiOperation(value = "Saves a new currency type to the database", notes = "Returns the newly created object")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Saved Currency Type", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<CurrencyTypeDto>> saveCurrencyType(@RequestBody CurrencyTypeDto currencyTypeDto) {
        CurrencyTypeDto savedObject = createCurrencyTypeFlow.saveCurrencyType(currencyTypeDto);
        GeneralResponse<CurrencyTypeDto> response = new GeneralResponse<>(true, savedObject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{ctId}")
    public ResponseEntity<GeneralResponse<String>> deleteCurrencyTypeById(@PathVariable Integer ctId) {
        modifyCurrencyTypeFlow.deleteCurrencyTypeById(ctId);
        String message = String.format("Successfully deleted currency type with ID %d", ctId);
        return new ResponseEntity<>(new GeneralResponse<>(true, message), HttpStatus.OK);
    }
}

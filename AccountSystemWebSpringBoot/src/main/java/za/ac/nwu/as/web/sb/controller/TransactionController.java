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
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchTransactionFlow;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final FetchTransactionFlow fetchTransactionFlow;

    @Autowired
    public TransactionController(FetchTransactionFlow fetchTransactionFlow) {
        this.fetchTransactionFlow = fetchTransactionFlow;
    }

    @ApiOperation(value = "Fetches a transaction by ID", notes = "Returns a transaction specified by an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transaction Returned Successfully", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Transaction Not Found", response = GeneralResponse.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<TransactionDto>> findTransactionById(@PathVariable Integer id) {
        TransactionDto transactionDto = fetchTransactionFlow.findTransactionById(id);
        GeneralResponse<TransactionDto> response = new GeneralResponse<>(true, transactionDto);
        if (null != transactionDto) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

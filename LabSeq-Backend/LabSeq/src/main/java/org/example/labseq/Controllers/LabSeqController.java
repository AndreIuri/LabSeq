package org.example.labseq.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.labseq.Services.LabSeqService;
import org.example.labseq.Services.LabSeqServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;

@RestController
@RequestMapping("/labseq")
@RequiredArgsConstructor
public class LabSeqController {

    private final LabSeqServiceImpl labSeqService;

    @Operation(summary = "Returns a value from the LabSeq given the position(n)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtained LabSeq value"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request")
    }
    )
    @GetMapping("/{n}")
    public ResponseEntity<String> getValue(@PathVariable Integer n) {
        BigInteger value = labSeqService.getValue(n);
        String strValue = value.toString();

        return new ResponseEntity<>(strValue, HttpStatus.OK);
    }

    @Operation(summary = "Returns the obtained LabSeq sequence saved in cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtained LabSeq sequence value saved in cache"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request")
    }
    )
    @GetMapping("/all")
    public ResponseEntity<String> getAllLabSeqValues() {
        Collection<BigInteger> labSeqValues = labSeqService.getAllLabSeqValues();
        String strValue = labSeqValues.toString();

        return new ResponseEntity<>(strValue, HttpStatus.OK);
    }
}

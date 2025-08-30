package com.ale94.digital_banking_api.api.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale94.digital_banking_api.api.models.responses.TransactionResponse;
import com.ale94.digital_banking_api.infraestructure.abstract_services.TransactionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(path = "/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(this.transactionService.getAllTransactions(accountNumber));
    }

    @GetMapping(path = "download-pdf/{operationNumber}")
    public ResponseEntity<byte[]> downloadPdfReceipt(@PathVariable String operationNumber) {
        byte[] pdfBytes = this.transactionService.generatePDF(operationNumber);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt_" + operationNumber + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}

package com.ale94.digital_banking_api.api.controllers;

import com.ale94.digital_banking_api.api.models.requests.TransferRequest;
import com.ale94.digital_banking_api.infraestructure.abstract_services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Map<String, BigDecimal>> getBalance(@PathVariable String accountNumber) {
        var response = new HashMap<String, BigDecimal>();
        var account = this.accountService.balance(accountNumber);
        response.put("balance", account.getBalance());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        this.accountService.transfer(request);
        return ResponseEntity.ok("Transferencia Exitosa!");
    }

    @PatchMapping("/deposit/{accountNumber}")
    public ResponseEntity<String> transfer(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        this.accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok("Deposito Exitoso!");
    }

}

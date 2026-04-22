package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
@CrossOrigin(origins = "*")
public class AdminPaymentController {

    @GetMapping("/history")
    public ResponseEntity<List<Payment>> getTransactionHistory() {
        // Simulación de lista de pagos de la DB
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/revenue/monthly")
    public ResponseEntity<?> getMonthlyRevenue() {
        // Lógica para sumar montos del mes actual
        return ResponseEntity.ok(540.50); // Ejemplo de ingreso total
    }
}
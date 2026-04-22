package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.Payment;
import com.tecsup.back_adminzonet.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
@CrossOrigin(origins = "*") // Para conexión con React
public class AdminPaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * 🟢 Jala el historial real de transacciones de la tabla 'payments' en Railway.
     * GET http://localhost:8081/api/admin/payments/history
     */
    @GetMapping("/history")
    public ResponseEntity<List<Payment>> getTransactionHistory() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }
}
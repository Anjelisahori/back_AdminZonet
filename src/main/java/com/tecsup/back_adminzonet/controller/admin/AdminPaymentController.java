package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.Payment;
import com.tecsup.back_adminzonet.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
@CrossOrigin(origins = "*") //
public class AdminPaymentController {

    @Autowired
    private PaymentRepository paymentRepository; //

    /**
     * 🟢 Endpoint corregido para coincidir con el Frontend.
     * Jala los datos reales de la tabla 'payments' en Railway.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        // Busca todos los registros de la tabla 'payments'
        List<Payment> payments = paymentRepository.findAll();

        // Retorna la lista de pagos vinculados a los campos reales de tu DB
        return ResponseEntity.ok(payments);
    }
}
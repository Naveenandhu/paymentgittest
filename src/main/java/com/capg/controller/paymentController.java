package com.capg.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capg.entity.Payment;
import com.capg.exceptionHandler.IdNotFoundException;
import com.capg.service.PaymentService;
import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class paymentController {
	@Autowired
	private PaymentService paymentService;
	@PostMapping("/add")
 public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
		  System.out.println(payment.getAmount());
			return new ResponseEntity<Payment>(paymentService.addPayment(payment),HttpStatus.CREATED);
		}
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<Payment>> getAllPayments() {
		return new ResponseEntity<List<Payment>>(paymentService.getAllPayments(), HttpStatus.OK);

	}
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
		Payment payment = paymentService.getById(id);
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		paymentService.deleteById(id);
		return ResponseEntity.ok("Id no: " + id + "is deleted sucessfully...");

	}
	@PutMapping("/{id}")
	public ResponseEntity<Payment> editPaymentById(@PathVariable("id") int id,
			@Valid @RequestBody Payment updatePayment) {
		try {
			Payment payment = paymentService.editPaymentById(id, updatePayment);
			return ResponseEntity.ok(payment);
		} catch (IdNotFoundException ex) {
			ex.getMessage();
			return ResponseEntity.badRequest().build();
		}
	}

}

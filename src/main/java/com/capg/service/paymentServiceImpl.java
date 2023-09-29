package com.capg.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.entity.Payment;
import com.capg.exceptionHandler.IdNotFoundException;
import com.capg.exceptionHandler.UpdateFailedException;
import com.capg.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
@Transactional
public class paymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}
	@Override
	public Payment getById(int id) {
		try {
			return paymentRepository.findById(id).get();
		} catch (Exception ex) {
			throw new IdNotFoundException("Payment with Id " + id + "not found");
		}		
	}

	@Override
	public void deleteById(Integer id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Id is Not Found" + id));
		paymentRepository.delete(payment);
	}

	@Override
	public Payment editPaymentById(int id, @Valid Payment updatePayment) {//@valid
		// TODO Auto-generated method stub
		if (paymentRepository.existsById(id)) {
			updatePayment.setPaymentId(id);
			return paymentRepository.save(updatePayment);
		} else {
			throw new UpdateFailedException("Failed to update Payment for id " + id);

		}
	}
	@Override
	public Payment addPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
}

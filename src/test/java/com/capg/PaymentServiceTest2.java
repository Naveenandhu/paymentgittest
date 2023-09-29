package com.capg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.capg.entity.Payment;
import com.capg.repository.PaymentRepository;
import com.capg.service.PaymentService;

@SpringBootTest
public class PaymentServiceTest2 {

	@Autowired
	private PaymentService paymentService;

	@MockBean
	private PaymentRepository paymentRepository;

	@Test
	public void testGetAllPayments() {
		// Arrange
		List<Payment> expectedPayments = new ArrayList<>();
		// Add some sample Payment objects to the list as expected results.

		when(paymentRepository.findAll()).thenReturn(expectedPayments);

		// Act
		List<Payment> actualPayments = paymentService.getAllPayments();

		// Assert
		assertThat(actualPayments).isEqualTo(expectedPayments);
		verify(paymentRepository, times(1)).findAll();
	}
}

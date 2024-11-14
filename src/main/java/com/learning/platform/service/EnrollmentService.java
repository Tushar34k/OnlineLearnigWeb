//package com.learning.platform.service;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.learning.platform.dto.EnrollmentDTO;
//import com.learning.platform.model.Course;
//import com.learning.platform.model.Enrollment;
//import com.learning.platform.model.Payment;
//import com.learning.platform.repository.CourseRepository;
//import com.learning.platform.repository.EnrollmentRepository;
//import com.learning.platform.repository.PaymentRepository;
//import com.learning.platform.repository.UserRepository;
//
//import lombok.Data;
//
//@Service
//@Data
//public class EnrollmentService {
//
//	@Autowired
//	private EnrollmentRepository enrollmentRepository;
//
//	@Autowired
//	private PaymentRepository paymentRepository;
//
//	@Autowired
//	private CourseRepository courseRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	// Method to enroll in courses with full or installment payment option
//	public void enrollCourses(Long userId, Set<Long> courseIds, String paymentOption, EnrollmentDTO enrollmentDTO) {
//		Enrollment enrollment = new Enrollment();
//		enrollment.setUser(userRepository.findById(userId)
//				.orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId)));
//		enrollment.setName(enrollmentDTO.getName());
//
//		// Fetch the selected courses and calculate total price
//		Set<Course> selectedCourses = new HashSet<>(courseRepository.findAllById(courseIds));
//		if (selectedCourses.isEmpty()) {
//			throw new IllegalArgumentException("No courses found for the given course IDs.");
//		}
//		double totalPrice = selectedCourses.stream().mapToDouble(Course::getPrice).sum();
//		enrollment.setCourses(selectedCourses); // Link the courses to this enrollment
//		enrollment.setTotalPrice(totalPrice); // Set the total price in the enrollment
//
//		// Handle payment option (FULL or INSTALLMENT)
//		if ("FULL".equalsIgnoreCase(paymentOption)) {
//			enrollment.setAmountPaid(totalPrice);
//			enrollment.setPaymentComplete(true); // Mark payment as complete
//		} else if ("INSTALLMENT".equalsIgnoreCase(paymentOption)) {
//			enrollment.setAmountPaid(totalPrice / 2); // Pay half now
//			enrollment.setPaymentComplete(false); // Full payment not completed yet
//		} else {
//			throw new IllegalArgumentException("Invalid payment option. Must be 'FULL' or 'INSTALLMENT'.");
//		}
//
//		// Save the enrollment with courses
//		enrollmentRepository.save(enrollment);
//
//		// Record the initial payment
//		Payment payment = new Payment();
//		payment.setAmount(enrollment.getAmountPaid());
//		payment.setPaymentDate(new Date());
//		payment.setPaymentType(paymentOption.toUpperCase());
//		payment.setEnrollment(enrollment);
//		paymentRepository.save(payment);
//	}
//
//	// Method to complete the remaining installment payment
//	public void completeInstallmentPayment(Long enrollmentId) {
//		Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
//				.orElseThrow(() -> new IllegalArgumentException("Enrollment not found with ID: " + enrollmentId));
//
//		// Check if payment is already completed
//		if (enrollment.isPaymentComplete()) {
//			throw new IllegalArgumentException("The payment for this enrollment is already completed.");
//		}
//
//		// Calculate the remaining amount
//		double remainingAmount = enrollment.getTotalPrice() - enrollment.getAmountPaid();
//		if (remainingAmount <= 0) {
//			throw new IllegalArgumentException("No remaining amount to be paid.");
//		}
//
//		// Update the enrollment with the remaining payment
//		enrollment.setAmountPaid(enrollment.getTotalPrice());
//		enrollment.setPaymentComplete(true);
//		enrollmentRepository.save(enrollment);
//
//		// Record the second installment payment
//		Payment secondPayment = new Payment();
//		secondPayment.setAmount(remainingAmount);
//		secondPayment.setPaymentDate(new Date());
//		secondPayment.setPaymentType("INSTALLMENT");
//		secondPayment.setEnrollment(enrollment);
//		paymentRepository.save(secondPayment);
//	}
//}

package model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import model.exceptions.InvalidDateException;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkin;
	private LocalDate checkout;

	public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
		super();
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public long period(LocalDate checkin, LocalDate checkout) {

		long dur = Period.between(checkin, checkout).getDays();

		return dur;
	}

	public String updateReservation(LocalDate checkin, LocalDate checkout) {

		if (checkin.isBefore(LocalDate.now()) || checkout.isBefore(LocalDate.now())) {
			return "Error in reservation: Reservation dates for update must be future dates";
		} 
		else if (checkin.isAfter(checkout)) {
			return "Error in reservation: Check-out date must be after check-in date";
		}

		this.checkin = checkin;
		this.checkout = checkout;

		return null;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + checkin.format(fmt) + ", checkout: " + checkout.format(fmt)
				+ ", " + period(checkin, checkout) + " nights.";
	}

}

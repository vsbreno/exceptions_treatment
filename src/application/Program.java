package application;

import java.time.LocalDate;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.InvalidDateException;

public class Program {

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.print("Room number: ");
			int roomNumber = scan.nextInt();
			scan.nextLine();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			LocalDate checkin = LocalDate.parse(scan.nextLine(), Reservation.fmt);
			System.out.print("Check-out date (dd/MM/yyyy): ");
			LocalDate checkout = LocalDate.parse(scan.nextLine(), Reservation.fmt);

			if (checkin.isAfter(checkout)) {
				throw new InvalidDateException("Error in reservation: Check-out date must be after check-in date");
			} 
			else {
				Reservation reservation = new Reservation(roomNumber, checkin, checkout);
				System.out.println("Reservation: " + reservation);

				System.out.println();
				System.out.println("Enter data to update the reservation:");
				System.out.print("Check-in date (dd/MM/yyyy): ");
				LocalDate checkinUpdated = LocalDate.parse(scan.nextLine(), Reservation.fmt);
				System.out.print("Check-out date (dd/MM/yyyy): ");
				LocalDate checkoutUpdated = LocalDate.parse(scan.nextLine(), Reservation.fmt);

				if (checkinUpdated.isBefore(LocalDate.now()) || checkoutUpdated.isBefore(LocalDate.now())) {
					throw new InvalidDateException(
							"Error in reservation: Reservation dates for update must be future dates");
				} 
				else if (checkin.isAfter(checkout)) {
					throw new InvalidDateException("Error in reservation: Check-out date must be after check-in date");
				} 
				else {
					reservation.updateReservation(checkinUpdated, checkoutUpdated);
					System.out.println("Reservation: " + reservation);
				}
			}

		} catch (InvalidDateException e) {
			System.out.println(e.getMessage());
		}
	}

}

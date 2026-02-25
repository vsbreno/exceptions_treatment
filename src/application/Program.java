package application;

import java.time.LocalDate;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

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

			Reservation reservation = new Reservation(roomNumber, checkin, checkout);
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			LocalDate checkinUpdated = LocalDate.parse(scan.nextLine(), Reservation.fmt);
			System.out.print("Check-out date (dd/MM/yyyy): ");
			LocalDate checkoutUpdated = LocalDate.parse(scan.nextLine(), Reservation.fmt);
			
			reservation.updateReservation(checkinUpdated, checkoutUpdated);
		}
		catch(DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		catch(RuntimeException e) {
			System.out.println("Unexpected error.");
		}
	}

}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Leitura dos dados iniciais da reserva
        System.out.print("Número do quarto: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        LocalDate checkIn = readDate(scanner, "Data de entrada (dd/MM/yyyy): ", formatter);
        LocalDate checkOut = readDate(scanner, "Data de saída (dd/MM/yyyy): ", formatter);
        
        // Validar datas iniciais
        while (!checkOut.isAfter(checkIn)) {
            System.out.println("Erro: Data de saída deve ser posterior à data de entrada.");
            checkOut = readDate(scanner, "Data de saída (dd/MM/yyyy): ", formatter);
        }

        Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
        System.out.println("\nReserva inicial:");
        System.out.println(reservation);

        // Atualização da reserva
        System.out.println("\nDigite as novas datas para atualizar a reserva:");
        LocalDate newCheckIn = readDate(scanner, "Nova data de entrada (dd/MM/yyyy): ", formatter);
        LocalDate newCheckOut = readDate(scanner, "Nova data de saída (dd/MM/yyyy): ", formatter);

        try {
            reservation.updateDates(newCheckIn, newCheckOut);
            System.out.println("\nReserva atualizada com sucesso!");
            System.out.println(reservation);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na atualização: " + e.getMessage());
        }

        scanner.close();
    }

    private static LocalDate readDate(Scanner scanner, String message, DateTimeFormatter formatter) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}

class Reservation {
    private int roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int duration() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public void updateDates(LocalDate newCheckIn, LocalDate newCheckOut) {
        LocalDate today = LocalDate.now();

        if (newCheckIn.isBefore(today) || newCheckOut.isBefore(today)) {
            throw new IllegalArgumentException("Datas devem ser futuras.");
        }

        if (!newCheckOut.isAfter(newCheckIn)) {
            throw new IllegalArgumentException("Data de saída deve ser posterior à data de entrada.");
        }

        this.checkIn = newCheckIn;
        this.checkOut = newCheckOut;
    }

    @Override
    public String toString() {
        return "Quarto " + roomNumber
            + "\nEntrada: " + checkIn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            + "\nSaída: " + checkOut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            + "\nDuração: " + duration() + " noites";
    }
}
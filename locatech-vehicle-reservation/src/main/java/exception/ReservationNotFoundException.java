package exception;

public class ReservationNotFoundException extends RuntimeException {
  
  private static final String MESSAGE = "Reservation not found [id=%d]";
  
  public ReservationNotFoundException(Long id) {
    super(MESSAGE.formatted(id));
  }
}

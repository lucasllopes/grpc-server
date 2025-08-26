package entity;

import jakarta.persistence.*;

@Entity
public class Reservation {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  private Long vehicleId;
  private Long userId;
  private String startDate;
  private String endDate;
  
  @Enumerated(EnumType.STRING)
  private ReservationStatus status;

  public Reservation() {
  }

  public Reservation(Long vehicleId, Long userId, String startDate, String endDate, ReservationStatus status) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }
}



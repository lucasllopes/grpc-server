package service.handler;

import dev.lucaslopes.locatech.reservation.*;
import entity.Reservation;
import entity.ReservationStatus;
import exception.ReservationNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import mapper.EntityMessageMapper;
import repository.ReservationRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class ReservationRequestHandler {
  
  private final ReservationRepository reservationRepository;
  
  public ReservationRequestHandler(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }
  
  @Transactional
  public CreateReservationResponse createReservation(CreateReservationRequest request) {
    var reservation = EntityMessageMapper.toEntity(request);
    reservationRepository.persistAndFlush(reservation);
    return CreateReservationResponse.newBuilder()
      .setMessage("Reservation created")
      .setReservationId(reservation.getId())
      .build();
  }

  @Transactional
  public GetReservationResponse getReservation(GetReservationRequest request) {
    Reservation reservation = reservationRepository
      .findByIdOptional(request.getReservationId())
      .orElseThrow(() -> new ReservationNotFoundException(request.getReservationId()));

    return EntityMessageMapper.toGetReservationResponseMessage(reservation);
  }

  @Transactional
  public GetReservationByVehicleIdResponse getReservationByVehicleId(
    GetReservationByVehicleIdRequest request) {

    Reservation reservation = reservationRepository
      .find("vehicleId", request.getVehicleId())
      .firstResultOptional()
      .orElseThrow(() -> new ReservationNotFoundException(request.getVehicleId()));

    return EntityMessageMapper.toGetReservationByVehicleIdResponseMessage(reservation);
  }

  @Transactional
  public CompleteReservationResponse completeReservation(CompleteReservationRequest request) {
    Reservation reservation = reservationRepository
      .findByIdOptional(request.getReservationId())
      .orElseThrow(() -> new ReservationNotFoundException(request.getReservationId()));

    reservation.setStatus(ReservationStatus.CONFIRMED);
    reservationRepository.persist(reservation);

    LocalDate startDate = LocalDate.parse(reservation.getStartDate());
    LocalDate endDate = LocalDate.parse(reservation.getEndDate());
    long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

    return EntityMessageMapper.toCompleteReservationResponseMessage(totalDays);
  }




}

package mapper;

import entity.Reservation;
import entity.ReservationStatus;
import dev.lucaslopes.locatech.reservation.CreateReservationRequest;
import dev.lucaslopes.locatech.reservation.GetReservationResponse;
import dev.lucaslopes.locatech.reservation.GetReservationByVehicleIdResponse;
import dev.lucaslopes.locatech.reservation.CompleteReservationResponse;
import dev.lucaslopes.locatech.reservation.ReservationProto;
import dev.lucaslopes.locatech.reservation.ReservationStatusProto;

public class EntityMessageMapper {
  public static Reservation toEntity(CreateReservationRequest request) {
    return new Reservation(
      request.getVehicleId(),
      request.getUserId(),
      request.getStartDate(),
      request.getEndDate(),
      ReservationStatus.PENDING
    );
  }

  public static GetReservationResponse toGetReservationResponseMessage(Reservation reservation) {
    return GetReservationResponse.newBuilder().setReservation(
      ReservationProto.newBuilder()
        .setReservationId(reservation.getId())
        .setVehicleId(reservation.getVehicleId())
        .setUserId(reservation.getUserId())
        .setStartDate(reservation.getStartDate())
        .setEndDate(reservation.getEndDate())
        .setStatus(ReservationStatusProto.valueOf(reservation.getStatus().name()))
        .build()
    ).build();
  }

  public static CompleteReservationResponse toCompleteReservationResponseMessage(Long totalDays) {
    return CompleteReservationResponse
      .newBuilder()
      .setTotalDays(totalDays)
      .setMessage("Reservation completed successfully")
      .build();
  }

  public static GetReservationByVehicleIdResponse toGetReservationByVehicleIdResponseMessage(Reservation reservation) {
    return GetReservationByVehicleIdResponse.newBuilder()
      .setReservation(
        ReservationProto.newBuilder()
          .setReservationId(reservation.getId())
          .setVehicleId(reservation.getVehicleId())
          .setUserId(reservation.getUserId())
          .setStartDate(reservation.getStartDate())
          .setEndDate(reservation.getEndDate())
          .setStatus(ReservationStatusProto.valueOf(reservation.getStatus().name()))
          .build()
      )
      .build();
  }  
}


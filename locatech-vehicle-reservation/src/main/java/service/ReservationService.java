package service;

import dev.lucaslopes.locatech.reservation.*;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import service.handler.ReservationRequestHandler;

@GrpcService
@Blocking
public class ReservationService extends ReservationServiceGrpc.ReservationServiceImplBase {
  
  private final ReservationRequestHandler reservationRequestHandler;
  
  public ReservationService(ReservationRequestHandler reservationRequestHandler) {
    this.reservationRequestHandler = reservationRequestHandler;
  }

  @Override
  public void createReservation(CreateReservationRequest request, StreamObserver<CreateReservationResponse> responseObserver) {
    var response = reservationRequestHandler.createReservation(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getReservation(GetReservationRequest request, StreamObserver<GetReservationResponse> responseObserver) {
    var response = reservationRequestHandler.getReservation(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void completeReservation(CompleteReservationRequest request, StreamObserver<CompleteReservationResponse> responseObserver) {
    var response = reservationRequestHandler.completeReservation(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getReservationByVehicleId(GetReservationByVehicleIdRequest request, StreamObserver<GetReservationByVehicleIdResponse> responseObserver) {
    var response = reservationRequestHandler.getReservationByVehicleId(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}

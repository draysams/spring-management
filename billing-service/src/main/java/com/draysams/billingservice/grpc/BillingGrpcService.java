package com.draysams.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
	
	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	
	
	@Override
	public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
		log.info("Create billing account request received {}", billingRequest.toString());
		
		// Business logic here
		
		BillingResponse response = BillingResponse.newBuilder().setAccountId("1234").setStatus("ACTIVE").build();
		
		responseObserver.onNext(response);
		
		responseObserver.onCompleted();
	}
}
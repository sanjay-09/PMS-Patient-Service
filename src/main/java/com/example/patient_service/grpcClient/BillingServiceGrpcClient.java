package com.example.patient_service.grpcClient;


import com.example.grpc.BillingRequest;
import com.example.grpc.BillingResponse;
import com.example.grpc.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingServiceGrpcClient {

  private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress,
                                    @Value("${billing.service.address.port:3001}") int serverPort
                                    ){


        log.info("Connection to the grpc server is following serverAddess:{} and port:{}",serverAddress,serverPort);

        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress(serverAddress,serverPort).usePlaintext().build();
        this.blockingStub=BillingServiceGrpc.newBlockingStub(managedChannel);

    }

    public BillingResponse createBillingAccount(String patientId,String name,String email){

        BillingRequest billingRequest=BillingRequest.newBuilder().setName(name).setEmail(email).setPatientId(patientId).build();

        BillingResponse billingResponse=this.blockingStub.createPatient(billingRequest);
        log.info("response received from the grpc server,accountId :{}",billingResponse.getAccountId());

        return billingResponse;

    }


}

package com.stackroute.paymentservice.service;

import com.stackroute.kafka.domain.CardDetails;


public interface CardService {

   CardDetails saveCardDetails(CardDetails cardDetails);

}
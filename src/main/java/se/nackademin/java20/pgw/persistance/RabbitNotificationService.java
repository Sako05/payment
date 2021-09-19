package se.nackademin.java20.pgw.persistance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import se.nackademin.java20.pgw.domain.Payment;
import se.nackademin.java20.pgw.domain.PaymentNotificationService;

import java.util.HashMap;
import java.util.Map;

public class RabbitNotificationService implements PaymentNotificationService {
    private final static Logger LOG = LoggerFactory.getLogger(RabbitNotificationService.class);

    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    public RabbitNotificationService(RabbitTemplate template, ObjectMapper objectMapper) {

        this.template = template;
        this.objectMapper = objectMapper;
    }

    @Override
    public void notifyPaid(Payment payment) {
        try {
            String object = objectMapper.writeValueAsString(new PaymentMessageDto(payment.getReference(), "" + payment.getId(), payment.getStatus()));
            LOG.info("Sending {}", object);
            template.convertAndSend("payments-exchange", payment.getReference(), object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://hakimlivsdb.herokuapp.com/payment/add";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("reference", payment.getReference());
        map.add("status", payment.getStatus());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );

        LOG.info(map.toString());
        LOG.info(String.valueOf(response.getStatusCode()));
        LOG.info(response.getBody());


    }
}

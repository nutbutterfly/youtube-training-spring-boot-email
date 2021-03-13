package email.listener;

import com.iamnbty.training.common.EmailRequest;
import email.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailListener {

    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "activation-email")
    public void listenForActivationEmail(EmailRequest request) {
        log.info("Kafka received: " + request.getTo());
        log.info(request.getContent());

        emailService.send(request.getTo(), request.getSubject(), request.getContent());
    }

}

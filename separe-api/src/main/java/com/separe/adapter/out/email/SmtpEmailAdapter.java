package com.separe.adapter.out.email;

import com.separe.domain.port.out.EmailServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SmtpEmailAdapter implements EmailServicePort {

    private static final Logger log = LoggerFactory.getLogger(SmtpEmailAdapter.class);
    private static final int MAX_TENTATIVAS = 3;
    private static final long INTERVALO_RETRY_MS = 5000;

    private final JavaMailSender mailSender;

    public SmtpEmailAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void enviarEmail(String destinatario, String assunto, String corpo) {
        for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
            try {
                SimpleMailMessage mensagem = new SimpleMailMessage();
                mensagem.setTo(destinatario);
                mensagem.setSubject(assunto);
                mensagem.setText(corpo);
                mensagem.setFrom("noreply@separe.com");

                mailSender.send(mensagem);
                log.info("E-mail enviado com sucesso para {} (tentativa {})", destinatario, tentativa);
                return;

            } catch (Exception e) {
                log.warn("Falha ao enviar e-mail para {} (tentativa {}/{}): {}",
                        destinatario, tentativa, MAX_TENTATIVAS, e.getMessage());

                if (tentativa < MAX_TENTATIVAS) {
                    try {
                        Thread.sleep(INTERVALO_RETRY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        log.error("Retry de e-mail interrompido para {}", destinatario);
                        return;
                    }
                } else {
                    log.error("Todas as tentativas de envio de e-mail falharam para {}", destinatario);
                }
            }
        }
    }
}

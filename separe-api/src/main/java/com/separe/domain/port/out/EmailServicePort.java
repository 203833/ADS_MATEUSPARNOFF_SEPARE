package com.separe.domain.port.out;

public interface EmailServicePort {

    void enviarEmail(String destinatario, String assunto, String corpo);
}

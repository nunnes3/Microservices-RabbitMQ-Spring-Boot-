package com.nunnes.ms.user.producers;

import com.nunnes.ms.user.dtos.EmailDto;
import com.nunnes.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso !!");
        emailDto.setText(userModel.getName() + ",seja bem vindo(a)! \n Agradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma");
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}

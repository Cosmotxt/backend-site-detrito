package detrito.espacial.website.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import detrito.espacial.website.DTO.ApiResponseDTO;
import detrito.espacial.website.DTO.UserDTO;
import detrito.espacial.website.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/contato")
@CrossOrigin(origins = {"http://localhost:3000", "https://detritoespacial.com"})
public class EmailController {
    @Autowired
    private EmailService emailService;
    
    @PostMapping 
    public ResponseEntity<Object> sendEmail(@RequestBody @Valid UserDTO user) throws MessagingException, UnsupportedEncodingException {   
        try {
            emailService.sendEmail(user);
            System.out.println("Email enviado com sucesso!");
            return ResponseEntity.ok(new ApiResponseDTO(
                true,
                user.getMessage()
            ));
        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponseDTO(
                                    false,
                                    "Falha ao enviar mensagem: " + e.getMessage()
                                ));
        } 
    }
}

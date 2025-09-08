package detrito.espacial.website.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private String name;

    @NotBlank(message = "Precisamos saber teu e-mail!")
    @Email(message = "Email Inválido")
    private String email;

    @NotBlank(message = "Fala alguma coisa po :(")
    private String message;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

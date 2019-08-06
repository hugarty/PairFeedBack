package pairFeedBack.dataTransferer.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class SignUpForm {

    @NotNull @NotEmpty @Length(min = 3, max = 100)
    String name;
    @NotNull @Email @Length(min = 3, max = 100)
    String email;
    @NotNull @NotEmpty @Length(min = 6, max = 100)
    String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
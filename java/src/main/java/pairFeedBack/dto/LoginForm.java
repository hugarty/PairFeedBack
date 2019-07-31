package pairFeedBack.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    @NotNull @NotEmpty @Length(min = 3, max = 100)
    String email;
    
    @NotNull @NotEmpty @Length(min = 6, max = 100)
    String senha;
        
    public UsernamePasswordAuthenticationToken convert(){
       return new UsernamePasswordAuthenticationToken(email, senha);
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
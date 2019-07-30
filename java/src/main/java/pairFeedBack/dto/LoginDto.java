package pairFeedBack.dto;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

    String login;
    String senha;
        
    public UsernamePasswordAuthenticationToken convert(){
       return new UsernamePasswordAuthenticationToken(login, senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
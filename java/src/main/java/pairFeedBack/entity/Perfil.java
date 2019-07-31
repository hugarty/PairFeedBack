package pairFeedBack.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import pairFeedBack.utils.PerfilEnum;

@Entity
public class Perfil implements GrantedAuthority {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    PerfilEnum perfilEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return perfilEnum.name();
    }

    public String getPerfilEnum() {
        return perfilEnum.name();
    }

    public void setPerfilEnum(PerfilEnum perfilEnum) {
        this.perfilEnum = perfilEnum;
    }
}
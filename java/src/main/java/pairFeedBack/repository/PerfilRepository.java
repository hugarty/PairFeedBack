package pairFeedBack.repository;

import org.springframework.data.repository.CrudRepository;

import pairFeedBack.entity.Perfil;
import pairFeedBack.utils.PerfilEnum;

public interface PerfilRepository extends CrudRepository<Perfil, Long>{
    Perfil findByPerfilEnum(PerfilEnum perfilEnum);
}
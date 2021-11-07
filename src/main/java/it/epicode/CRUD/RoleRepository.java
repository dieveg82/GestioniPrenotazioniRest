package it.epicode.CRUD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Postazione;
import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.model.TipoPostazione;

public interface RoleRepository extends JpaRepository<Role, Long> {



}

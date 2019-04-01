package br.com.alan.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.alan.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		
		 Usuario usuario = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
		 	.setParameter("email", email)
		 	.getSingleResult();
		 
		 if (usuario == null) {
			 throw new UsernameNotFoundException("Usuario não encontrado");
		 }
		 
		 return usuario;
		
	}

}

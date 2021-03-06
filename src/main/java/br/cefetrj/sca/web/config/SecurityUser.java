/**
 * 
 */
package br.cefetrj.sca.web.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import br.cefetrj.sca.dominio.Role;
import br.cefetrj.sca.dominio.usuarios.PerfilUsuario;
import br.cefetrj.sca.dominio.usuarios.Usuario;

public class SecurityUser extends Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	public SecurityUser(Usuario user) {
		if (user != null) {
			this.setId(user.getId());
			this.setNome(user.getNome());
			this.setLogin(user.getLogin());
			this.setPassword(user.getPassword());
			this.setDob(user.getDob());
			this.setUserProfiles(user.getUserProfiles());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Set<PerfilUsuario> userRoles = this.getUserProfiles();

		if (userRoles != null) {
			for (PerfilUsuario role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						role.getType());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

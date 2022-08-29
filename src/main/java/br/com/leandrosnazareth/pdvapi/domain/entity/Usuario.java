package br.com.leandrosnazareth.pdvapi.domain.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
//implementar a interface UserDetails com os metodos de login do spring security
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Size(min = 3, max = 20, message = "Campo login deve ter entre 3 e 20 caracteres")
    @NotNull(message = "Campo login é obrigatório")
    @NotEmpty(message = "Campo login é obrigatório")
	@Column(unique = true)
	private String username;
    @NotNull(message = "Campo senha é obrigatório")
    @NotEmpty(message = "Campo senha é obrigatório")
	private String password;
	@Size(min = 3, max = 50, message = "Campo nome deve ter entre 3 e 50 caracteres")
    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome é obrigatório")
	private String name;
	private String token = "";

	@OneToMany(fetch = FetchType.EAGER)
	private List<Role> roles; //Os papeis ou acessos

	//permissões do usuario role_admin role_gerente
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
package br.com.leandrosnazareth.pdvapi.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

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
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 20, message = "Campo nome deve ter entre 3 e 20 caracteres")
    @NotNull(message = "Campo nome é obrigatório")
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nameRole; // Papel, exemplo ROLE_SECRETARIO OU ROLE_GERENTE

    // implementação obriatório da interface GrantedAuthority
    @Override
    public String getAuthority() {
        return this.nameRole;
    }
}

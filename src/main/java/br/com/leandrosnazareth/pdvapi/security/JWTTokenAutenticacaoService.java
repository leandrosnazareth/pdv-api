package br.com.leandrosnazareth.pdvapi.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.domain.repository.UsuerRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
// responsavel por gerar e validar o token
public class JWTTokenAutenticacaoService {

	// Tempo de validade do Token 2 dias, após esse tempo, será necessário logar
	// novamente
	private static final long EXPIRATION_TIME = 172800000;

	// Senha unica para compor a autenticacao e ajudar na segurança
	private static final String SECRET = "$enha$ecretaDaAPI";

	// Prefixo padrão de Token
	private static final String TOKEN_PREFIX = "Bearer";

	// identificação do cabeçalho da resposta
	private static final String HEADER_STRING = "Authorization";

	// Gerar token de autenticado e adiconando ao cabeçalho e resposta Http
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		// Montagem do Token
		String JWT = Jwts.builder() // Chama o gerador de Token
				.setSubject(username) // Adicona o usuario
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Tempo de expiração
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); // Compactação e algoritmos criptografia

		// Junta token com o prefixo
		String token = TOKEN_PREFIX + " " + JWT; // Bearer + o token gerado

		// Adiciona no cabeçalho http
		response.addHeader(HEADER_STRING, token); // Authorization: Bearer + o token gerado

		// atualizar token no banco de dados
		ApplicationContextLoad.getApplicationContext()
				.getBean(UsuerRepository.class).atualizaTokenUser(JWT, username);

		// Escreve o token como responsta no corpo http
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	// Retorna o usuário validado com token ou caso não sejá valido retorna null
	public Authentication getAuhentication(HttpServletRequest request, HttpServletResponse response) {
		// Pega o token enviado no cabeçalho http
		String token = request.getHeader(HEADER_STRING);
		try {
			if (token != null) {
				// pegar o token e retirar os espaços e o bearer
				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
				// Faz a validação do token do usuário na requisição
				String user = Jwts.parser().setSigningKey(SECRET) // Bearer + o token gerado
						.parseClaimsJws(tokenLimpo) // somente o token
						.getBody().getSubject(); // get usuário
				if (user != null) {
					// retornar o usuário logado
					Usuario usuario = ApplicationContextLoad.getApplicationContext()
							.getBean(UsuerRepository.class).findUserByUsername(user);
					if (usuario != null) {
						// comparar token do banco com o fornecido pelo usuário
						if (tokenLimpo.equalsIgnoreCase(usuario.getToken())) {
							// retornar usuário com as permissões
							return new UsernamePasswordAuthenticationToken(
									usuario.getUsername(),
									usuario.getPassword(),
									usuario.getAuthorities());
						}
					}
				}
			}
		} catch (ExpiredJwtException e) {
			try {
				// add ao response a msg de erro do token expirado
				response.getOutputStream().print("Seu token está expirado, faça novamente o login do usuário");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return null; // Não autorizado
	}

}
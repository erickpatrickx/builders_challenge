package br.com.devchallenge.cliente.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Validador do token
 * @author erick.oliveira
 *
 */
public class TokenAuthenticationService {
	
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
	     List<GrantedAuthority> grantedAuths =
		                AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("roles"));
			if (claims != null) {
				return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, grantedAuths);
			}
		}
		return null;
	}
	
	
	
	/**Metodo de geração de autenticação 
	 * @author erick.oliveira
	 * @param response
	 * @param username
	 * @param collection
	 * @throws IOException
	 */
	public static  String generateToken(String username,String authorities) throws IOException {
		String JWT = Jwts.builder()
				.setSubject(username)
				.claim("roles", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		if(JWT != null) {
				return TOKEN_PREFIX + " "+JWT;
		}
		return "";
	}
	
	
	
}

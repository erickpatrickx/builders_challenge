package br.com.devchallenge.auth.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	/**Metodo de adição de autenticação
	 * @author erick.oliveira
	 * @param response
	 * @param username
	 * @param collection
	 * @throws IOException
	 */
	public  String addAuthentication(HttpServletResponse response, String username,String authorities ) throws IOException {
		String JWT = Jwts.builder()
				.setSubject(username)
				.claim("roles", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		if(JWT != null) {
				return TOKEN_PREFIX + " "+JWT;
		}
		return "";
	}
	/**Recuperar autenticação partir do token
	 * @author erick.oliveira
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
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
	
}

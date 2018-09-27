package rostyslav.popadynets.config.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rostyslav.popadynets.constant.SecurityConstant;
import rostyslav.popadynets.entity.enums.UserRole;

@Component
public class JWTTokenProvider {

	@Autowired
	UserDetailsService userDetailsService;

	public String createToken(String email, UserRole role) {
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("auth", AuthorityUtils.createAuthorityList(String.valueOf(role)));

		Date now = new Date();
		Date validity = new Date(now.getTime() + SecurityConstant.EXPIRATION_TIME);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, SecurityConstant.TOKEN_SECRET).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserEmail(String token) {
		return Jwts.parser().setSigningKey(SecurityConstant.TOKEN_SECRET).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(SecurityConstant.HEADER_STRING);
		if (bearerToken != null && bearerToken.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) { // перевірка токена чи живий
		try {
			Jwts.parser().setSigningKey(SecurityConstant.TOKEN_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

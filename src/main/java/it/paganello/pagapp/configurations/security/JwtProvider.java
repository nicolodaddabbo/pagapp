package it.paganello.pagapp.configurations.security;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.logging.Logger;


@Component
public class JwtProvider {
	
	private static final Logger log = Logger.getLogger(JwtProvider.class.getName());
	
	public static final String issuer = "demo-api-app";
	public static String secret;
	public static String prefix;
	public static String headerParam;
	
	@Autowired
	public JwtProvider(Environment env) {
		JwtProvider.secret = env.getProperty("security.secret");
		JwtProvider.prefix = env.getProperty("security.prefix");
		JwtProvider.headerParam = env.getProperty("security.param");
		if (JwtProvider.secret == null || JwtProvider.prefix == null || JwtProvider.headerParam == null) {
			throw new BeanInitializationException("Cannot assign security properties. Check application.yml file.");
		}
	}
	
	public static String createJwt(String subject, Map<String, Object> payloadClaims) {
		JWTCreator.Builder builder = JWT.create().withSubject(subject).withIssuer(issuer);
		final LocalDateTime now = LocalDateTime.now();
		builder.withIssuedAt(now.atZone(ZoneId.systemDefault()).toInstant()).withExpiresAt(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
		
		if (payloadClaims.isEmpty()) {
			log.warning("You are building a JWT without header claims");
		}
		for (Map.Entry<String, Object> entry : payloadClaims.entrySet()) {
			builder.withClaim(entry.getKey(), entry.getValue().toString());
		}
		return builder.sign(Algorithm.HMAC256(JwtProvider.secret));
	}
	
	public static DecodedJWT verifyJwt(String jwt) {
		return JWT.require(Algorithm.HMAC256(JwtProvider.secret)).build().verify(jwt);
	}
}
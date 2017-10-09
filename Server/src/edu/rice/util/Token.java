package edu.rice.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;  

public final class Token {
	
	public static final Token Singleton = new Token();
	
	/**
	 * Default secret key
	 */
	private static final String JWT_SECRET = "HACKRICE2017APP#";
	/**
	 * Default issuer
	 */
	private static final String JWT_ISSUER = "RICEAPPTEAM";
	/**
	 * Default ttl is 10 mins.
	 */
	private static final long JWT_TTL = 10*60*1000;
	
	private Token() {
		
	}
	
	/**
	 * Construct a JWT string given by id and subject
	 * @param id 
	 * @param subject
	 * @return JWT the returned token
	 */
	public String createJWT(String id, String subject) {
		return createJWT(id, JWT_ISSUER, subject, JWT_TTL, JWT_SECRET);
	}
	
	/**
	 * Create a JWT token given by four parameters
	 * @param type
	 * @param issuer
	 * @param subject
	 * @param ttlMillis
	 * @param secret
	 * @return
	 */
	public static String createJWT(String id, String issuer, String subject, long ttlMillis, String secret) {
	 
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	/**
	 * Verify a given JWT string
	 * @param jwt
	 * @param type
	 * @param issuer
	 * @param secret
	 * @return return whether if verification succeeded, false if failed.
	 */
    public static boolean verifyJWT(String jwt, String id, String issuer, String subject, String secret) {
    	try{
	    	Claims claims = Jwts.parser()      
	       .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
	       .parseClaimsJws(jwt).getBody();
	    	if (claims.getId() == null || claims.getSubject() == null || claims.getIssuer() == null)
	    		return false;
	    	if ( !(claims.getId().equals(id) 
	    			&& claims.getIssuer().equals(issuer) && claims.getSubject().equals(subject)) )
	    	return false;
	    	
	    	long nowMillis = System.currentTimeMillis();
		    Date now = new Date(nowMillis); 
			Date exp = claims.getExpiration();
			if (now.before(exp))
			    return true;
		    return false;
		    
		}catch (SignatureException e){
		    return false;
		}catch (IncorrectClaimException e) {
			return false;
		}catch(MalformedJwtException e) {
			return false;
		}catch(ExpiredJwtException e){
			return false;
		}
    }
	
    /**
     * Verify a jst token using default JWT_ISSUER and JWT_SECRET
     * @param jwt
     * @param id
     * @param subject
     * @return return false if verification failed, and true if passed.
     */
    public static boolean verifyJWT(String jwt, String id, String subject) {
    	return verifyJWT(jwt, id, JWT_ISSUER, subject, JWT_SECRET);
    }
    
}

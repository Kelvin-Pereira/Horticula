package com.koldex.horticola.api.oauth.service;

import com.koldex.horticola.api.oauth.entity.User;
import com.koldex.horticola.api.oauth.repository.UserRepository;
import com.koldex.horticola.config.exceptions.NegocioException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository repository;
    private static final String SECRET_KEY = "4226452948404D635166546A576D5A7134743777217A25432A462D4A614E6452";

    public String extractUsername(String token) {
        return extractClaim(token, (Claims c) ->c.get("email").toString());
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(createClaim(userDetails));
    }

    private Map<String, String> createClaim(UserDetails userDetails) {
        User user = repository.findByEmailIgnoreCase(userDetails.getUsername()).orElseThrow(() -> new NegocioException("Usúario não localizado."));
        Map<String, String> claims = new HashMap<>();
        claims.put("idUsuario", user.getIdUser().toString());
        claims.put("nome", user.getNomeCompleto());
        claims.put("cpf", user.getCpf());
        claims.put("email", userDetails.getUsername());
        claims.put("perfils", userDetails.getAuthorities().stream().map(String::valueOf).collect(Collectors.joining(",")));
        return claims;
    }

    public String generateToken(Map<String, String> extraClaims) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

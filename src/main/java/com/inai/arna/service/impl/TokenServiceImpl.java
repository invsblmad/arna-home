package com.inai.arna.service.impl;

import com.inai.arna.dto.response.TokenResponse;
import com.inai.arna.security.UserDetailsImpl;
import com.inai.arna.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JwtEncoder encoder;
    private final UserDetailsService userDetailsService;
    @Value("${access.jwt-token.expiration-period}")
    private int accessTokenExpirationPeriod;
    @Value("${refresh.jwt-token.expiration-period}")
    private int refreshTokenExpirationPeriod;

    @Override
    public TokenResponse generateTokens(UserDetailsImpl userDetails) {
        String accessToken = generateToken(userDetails, accessTokenExpirationPeriod);
        String refreshToken = generateToken(userDetails, refreshTokenExpirationPeriod);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public TokenResponse refreshTokens() {
        return generateTokens(getUserDetailsFromToken());
    }

    @Override
    public UserDetailsImpl getUserDetailsFromToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt token = (Jwt) authentication.getPrincipal();
        return (UserDetailsImpl) userDetailsService.loadUserByUsername(token.getSubject());
    }

    private String generateToken(UserDetailsImpl userDetails, int expirationPeriod) {
        JwtClaimsSet claims = getJwtClaims(userDetails, expirationPeriod);
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private JwtClaimsSet getJwtClaims(UserDetailsImpl userDetails, int expirationPeriod) {
        Instant now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expirationPeriod, ChronoUnit.HOURS))
                .subject(userDetails.getUsername())
                .id(String.valueOf(userDetails.user().getId()))
                .claim("scope", getScope(userDetails))
                .build();
    }

    private String getScope(UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }
}

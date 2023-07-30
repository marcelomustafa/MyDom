package br.com.mariapuri.mydom.config.security.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mariapuri.mydom.app.repository.user.UserRepository;
import br.com.mariapuri.mydom.config.security.auth.AuthTokenProvider;
import br.com.mariapuri.mydom.config.security.payload.RefreshToken;
import br.com.mariapuri.mydom.config.security.repository.RefreshTokenRepository;
import br.com.mariapuri.mydom.exceptionhandler.trowexceptionhandler.TokenRefreshException;
import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {
	
	@Autowired
	private AuthTokenProvider authProvider;
  
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(UUID userId) {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken = refreshTokenRepository.findByUserId(userId).get();
    refreshToken.setExpiryDate(Instant.now().plusMillis(authProvider.getValidityRefreshIn()));
    refreshToken.setToken(UUID.randomUUID().toString());

    return refreshTokenRepository.save(refreshToken);
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(UUID userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}

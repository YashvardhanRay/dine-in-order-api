package com.example.dio.config;

import com.example.dio.security.util.UserIdentity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Configuration
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final UserIdentity userIdentity;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userIdentity.getCurrentUserEmail());
    }
}

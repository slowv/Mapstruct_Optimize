package com.example.unscape.database;

import com.example.unscape.entity.auth.AccessEntity;
import com.example.unscape.entity.auth.RoleEntity;
import com.example.unscape.entity.auth.UserEntity;
import com.example.unscape.repository.AccessRepository;
import com.example.unscape.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseMigration implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) return;

        final String publicKey = " %s\n %s\n %s\n %s\n %s\n %s\n "
                .formatted(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        final AccessEntity access = new AccessEntity();
        access.setPublicKey(publicKey);

        final RoleEntity role = new RoleEntity();
        role.setName("ADMIN");

        final UserEntity user = new UserEntity();
        user.setAccess(Set.of(access));
        user.setRoles(Set.of(role));
        user.setUuid(UUID.randomUUID().toString());
        user.setFullName("SlowV Developer");
        userRepository.save(user);
    }
}

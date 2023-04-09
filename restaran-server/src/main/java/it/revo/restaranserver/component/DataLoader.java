package it.revo.restaranserver.component;

import it.revo.restaranserver.entity.Aware;
import it.revo.restaranserver.entity.Role;
import it.revo.restaranserver.entity.User;
import it.revo.restaranserver.entity.enums.AwareStatus;
import it.revo.restaranserver.entity.enums.RoleName;
import it.revo.restaranserver.repository.AuthRepository;
import it.revo.restaranserver.repository.AwareRepository;
import it.revo.restaranserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DataLoader implements CommandLineRunner {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AwareRepository awareRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("never")) {
            awareRepository.save(
                    new Aware(
                            "login",
                            "login",
                            "login",
                            "/auth/login",
                            AwareStatus.ABOUT
                    )
            );
            awareRepository.save(
                    new Aware(
                            "register",
                            "register",
                            "register",
                            "/auth/register",
                            AwareStatus.ABOUT
                    )
            );
            roleRepository.save(
                    new Role(
                            RoleName.ADMIN
                    )
            );
            roleRepository.save(
                    new Role(
                            RoleName.USER
                    )
            );
            authRepository.save(
                    new User(
                            "Sayfullo",
                            "To'xtayev",
                            "+998990763246",
                            passwordEncoder.encode("Root123"),
                            roleRepository.findAll()
                    )
            );
        }
    }
}

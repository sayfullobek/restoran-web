package it.revo.restaranserver.service;

import it.revo.restaranserver.entity.User;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.ReqRegister;
import it.revo.restaranserver.repository.AuthRepository;
import it.revo.restaranserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthService(AuthRepository authRepository, RoleRepository roleRepository) {
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
    }

    public ApiResponse register(ReqRegister request) {
        try {
//            PasswordEncoder passwordEncoder = new ;
            if (request.getPassword().equals(request.getPrePassword())) {
                if (!authRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                    User user = new User(
                            request.getFirstName(),
                            request.getLastName(),
                            "+" + request.getPhoneNumber(),
                            passwordEncoder().encode(request.getPassword()),
                            Collections.singletonList(roleRepository.findById(request.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("getRole")))
                    );
                    authRepository.save(user);
                    return new ApiResponse("User saqlandi", true);
                } else {
                    return new ApiResponse("Bunday user bor", false);
                }
            } else {
                return new ApiResponse("Parollar mos emas", false);
            }
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));

    }

    public UserDetails getUserById(UUID id) {
        return authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public User getOneUser(UUID id) {
        Optional<User> byId = authRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            return user;
        }
        return null;
    }

    public ApiResponse deleteUser(UUID uuid) {
        Optional<User> byId = authRepository.findById(uuid);
        if (byId.isPresent()) {
            User user = byId.get();
            authRepository.delete(user);
            return new ApiResponse("successfully deleted user", true);
        } else {
            return new ApiResponse("bunday user mavjud emas", false);
        }
    }

    public ApiResponse editUser(UUID id, ReqRegister reqRegister) {
        Optional<User> byId = authRepository.findById(id);
        if (byId.isPresent()) {
            if (reqRegister.getPassword().equals(reqRegister.getPrePassword())) {
                User user = byId.get();
                user.setFirstName(reqRegister.getFirstName());
                user.setLastName(reqRegister.getLastName());
                user.setPhoneNumber("+" + reqRegister.getPhoneNumber());
                user.setPassword(reqRegister.getPassword());
                user.setRoles(Collections.singletonList(roleRepository.findById(reqRegister.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("getRole"))));
                authRepository.save(user);
                return new ApiResponse("successfully saved user", true);
            } else {
                return new ApiResponse("password va prePassword teng bolishi shart", false);
            }
        } else {
            return new ApiResponse("bunday user mavjud emas", false);
        }
    }
}

package com.bsuir.shoken.iam;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class UserService {

    private final UserRepository userRepository;

    private final PasswordHashEncoder passwordHashEncoder;

    @Transactional(readOnly = true)
    Page<User> findAll(final Pageable pageable) {
        return userRepository.findAllByOrderByRegistrationDateDesc(pageable);
    }

    @Transactional(readOnly = true)
    User findById(final Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    Optional<User> findByLogin(final String login) {
        return userRepository.findOneByLogin(login);
    }

    @Transactional(readOnly = true)
    Optional<User> findByEmail(final String email) {
        return userRepository.findOneByEmail(email);
    }

    User create(final User userToCreate) {

        final User user = new User(userToCreate.getLogin(), passwordHashEncoder.encode(userToCreate.getPassword()),
                userToCreate.getEmail());

        return userRepository.save(user);
    }
}

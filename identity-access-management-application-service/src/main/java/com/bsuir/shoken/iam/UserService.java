package com.bsuir.shoken.iam;

import com.bsuir.shoken.AlreadyExistingEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordHashEncoder passwordHashEncoder;

    private final ApplicationEventPublisher publisher;

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

    public User create(final User userToCreate) throws AlreadyExistingEntityException {

        final Optional<User> userByLogin = userRepository.findOneByLogin(userToCreate.getLogin());
        if (userByLogin.isPresent()) {
            throw new AlreadyExistingEntityException("User with such login already exists.");
        }

        final Optional<User> userByEmail = userRepository.findOneByLogin(userToCreate.getEmail());
        if (userByEmail.isPresent()) {
            throw new AlreadyExistingEntityException("User with such email already exists.");
        }

        final User user = new User(userToCreate.getLogin(), passwordHashEncoder.encode(userToCreate.getPassword()),
                userToCreate.getEmail());

        final User userFromDatabase = userRepository.save(user);

        publisher.publishEvent(new UserCreatedEvent(userFromDatabase));

        return userFromDatabase;
    }
}

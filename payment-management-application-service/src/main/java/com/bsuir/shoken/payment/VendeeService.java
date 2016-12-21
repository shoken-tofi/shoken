package com.bsuir.shoken.payment;

import com.bsuir.shoken.NoSuchEntityException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@Service
@Transactional
class VendeeService {

    private final VendeeRepository vendeeRepository;

    @Transactional(readOnly = true)
    Vendee findById(final Long id) {
        return vendeeRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    Vendee findByName(final String name) throws NoSuchEntityException {
        return vendeeRepository.findOneByName(name)
                .orElseThrow(() -> new NoSuchEntityException("Vendee with such name = " + name + " doesn't exists."));
    }

    Vendee create(final Vendee vendee) {
        return vendeeRepository.save(vendee);
    }
}

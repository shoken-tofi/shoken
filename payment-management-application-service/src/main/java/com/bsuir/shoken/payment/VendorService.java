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
class VendorService {

    private final VendorRepository vendorRepository;

    @Transactional(readOnly = true)
    Vendor findById(final Long id) {
        return vendorRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    Vendor findByName(final String name) throws NoSuchEntityException {
        return vendorRepository.findOneByName(name)
                .orElseThrow(() -> new NoSuchEntityException("Vendor with such name = " + name + " doesn't exists."));
    }

    Vendor create(final Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}

package com.bsuir.shoken;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__({@Autowired}))

@RestController
@RequestMapping(value = "/init")
class ApplicationInitializer {

    private static final Logger LOGGER = getLogger(ApplicationInitializer.class);

    private static final int BID_COUNT = 5;

    private final UserInitializer userInitializer;

    private final BidInitializer bidInitializer;

    private final BetInitializer betInitializer;

    @GetMapping
    public void init() {

        LOGGER.info("...initialization started...");

        userInitializer.init();

//        for (int i = 0; i < BID_COUNT; i++) {
//            bidInitializer.init();
//        }
//
//        betInitializer.init();

        LOGGER.info("...initialization ended...");
    }
}

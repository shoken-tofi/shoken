package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import com.bsuir.shoken.iam.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bets")
abstract class BetController {

    final static String PAGE = "1";
    final static String SIZE = "20";

    private final BetService betService;
    private final BetConverter betConverter;

    private final SecurityContextService securityContextService;

    private final SellerService sellerService;

    private final InvestorService investorService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public BetFindAllDto create(@RequestBody BetCreateDto dto) throws ValidationException, NoSuchEntityException {

        final String username = securityContextService.getUsername();

        final Seller seller = sellerService.findByName(username);
        if (seller != null) {
            throw new ValidationException("Seller can't create bet.");
        }

        final Investor investor = investorService.findByName(username);
        dto.setInvestorId(investor.getId());

        final Bet newBet = betConverter.toEntity(dto);
        final Bet createdBet = betService.create(newBet);

        return betConverter.toFindAllDto(createdBet);
    }
}

package com.bsuir.shoken.bid;

import com.bsuir.shoken.NoSuchEntityException;
import com.bsuir.shoken.ValidationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)

@RequestMapping("/bids")
abstract class BidController {

    private final static String PAGE = "1";
    private final static String SIZE = "10";

    private final BidService bidService;
    private final BidConverter bidConverter;

    private final BetService betService;
    private final BetConverter betConverter;

    private final SearchCriteriaConverter searchCriteriaConverter;

    private final InvestorService investorService;

    private final SellerService sellerService;

    @GetMapping
    public BidsFindAllDto getAll(@RequestParam(required = false, defaultValue = PAGE) int page,
                                 @RequestParam(required = false, defaultValue = SIZE) int size,
                                 @ModelAttribute SearchCriteriaDto searchCriteriaDto) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final SearchCriteria searchCriteria = searchCriteriaConverter.toEntity(searchCriteriaDto);
        final Page<Bid> bids = bidService.findAll(searchCriteria, pageRequest);

        return new BidsFindAllDto(bidConverter.toFindAllDTOs(bids.getContent()));
    }

    @GetMapping(value = "/{id}")
    public BidFindOneDto get(@PathVariable Long id) throws Exception {

        final Bid bid = bidService.findOne(id);

        return bidConverter.toFindOneDto(bid);
    }

    @PostMapping
    public BidFindAllDto create(@RequestParam("file") MultipartFile image, @RequestParam("data") BidCreateDto dto)
            throws ValidationException, NoSuchEntityException {

        final String username = "admin";
        final Seller seller = sellerService.findByName(username);
        dto.setSellerId(seller.getId());

        final String imageNme = image.getOriginalFilename();
        dto.setImageName(imageNme);

        final Bid bid = bidConverter.toEntity(dto);
        final Bid bidFromDatabase = bidService.create(bid);

        uploadImage(image, BidConverter.IMAGE_PATH + bidFromDatabase.getId() + "/" + imageNme);

        return bidConverter.toFindAllDto(bidFromDatabase);
    }

    private void uploadImage(final MultipartFile image, final String path) {

        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) throws ValidationException, NoSuchEntityException {
        bidService.delete(id);
    }

    @GetMapping("/{id}/bets")
    public BetsFindAllDto getBetsByBidId(@PathVariable(name = "id") Long bidId,
                                         @RequestParam(required = false, defaultValue = BetController.PAGE) int page,
                                         @RequestParam(required = false, defaultValue = BetController.SIZE) int size) {

        final Pageable pageRequest = new PageRequest(--page, size);
        final Page<Bet> bets = betService.findByBidId(bidId, pageRequest);

        return new BetsFindAllDto(betConverter.toFindAllDTOs(bets.getContent()));
    }
}

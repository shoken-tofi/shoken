package com.bsuir.shoken.bid;

import java.util.List;

public interface BetServiceGateway {

    List<BetCreateDto> create(int count);
}

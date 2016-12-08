package com.bsuir.shoken.iam;

import java.util.List;

public interface UserServiceGateway {

    List<RegisterDto> create(int count);
}

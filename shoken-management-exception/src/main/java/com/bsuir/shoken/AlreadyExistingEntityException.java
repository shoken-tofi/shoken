package com.bsuir.shoken;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistingEntityException extends Exception {

    public AlreadyExistingEntityException(String message) {
        super(message);
    }

    public AlreadyExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistingEntityException(Throwable cause) {
        super(cause);
    }
}

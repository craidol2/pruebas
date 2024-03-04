package com.development.gateway_master.shared.authentication.domain;

import com.development.commons.shared.exceptions.DomainException;

public class InvalidJwtFormat extends DomainException {
    public InvalidJwtFormat() {
        super(CONFLICT, CONFLICT.value(), "message.error.conflicts");
    }
}

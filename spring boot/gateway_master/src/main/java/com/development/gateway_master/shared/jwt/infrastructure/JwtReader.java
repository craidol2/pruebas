package com.development.gateway_master.shared.jwt.infrastructure;

import com.development.commons.shared.spring.object_mapper.CustomObjectMapper;
import com.development.gateway_master.shared.authentication.domain.InvalidJwtFormat;

import java.util.Base64;

public class JwtReader {

    public static JwtPayload getTokenPayload(String token) {
        try {
            var payloadEncoded = token.split("\\.")[1];
            return CustomObjectMapper.get().readValue(Base64.getUrlDecoder().decode(payloadEncoded), JwtPayload.class);
        } catch (Exception e) {
            throw new InvalidJwtFormat();
        }
    }
}

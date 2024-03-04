package com.development.gateway_master.shared.jwt.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtPayload {
    private String sid;
    private String name;
    private String email;
}

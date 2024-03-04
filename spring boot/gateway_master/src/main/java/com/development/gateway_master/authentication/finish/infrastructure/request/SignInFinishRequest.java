package com.development.gateway_master.authentication.finish.infrastructure.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignInFinishRequest {
    private String code;
}

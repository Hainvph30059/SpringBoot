package com.srping.identify_course.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.srping.identify_course.dto.request.*;
import com.srping.identify_course.dto.response.AuthenticationResponse;
import com.srping.identify_course.dto.response.IntrospectResponse;
import com.srping.identify_course.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    // cách để spotless không tự format đoạn code:
    //đánh dấu đoạn bắt đầu không format
    //spottles:off
    @PostMapping("/token")
    ApiReponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiReponse.<AuthenticationResponse>builder().result(result).build();
    }
    //đánh dấu đoạn tiếp tục được format
    //spottles:on
    @PostMapping("/introspect")
    ApiReponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiReponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiReponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiReponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiReponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiReponse.<AuthenticationResponse>builder().result(result).build();
    }
}

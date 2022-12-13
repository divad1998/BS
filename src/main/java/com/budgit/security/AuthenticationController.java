package com.budgit.security;

import com.budgit.dto.Login;
import com.budgit.hateoas.model.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/")
public class AuthenticationController {

    @PostMapping(path = "login")
    public Mono<Response> login(@RequestBody Login login) {
        //ToDo
        return null;
    }

    @PostMapping(path = "logout")
    public Mono<Response> logout() {
        //ToDo
        return null;
    }
}

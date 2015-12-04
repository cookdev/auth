package org.anyframe.cloud.auth.interfaces.rest;

import org.anyframe.cloud.auth.repository.domain.UserResource;
import org.anyframe.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hahn on 2015-12-04.
 */
@RestController
@RequestMapping(value="/user")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/regist", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registUser(@RequestBody UserResource userResource){
        userService.create(userResource);
    }
}

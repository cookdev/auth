package org.anyframe.cloud.auth.user.interfaces.rest.user;

import org.anyframe.cloud.auth.user.application.exception.IDAlreadyExistException;
import org.anyframe.cloud.auth.user.domain.RegisteredUser;
import org.anyframe.cloud.auth.user.interfaces.facade.dto.PasswordValidationDTO;
import org.anyframe.cloud.auth.user.application.RegistrationService;
import org.anyframe.cloud.auth.user.application.exception.PasswordIsWrongException;
import org.anyframe.cloud.auth.user.application.exception.UserIsNotExistException;
import org.anyframe.cloud.auth.user.interfaces.facade.dto.PasswordChangeDTO;
import org.anyframe.cloud.auth.user.interfaces.facade.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
	
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private RegistrationService registrationService;

    @RequestMapping(value = {"/user"}, method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredUser registerUser(@RequestBody RegisteredUser newUser){

        RegisteredUser registeredUser = registrationService.registerNewUser(newUser);
        return registeredUser;
    }

    @RequestMapping(value = {"/user/{userIdForModify}"}, method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser modifyUser(@RequestBody RegisteredUser registeredUser, @PathVariable String userIdForModify){

        logger.debug("##### User ID for modify : {}", userIdForModify);
        if(registeredUser.getUserId() == null){
            registeredUser.setUserId(userIdForModify);
        }

        RegisteredUser modifiedUser = registrationService.modifyUser(registeredUser);
        return modifiedUser;
    }

    @RequestMapping(value = {"/user/{userIdForDelete}"}, method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userIdForDelete){

        logger.debug("##### User ID for delete : {}", userIdForDelete);
        registrationService.deleteUser(userIdForDelete);
    }

    @RequestMapping(value = {"/user/{userId}"}, method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDTO getUser(@PathVariable String userId){
        UserInfoDTO resultUserInfo = new UserInfoDTO();

        logger.info("##### User ID for inquiry : {}", userId);
        RegisteredUser registeredUser =  registrationService.getUserById(userId);

        resultUserInfo.setUserId(registeredUser.getUserId());
        resultUserInfo.setUserName(registeredUser.getUserName());
        resultUserInfo.setEmail(registeredUser.getEmail());
        resultUserInfo.setPicture(registeredUser.getPicture());

        return resultUserInfo;
    }

    @RequestMapping(value = {"/password/validation"}, method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validatePassword(@RequestBody PasswordValidationDTO passwordValidationDTO){

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUserId(passwordValidationDTO.getUserId());
        registeredUser.setPassword(passwordValidationDTO.getPassword());

        registrationService.validatePassword(registeredUser);
    }

    @RequestMapping(value = {"/password/change"}, method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO){

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUserId(passwordChangeDTO.getUserId());
        registeredUser.setPassword(passwordChangeDTO.getPassword());

        RegisteredUser modifiedUser = registrationService.changePassword(registeredUser, passwordChangeDTO.getExistPassword());

        return modifiedUser;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "ID is Already existed")
    @ExceptionHandler(IDAlreadyExistException.class)
    public void idAlreadyExistException(){
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Is Not Found")
    @ExceptionHandler(UserIsNotExistException.class)
    public void userIsNotExistException(){
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Password Is Wrong")
    @ExceptionHandler(PasswordIsWrongException.class)
    public void passwordIsWrongException(){
    }
}

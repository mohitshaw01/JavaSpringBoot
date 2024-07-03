package com.ApiProject.ApiProject.Service;

import com.ApiProject.ApiProject.Dto.LoginDto;
import com.ApiProject.ApiProject.Dto.ResponseDto;
import com.ApiProject.ApiProject.Repository.UserRepository;
import com.ApiProject.ApiProject.Utils.PasswordHashingUtil;
import com.ApiProject.ApiProject.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseDto> loginUser(LoginDto userData) {
        ResponseDto responseDto = new ResponseDto();

        try {
            // Check if any of the required fields are null or empty
            if (userData == null || userData.getUserName() == null || userData.getUserName().isEmpty()
                    || userData.getEmail() == null || userData.getEmail().isEmpty()
                    || userData.getPassword() == null || userData.getPassword().isEmpty()) {
                responseDto.setResponse("Fill all the fields");
                responseDto.setStatusCode("400");
                return ResponseEntity.badRequest().body(responseDto);
            }

            // Check if user exists
            UserModel foundUser = userRepository.findByEmail(userData.getEmail());
            if (foundUser == null) {
                responseDto.setStatusCode("404");
                responseDto.setResponse("User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
            }

            // Password Verification
            String inputPasswordHash = PasswordHashingUtil.HashPassword(userData.getPassword());
            String storedPasswordHash = foundUser.getPassword();

            if (!inputPasswordHash.equals(storedPasswordHash)) {
                responseDto.setStatusCode("401");
                responseDto.setResponse("Incorrect password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
            }

            // Login successful
            responseDto.setStatusCode("200");
            responseDto.setResponse("Login successful");
            return ResponseEntity.ok(responseDto);

        } catch (Exception e) {
            // Handle exceptions
            responseDto.setStatusCode("500");
            responseDto.setResponse("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }


public ResponseEntity<ResponseDto> signUp(UserModel userData) {
    ResponseDto responseDto = new ResponseDto();

    try {
        // Check if any of the required fields are null or empty
        if (userData == null || userData.getUserName() == null || userData.getUserName().isEmpty()
                || userData.getEmail() == null || userData.getEmail().isEmpty()
                || userData.getPassword() == null || userData.getPassword().isEmpty()) {
            responseDto.setResponse("Enter all required data");
            responseDto.setStatusCode("400");
            return ResponseEntity.badRequest().body(responseDto);
        }

        // Password Hashing
        String hashedPassword = PasswordHashingUtil.HashPassword(userData.getPassword());
        userData.setPassword(hashedPassword);

        // Save user to repository
        userRepository.save(userData);

        // Success response
        responseDto.setStatusCode("200");
        responseDto.setResponse("User signed up and saved to database");
        logger.info("User Saved");
        return ResponseEntity.ok(responseDto);

    } catch (Exception e) {
        // Handle exceptions
        responseDto.setStatusCode("500");
        responseDto.setResponse("Internal Server Error");
        logger.error("Error while saving user: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
}
}

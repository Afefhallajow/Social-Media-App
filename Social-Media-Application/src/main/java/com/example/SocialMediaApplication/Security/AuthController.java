package com.example.SocialMediaApplication.Security;

import com.example.SocialMediaApplication.Entity.User;
import com.example.SocialMediaApplication.Extra.ApiResponse;
import com.example.SocialMediaApplication.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @RequestMapping (value = "/register" ,method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@RequestBody SignRequestDTO body){

        if (body.getUserName() == null || body.getPassword() == null) {
            return new ResponseEntity<>("userName or password should not be null",
                    HttpStatus.BAD_REQUEST);
        }

        try {
            User user = new User();
            user.setUsername(body.getUserName());
            user.setPassword(passwordEncoder.encode(body.getPassword()));
            userRepository.save(user);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new ApiResponse(true, "done"));
    }

    @RequestMapping (value = "/login" ,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody SignRequestDTO body) {
        if (body.getUserName() == null || body.getPassword() == null) {
            new ResponseEntity<>("userName or password should not be null",
                    HttpStatus.BAD_REQUEST);
        }
        logger.info("user name: {}", body.getUserName());
        UserDetails user = userDetailsService.loadUserByUsername(body.getUserName());

        if(user == null){
            return new ResponseEntity<>("user not found", HttpStatusCode.valueOf(400));
        }

        logger.info("userName: {} Password: {}", body.userName, body.password);

        if(!passwordEncoder.matches(body.getPassword(), user.getPassword())){
            return new ResponseEntity<>("passwords not match", HttpStatusCode.valueOf(400));
        }

        logger.info("pass matched");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.getUserName(), body.getPassword()));

        return ResponseEntity.ok(new HashMap<>(){{put("token", jwtService.generateToken(user));}});
    }
}
package com.travelbnb.controller;

import com.travelbnb.dto.AppUserDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.payload.JWTTokenDto;
import com.travelbnb.payload.LoginDto;
import com.travelbnb.repository.AppUserRepository;
import com.travelbnb.service.AppUserIMPL;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
public class AppUserController {
    private AppUserIMPL appUserIMPL;
    private AppUserRepository appUserRepository;


    public AppUserController(AppUserIMPL appUserIMPL, AppUserRepository appUserRepository) {
        this.appUserIMPL = appUserIMPL;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody AppUserDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        if (appUserRepository.existsByEmail(dto.getEmail())) {
            return new ResponseEntity<>("Email Exists", HttpStatus.BAD_REQUEST);
        }
        if (appUserRepository.existsByUsername(dto.getUsername())) {
            return new ResponseEntity<>("Username Exists", HttpStatus.BAD_REQUEST);
        }
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        AppUserDto appUserDto = appUserIMPL.addUser(dto);
        return new ResponseEntity<>(appUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto) {
        String token = appUserIMPL.verifyLogin(loginDto);
        if (token != null) {
            JWTTokenDto jwtToken = new JWTTokenDto();
            jwtToken.setType("JWT Token");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Username/Password", HttpStatus.CREATED);
        }
    }
    @DeleteMapping
    public ResponseEntity<String>deleteUser(@RequestParam long userId){
        appUserIMPL.deleteUser(userId);
        return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<AppUserDto>updateUser(
            @PathVariable long userId,@RequestBody AppUserDto dto){
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        AppUserDto appUserDto = appUserIMPL.updateUser(userId, dto);
        return new ResponseEntity<>(appUserDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AppUserDto>>getAllUser(
            @RequestParam (name = "pageNo",defaultValue = "5",required = false)int pageNo,
             @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize,
              @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "id",required = false) String sortDir){
        List<AppUserDto> allUser = appUserIMPL.getAllUser(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }
    @GetMapping("{getUserId}")
    public ResponseEntity<AppUserDto>getById(@RequestParam long userId){
        AppUserDto byId = appUserIMPL.getById(userId);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
}
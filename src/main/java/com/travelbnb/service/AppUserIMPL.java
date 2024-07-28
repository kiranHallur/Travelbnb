package com.travelbnb.service;
import com.travelbnb.dto.AppUserDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.exception.ResourceNotFound;
import com.travelbnb.payload.LoginDto;
import com.travelbnb.repository.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserIMPL implements AppUserService{


    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public AppUserIMPL(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }
    @Override
    public AppUserDto addUser(AppUserDto dto){
        AppUser appUser1 = dtoToEntity(dto);
        AppUser save = appUserRepository.save(appUser1);
        AppUserDto appUserDto = entityToDto(save);
        return appUserDto;
    }
    public AppUser dtoToEntity (AppUserDto dto){
        AppUser user=new AppUser();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
    public AppUserDto entityToDto (AppUser appUser){
        AppUserDto dto1=new AppUserDto();
        dto1.setId(appUser.getId());
        dto1.setName(appUser.getName());
        dto1.setEmail(appUser.getEmail());
        dto1.setUsername(appUser.getUsername());
        dto1.setRole(appUser.getRole());
        return dto1;
    }
    @Override
    public String verifyLogin(LoginDto loginDto){
        Optional<AppUser> byUsername = appUserRepository.findByUsername(loginDto.getUsername());
        if(byUsername.isPresent())  {
            AppUser appUser = byUsername.get();
      if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())) {
         String token= jwtService.generateToken(appUser);
         return token;
      }
        }
        return null;
    }
    @Override
    public void deleteUser(long userId) {
        appUserRepository.deleteById(userId);

    }
    @Override
    public AppUserDto updateUser(long userId, AppUserDto dto) {
        AppUser appUser=null;
        Optional<AppUser> byId = appUserRepository.findById(userId);
        if(byId.isPresent()){
              appUser = byId.get();
            appUser.setName(dto.getName());
            appUser.setUsername(dto.getUsername());
            appUser.setEmail(dto.getEmail());
            appUser.setPassword(dto.getPassword());
            appUser.setRole(dto.getRole());
            AppUser save = appUserRepository.save(appUser);
            AppUserDto appUserDto = entityToDto(save);
            return appUserDto;

        }else {
            throw  new  ResourceNotFound("User Not Found Id: "+userId);
        }
    }

    @Override
    public List<AppUserDto> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable=null;
        if(sortDir.equalsIgnoreCase("asc")){
            pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        }if(sortDir.equalsIgnoreCase("desc")){
            pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy).descending());
        }
        Page<AppUser> all = appUserRepository.findAll(pageable);
        List<AppUser> content = all.getContent();
        List<AppUserDto> collect = content.stream().map(c -> entityToDto(c)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public AppUserDto getById(long userId) {
        Optional<AppUser> byId = appUserRepository.findById(userId);
        if (byId.isPresent()){
            AppUser appUser = byId.get();
            AppUserDto appUserDto = entityToDto(appUser);
            return appUserDto;
        }else {
            throw new ResourceNotFound("User Not Found Id: "+userId);
        }

    }
}

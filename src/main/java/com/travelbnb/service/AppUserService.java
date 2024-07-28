package com.travelbnb.service;

import com.travelbnb.dto.AppUserDto;
import com.travelbnb.payload.LoginDto;

import java.util.List;

public interface AppUserService {
    public AppUserDto addUser(AppUserDto dto);
    public String verifyLogin(LoginDto loginDto);
    public void deleteUser(long userId);
    public AppUserDto updateUser(long userId,AppUserDto dto);
    public List<AppUserDto>getAllUser(int pageNo,int pageSize,String sortBy,String sortDir);
    public AppUserDto getById(long userId);
}

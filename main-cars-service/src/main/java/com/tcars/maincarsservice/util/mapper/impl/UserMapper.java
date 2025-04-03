package com.tcars.maincarsservice.util.mapper.impl;

import com.tcars.maincarsservice.dao.model.User;
import com.tcars.maincarsservice.service.dto.UserDto;
import com.tcars.maincarsservice.util.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public User mapToModel(UserDto userDto) {
        //todo avatar and password to hash
        return new User(userDto.getName(), userDto.getSurname(), null, userDto.getLogin(),
                userDto.getPassword(), userDto.getPhone());
    }

    @Override
    public UserDto mapToDto(User user) {
        //todo not return password and avatar to file
        return new UserDto(user.getName(), user.getSurname(), user.getLogin(), null,
                user.getPhone(), null);
    }
}

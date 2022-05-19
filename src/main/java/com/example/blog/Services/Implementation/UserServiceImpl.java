package com.example.blog.Services.Implementation;

import com.example.blog.Config.AppConstants;
import com.example.blog.Entities.Role;
import com.example.blog.Entities.User;
import com.example.blog.Exceptions.ResourceNotFoundException;
import com.example.blog.Payloads.UserDto;
import com.example.blog.Repositories.RoleRepository;
import com.example.blog.Repositories.UserRepository;
import com.example.blog.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);


    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User savedUser = userRepository.save(userDtoToEntity(userDto));
        return userEntityToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());


        User updateUser = userRepository.save(user);
        return userEntityToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        return userEntityToDto(findUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : allUsers){
            userDtoList.add(userEntityToDto(user));
        }
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        userRepository.delete(findUser);
        // userRepository.deleteById(findUser.getId());  alternate method


    }

    private User userDtoToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);

//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto userEntityToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }

}

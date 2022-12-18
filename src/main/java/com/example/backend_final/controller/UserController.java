package com.example.backend_final.controller;

import com.example.backend_final.dto.UserDto;
import com.example.backend_final.model.User;
import com.example.backend_final.payload.response.UserResp;
import com.example.backend_final.service.UserService;
import com.example.backend_final.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "1") Integer pageSize,
                                          @RequestParam(value = "sortBy",defaultValue = "username") String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> userPage = userService.findAll(pageable);
        List<User> userList = userPage.getContent();

        List<UserDto> userDtoList = userList.stream().map(user -> mapper.toUserDto(user)).collect(Collectors.toList());
        UserResp userResp = new UserResp();
        userResp.setListOfUsers(userDtoList);
        userResp.setPageNo(userPage.getNumber());
        userResp.setPageSize(userPage.getSize());
        userResp.setTotalElements(userPage.getTotalElements());
        userResp.setTotalPages(userPage.getTotalPages());
        userResp.setLast(userPage.isLast());
        return ResponseEntity.ok(userResp);
    }

    @GetMapping("/detail/{username}")
    public ResponseEntity<UserDto> getDetailUser(@PathVariable("username") String username){
        Optional<User> userTmp = userService.findByUsername(username);
        if(userTmp.isPresent())
            return ResponseEntity.ok(mapper.toUserDto(userTmp.get()));
        else
            return ResponseEntity.noContent().build();
    }
}

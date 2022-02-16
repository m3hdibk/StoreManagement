package com.mehdi.storemanagement.controller;

import com.mehdi.storemanagement.model.dto.UserData;
import com.mehdi.storemanagement.model.dto.response.StatisticsDashboardResponse;
import com.mehdi.storemanagement.service.PrivilegeService;
import com.mehdi.storemanagement.service.UserService;
import com.mehdi.storemanagement.service.impl.DashboardServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final PrivilegeService privilegeService;


    @PostMapping("/add")
    public ResponseEntity<?> getAllOrders(@RequestBody UserData userData) {
        userService.addUser(userData);
        return ResponseEntity.ok().build();
    }
     @GetMapping("/")
    public ResponseEntity<?> getUserByType(@RequestParam int type) {

        return ResponseEntity.ok(userService.getUsersByType(type));
    }
}

package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.service.impl.SchemeServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/schemes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class SchemeController {

    private final SchemeServiceImpl schemeService;

    @GetMapping
    public ResponseEntity<?> getAllSchemes(
            @RequestParam Integer type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "true") boolean enabled) {

        PageResponse<SchemeData> response = schemeService.getAllSchemes(type, page, size, enabled);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public void createScheme(@Valid @RequestBody SchemeData schemeData) {
        schemeService.createScheme(schemeData);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SchemeData> retrieveScheme(@PathVariable long id) {
        SchemeData scheme = schemeService.getSchemeById(id);
        return new ResponseEntity<>(scheme, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchemeData> updateScheme(@PathVariable long id, @RequestBody SchemeData schemeData) {
        schemeService.updateScheme(schemeData, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSchemeById(@PathVariable Long id) {
        schemeService.deleteSchemeById(id);
    }
}

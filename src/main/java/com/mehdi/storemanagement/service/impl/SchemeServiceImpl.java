package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.exception.InputValidationException;
import com.mehdi.storemanagement.exception.SchemeNotFoundException;
import com.mehdi.storemanagement.model.Scheme;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.repository.SchemeRepository;
import com.mehdi.storemanagement.service.SchemeService;
import com.mehdi.storemanagement.util.SchemeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public record SchemeServiceImpl(SchemeRepository schemeRepository) implements SchemeService {

    @Override
    public SchemeData getDefaultSchemeByType(int type) {
        return schemeRepository.findByTypeAndDefaultItemIsTrue(type).convertToData();
    }

    @Override
    public PageResponse<SchemeData> getAllSchemes(Integer type, int page, int size, boolean enabled) {
        Pageable paging = PageRequest.of(page, size);
        Page<Scheme> pageScheme;

        List<String> errors = validateType(type);

        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }

        if (enabled) {
            pageScheme = schemeRepository.findSchemesByTypeAndStatusTrue(type, paging);
        } else {
            pageScheme = schemeRepository.findSchemesByType(type, paging);
        }

        List<SchemeData> schemes = pageScheme.getContent().stream()
                .map(Scheme::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(schemes, pageScheme.getNumber(),
                pageScheme.getTotalElements(),
                pageScheme.getTotalPages());

    }

    private List<String> validateType(Integer type) {
        List<String> errors = new ArrayList<>();
        if (type == null || type <= 0) {
            errors.add("Invalid Type");
        } else {
            if (SchemeUtils.SchemeType.getById(type) == null) {
                errors.add("Invalid Type");
            }
        }
        return errors;
    }

    @Override
    public SchemeData getSchemeById(long schemeId) {
        Optional<Scheme> schemeOptional = schemeRepository.findById(schemeId);

        if (schemeOptional.isEmpty()) {
            throw new SchemeNotFoundException("Scheme not found, schemeId : " + schemeId);
        }
        return schemeOptional.get().convertToData();
    }

    @Override
    public SchemeData createScheme(SchemeData newScheme) {
        newScheme.setId(0);
        List<String> errors = validateType(newScheme.getType());
        Scheme defaultScheme = null;
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        if (newScheme.isDefaultItem()) {
            defaultScheme = schemeRepository.findByTypeAndDefaultItemIsTrue(newScheme.getType());
        }
        Scheme scheme = schemeRepository.save(newScheme.convertToEntity());
        if (defaultScheme != null) {
            defaultScheme.setDefaultItem(false);
            schemeRepository.save(defaultScheme);
        }
        newScheme.setId(scheme.getId());
        return newScheme;
    }


    @Override
    public SchemeData updateScheme(SchemeData updatedScheme, long schemeId) {
        Optional<Scheme> schemeOptional = schemeRepository.findById(schemeId);

        Scheme defaultScheme = null;
        if (schemeOptional.isEmpty()) {
            throw new SchemeNotFoundException("Scheme not found, schemeId : " + schemeId);
        }
        Scheme scheme = schemeOptional.get();
        List<String> errors = validateUpdate(updatedScheme, scheme);
        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        if (updatedScheme.isDefaultItem()) {
            defaultScheme = schemeRepository.findByTypeAndDefaultItemIsTrue(updatedScheme.getType());
        }
        updateEntity(scheme, updatedScheme);
        Scheme savedScheme = schemeRepository.save(scheme);
        if (defaultScheme != null) {
            defaultScheme.setDefaultItem(false);
            schemeRepository.save(defaultScheme);
        }
        return savedScheme.convertToData();

    }

    private List<String> validateUpdate(SchemeData updatedScheme, Scheme scheme) {
        List<String> errors = new ArrayList<>();
        if (updatedScheme.getType() != scheme.getType()) {
            errors.add("Scheme Type cannot be updated");
        }
        if (scheme.isDefaultItem() && !updatedScheme.isDefaultItem()) {
            errors.add("You can't update default value, at least one default item");
        }
        return errors;
    }

    private void updateEntity(Scheme scheme, SchemeData updatedScheme) {
        scheme.setStatus(updatedScheme.isStatus());
        scheme.setName(updatedScheme.getName());
        scheme.setDescription(updatedScheme.getDescription());
        scheme.setDefaultItem(updatedScheme.isDefaultItem());
    }



    @Override
    public void deleteSchemeById(long schemeId) {
        log.info("deleteSchemeById() - start: id = {}", schemeId);
        schemeRepository.deleteById(schemeId);
        log.info("deleteSchemeById() - end: id = {}", schemeId);
    }

}

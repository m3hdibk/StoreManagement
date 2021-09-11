package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.SchemeData;

public interface SchemeService {

    PageResponse<SchemeData> getAllSchemes(Integer type, int page, int size, boolean enabled);

    SchemeData getSchemeById(long schemeId);

    SchemeData createScheme(SchemeData newScheme);

    SchemeData updateScheme(SchemeData updatedScheme, long schemeId);

    void deleteSchemeById(long schemeId);
}
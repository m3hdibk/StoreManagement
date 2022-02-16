package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.Scheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {

    Page<Scheme> findSchemesByType(int type, Pageable paging);

    Page<Scheme> findSchemesByTypeAndStatusTrue(int type, Pageable paging);

    Page<Scheme> findAllByStatusTrue(Pageable paging);

    Optional<Scheme> findSchemesByIdAndStatusTrue(long schemeId);

    Optional<Scheme> findSchemesByIdAndStatusTrueAndType(long schemeId, int type);
    boolean existsSchemeByIdAndStatusTrueAndType(long schemeId, int type);
    Scheme findByTypeAndDefaultItemIsTrue(int type);

}

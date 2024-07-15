package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Modules;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModulesRepository extends JpaRepository<Modules, Integer> {
    @Query(value = "SELECT * FROM modules WHERE project_id= :projectTokenId", nativeQuery = true)
    List<Modules> getModulesByPojectIdToken(@Param("projectTokenId") int projectTokenId);

    @Query(value = "SELECT * FROM modules WHERE token_module= :tokenModule", nativeQuery = true)
    Optional<Modules> getModulesByTokenModule(@Param("tokenModule") String tokenModule);
}

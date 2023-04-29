package com.example.projectmanagement.Reposirtory;


import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Domaine.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByProjectId(Long projectId);

    @Override
    Optional<Activity> findById(Long id);

    List<Activity> findByIdIn(List<Long> ids);

}

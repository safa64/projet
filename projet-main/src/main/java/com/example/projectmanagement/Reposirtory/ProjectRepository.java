package com.example.projectmanagement.Reposirtory;


import com.example.projectmanagement.Domaine.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    Optional<Project> findById(Long Long);
}

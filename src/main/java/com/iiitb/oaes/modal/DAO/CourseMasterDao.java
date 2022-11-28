package com.iiitb.oaes.modal.DAO;

import com.iiitb.oaes.modal.CourseMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMasterDao  extends JpaRepository<CourseMaster, Integer> {
    public List<CourseMaster> findAll();
}

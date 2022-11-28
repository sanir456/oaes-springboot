package com.iiitb.oaes.services;

import com.iiitb.oaes.modal.CourseMaster;
import com.iiitb.oaes.modal.DAO.CourseMasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "courseMaterService")
public class CourseMasterService {
    @Autowired
    private CourseMasterDao courseMasterDao;

    public List<CourseMaster> getCourses()
    {
        List<CourseMaster> courses = courseMasterDao.findAll();
        return courses;
    }
}

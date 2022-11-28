package com.iiitb.oaes.modal.DAO;

import com.iiitb.oaes.modal.QuestionPaperSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuestionPaperSetDao extends JpaRepository<QuestionPaperSet, Integer> {
    @Query(value = "SELECT * FROM question_paper_set where is_validate = 0 ", nativeQuery = true)
    List<QuestionPaperSet> getNotValidateQuestionPaperSet();

    QuestionPaperSet findBySetId(int setId);
}

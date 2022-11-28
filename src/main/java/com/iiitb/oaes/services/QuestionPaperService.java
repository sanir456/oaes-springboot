package com.iiitb.oaes.services;


import com.iiitb.oaes.modal.DAO.ItemDao;
import com.iiitb.oaes.modal.DAO.QuestionPaperSetDao;
import com.iiitb.oaes.modal.Item;
import com.iiitb.oaes.modal.QuestionPaperSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "questionPaperService")
public class QuestionPaperService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private QuestionPaperSetDao questionPaperSetDao;

    public QuestionPaperSet generatePaperSet(Map<String,Object> payload){
        QuestionPaperSet questionPaperSet = new QuestionPaperSet();
        int courseId = Integer.parseInt((String)payload.get("courseId"));
        int numberOfQuestionPaper = Integer.parseInt((String)payload.get("numberOfQuestionPaper"));
        Map<String,Object> testPattern = (Map<String,Object>) payload.get("testPattern");
        int numberOfSection = Integer.parseInt((String) testPattern.get("numberOfSection"));
        int totalMarks = Integer.parseInt((String) testPattern.get("totalMarks"));

        questionPaperSet.setCourseId(courseId);
        questionPaperSet.setNumberOfQuestionPaper(numberOfQuestionPaper);
        questionPaperSet.setNumberOfSection(numberOfSection);
        questionPaperSet.setTotalMarks(totalMarks);

        String paperSet = "{";
        for(int i=1;i<=numberOfQuestionPaper;i++)
        {
            if(i!=1) paperSet+=",";
            paperSet+="\"" + Integer.toString(i) + "\": {";
            for(int j=1;j<=numberOfSection;j++)
            {
                if(j!=1) paperSet+=",";
                Map<String,Object> sectionPattern = (Map<String,Object>) testPattern.get(Integer.toString(j));
                paperSet+="\"" + Integer.toString(j) + "\": {";
                paperSet+="\"questionType\": \"" +(String) sectionPattern.get("questionType") + "\",";
                paperSet+="\"sectionMarks\": \"" +(String) sectionPattern.get("sectionMarks") + "\",";
                paperSet+="\"numberOfQuestion\": \"" +(String) sectionPattern.get("numberOfQuestion") + "\",";
                paperSet+="\"numberOfQuestionAttempt\": \"" +(String) sectionPattern.get("numberOfQuestionAttempt") + "\",";
                paperSet+="\"questions\": [";
                paperSet+=generateSection(sectionPattern,courseId);
                paperSet+="]}";
            }
            paperSet+="}";
        }
        paperSet+="}";
        questionPaperSet.setPaperSet(paperSet);
        questionPaperSet = questionPaperSetDao.save(questionPaperSet);
        return questionPaperSet;
    }

    public String generateSection(Map<String,Object> section,int courseId)
    {
        String category = (String) section.get("questionType");
        int numberOfQuestion = Integer.parseInt((String) section.get("numberOfQuestion"));
        List<Item> items = itemDao.randomItem(courseId,category,numberOfQuestion);
        String questions = "";
        for(int i=1;i<=items.size();i++)
        {
            if(i!=1) questions+=",";
            questions+=Integer.toString(items.get(i-1).getItemId());
        }
        return questions;
    }

    public List<QuestionPaperSet> getNonValidateQuestionPaperSet()
    {
        List<QuestionPaperSet> questionPaperSets = questionPaperSetDao.getNotValidateQuestionPaperSet();
        return questionPaperSets;
    }

    public void validateQuestionPaperSet(Map<String,String> payload)
    {
        int questionPaperSetId = Integer.parseInt(payload.get("setId"));
        QuestionPaperSet questionPaperSet = questionPaperSetDao.findBySetId(questionPaperSetId);
        questionPaperSet.setValidate(true);
        questionPaperSetDao.save(questionPaperSet);
    }
}

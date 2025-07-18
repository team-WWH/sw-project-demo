package com.example.wwh.DTO;

import com.example.wwh.pojo.Comment;
import java.util.List;
public class QuestionDTO
{
    private int questionID;
    private String questionContent;
    private String answer;//答案ABCD
    private List<Comment> comments;  // 存储多个评论
    private String Answercon;//答案解析
    private String QresultsID;//题目分析结果ID
    private String A;   //选项
    private String B;
    private String C;
    private String D;

    public QuestionDTO(int questionID, String questionContent, String answer, List<Comment> comments) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.answer = answer;
        this.comments = comments;
    }
}

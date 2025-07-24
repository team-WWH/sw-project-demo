package com.example.wwh.Controller;


import com.example.wwh.Data.AccurayAndNumber;
import com.example.wwh.Data.ListenerAccuracyResponse;
import com.example.wwh.Data.QuetionAccuracy;
import com.example.wwh.Data.SpeechQuestionAnalysis;
import com.example.wwh.service.AnalysisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;
    //Listener的正确率
    @GetMapping("Listener/Accuracy")
    public AccurayAndNumber getListenerAccuracy(@Param("ListenerID") Integer ListenerID,@Param("SpeechID") Integer SpeechID){
        return analysisService.getlistenerAccuracy(ListenerID,SpeechID);
    }
    //Speech全部Listener的正确率
    @GetMapping("Speaker/AllListenerAccuracy")
    public List<ListenerAccuracyResponse> getAllListenerAccuracyAndNumber(@Param("speechID") Integer SpeechID){
        System.out.println(SpeechID);
        return analysisService.getspeechAccuracy(SpeechID);
    }
    //题目正确率分布
    @GetMapping("Speaker/QuestionAnalysis")
    public SpeechQuestionAnalysis getspeechQuestionAnalysis(@Param("SpeechID") Integer SpeechID){
        List<QuetionAccuracy> quetionAccuracies = analysisService.getQueationAccuracy(SpeechID);
        SpeechQuestionAnalysis speechQuestionAnalysis = new SpeechQuestionAnalysis();
        Integer high = 0;
        Integer middle = 0;
        Integer low = 0;
        for(QuetionAccuracy quetionAccuracy:quetionAccuracies){
            if(quetionAccuracy.getAccuracy()>0.6){
                high+=1;
            }else if(quetionAccuracy.getAccuracy()<0.6&&quetionAccuracy.getAccuracy()>0.3){
                middle+=1;
            }else{
                low+=1;
            }
        }
        speechQuestionAnalysis.setHigh(high);
        speechQuestionAnalysis.setMiddle(middle);
        speechQuestionAnalysis.setLow(low);
        speechQuestionAnalysis.setTotal(quetionAccuracies.size());
        return speechQuestionAnalysis;
    }
    //演讲平均正确率
    @GetMapping("Speaker/SpeechAccuracy")
    public double getSpeechAccuracy(@Param("SpeechID") Integer SpeechID){
        return analysisService.getSpeechTotalAccuracy(SpeechID);
    }


}

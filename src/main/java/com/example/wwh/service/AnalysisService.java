package com.example.wwh.service;

import com.example.wwh.Data.ListenerAccuracyResponse;
import com.example.wwh.Data.QuetionAccuracy;
import com.example.wwh.Data.AccurayAndNumber;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Stuanswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AnalysisService {
    @Autowired
    private  StuAnswerService stuAnswerService;
    @Autowired
    private  SpeechService speechService;

    //得到listener在某个speech的正确率
    public AccurayAndNumber getlistenerAccuracy(Integer ListenerID,Integer SpeechID){
            List<Integer> QuestionIDList =speechService.getAllQuestionIDBySpeechID(SpeechID);
            Integer Accuracy = 0 ;
            Integer number = 0;
            for (Integer questionID:QuestionIDList ){
                Stuanswers stuanswers = stuAnswerService.getAnswerByListenerAndQuestion(ListenerID,questionID);
                if(stuanswers.getQS()!=0){
            Accuracy = Accuracy +stuanswers.getState();
                }
                if(stuanswers.getQS()==2){
                    number+=1;
                }

            }
        AccurayAndNumber accurayAndNumber = new AccurayAndNumber();
            accurayAndNumber.setAccuray((double) Accuracy /(QuestionIDList.size()));
            accurayAndNumber.setNumber(number);
            return accurayAndNumber;
        }

    //得到speech的全部人的正确率和答题数量
    public List<ListenerAccuracyResponse> getspeechAccuracy(Integer SpeechID) {
        List<Listener> ListenerIDList = speechService.getAllListenerBySpeechID(SpeechID);
        List<ListenerAccuracyResponse> Listeneraccuracy = new ArrayList<>();
        for (Listener listener : ListenerIDList) {

          double accuracy  =getlistenerAccuracy(listener.getListenerID(),SpeechID).getAccuray();
          Integer number = getlistenerAccuracy(listener.getListenerID(),SpeechID).getNumber();
            ListenerAccuracyResponse listenerAccuracyResponse= new ListenerAccuracyResponse();
            listenerAccuracyResponse.setAccuracy(accuracy);
            listenerAccuracyResponse.setAnonymous(listener.getAnonymous());
            listenerAccuracyResponse.setUname(listener.getUname());
            listenerAccuracyResponse.setPhone(listener.getPhone());
            listenerAccuracyResponse.setMail(listener.getMail());
            listenerAccuracyResponse.setNumber(number);
            Listeneraccuracy.add(listenerAccuracyResponse);
        }
        Listeneraccuracy.sort(Comparator.comparingDouble(ListenerAccuracyResponse::getAccuracy).reversed());;
        return Listeneraccuracy;
    }
    //演讲的平均正确率
    public double getSpeechTotalAccuracy(Integer SpeechID){
        double totalAccuracy = 0;
        List<ListenerAccuracyResponse> listenerAccuracyResponses = getspeechAccuracy(SpeechID);
        for(ListenerAccuracyResponse listenerAccuracyResponse:listenerAccuracyResponses){
            totalAccuracy += listenerAccuracyResponse.getAccuracy();
        }
        return totalAccuracy/(listenerAccuracyResponses.size());
    }

    //每个题目的正确率
    public List<QuetionAccuracy> getQueationAccuracy(Integer SpeechID){
        List<Integer> QuestionIDList =speechService.getAllQuestionIDBySpeechID(SpeechID);
        List<QuetionAccuracy> quetionAccuracies = new ArrayList<>();
        for(Integer QuestionID:QuestionIDList){
           List<Stuanswers> stuanswersList = stuAnswerService.getStuanswerByQuestionID(QuestionID);
            Integer right = 0;
           for(Stuanswers stuanswers:stuanswersList){
               right+=stuanswers.getState();
           }
           QuetionAccuracy quetionAccuracy = new QuetionAccuracy();
           quetionAccuracy.setQuestionID(QuestionID);
           quetionAccuracy.setAccuracy((double) right/(stuanswersList.size()));
           quetionAccuracies.add(quetionAccuracy);
        }
        return quetionAccuracies;
    }

}

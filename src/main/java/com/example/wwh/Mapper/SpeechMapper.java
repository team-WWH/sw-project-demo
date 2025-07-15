package com.example.wwh.Mapper;
import com.example.wwh.pojo.Speech;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface SpeechMapper {

    @Select("SELECT * FROM speech WHERE Sstatus = 1")  // 获取正在进行中的演讲
    List<Speech> getOngoingSpeeches();

    @Select("SELECT * FROM speech WHERE Sstatus = 0")  // 获取已结束的演讲
    List<Speech> getEndedSpeeches();
}
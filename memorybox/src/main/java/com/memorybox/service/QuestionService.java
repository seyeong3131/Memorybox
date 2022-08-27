package com.memorybox.service;

import com.memorybox.dto.QuestionDto;
import com.memorybox.dto.QuestionFormDto;
import com.memorybox.dto.QuestionSearchDto;
import com.memorybox.entity.*;
import com.memorybox.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    private final QueBundleRepository queBundleRepository;

    private final QuestionImgService questionImgService;
    private final QuestionImgRepository questionImgRepository;
    private final SaveQueRepository saveQueRepository;

    public Long saveQuestion(QuestionFormDto questionFormDto, MultipartFile questionImgFile) throws Exception{
        Question question = questionFormDto.createQuestion(queBundleRepository);
        questionRepository.save(question);

        QuestionImg questionImg = new QuestionImg();
        questionImg.setQuestion(question);
        questionImg.setRepImgYn("Y");
        questionImgService.saveQuestionImg(questionImg, questionImgFile);

        return question.getId();
    }

    @Transactional(readOnly = true)
    public QuestionFormDto getQuestionFormDto(Long QuestionId){
        Question question = questionRepository.findById(QuestionId).orElseThrow(EntityNotFoundException::new);
        QuestionFormDto questionFormDto = QuestionFormDto.of(question);
        return questionFormDto;
    }

    public Long updateQuestion(QuestionFormDto questionFormDto, MultipartFile questionImgFile) throws Exception{
        Question question = questionRepository.findById(questionFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        QueBundle queBundle = queBundleRepository.findByQueBundleNm(questionFormDto.getQueBundleNm());
        Long questionImgId = questionFormDto.getQuestionImgId();
        questionImgService.updateQuestionImg(questionImgId, questionImgFile);
        question.updateQuestion(questionFormDto, queBundle);

        return question.getId();
    }

    @Transactional(readOnly = true)
    public Page<Question> getAdminQuestionPage(QuestionSearchDto questionSearchDto, Pageable pageable){
        return questionRepository.getAdminQuestionPage(questionSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public boolean validateQuestion(Long questionId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(EntityExistsException::new);
        Member savedMember = memberRepository.findByEmail(question.getCreatedBy());

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void deleteQuestion(Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(EntityExistsException::new);
        QuestionImg questionImg = questionImgRepository.findByQuestionId(questionId);
        questionImgRepository.delete(questionImg);
        questionRepository.delete(question);
    }

    public List<QuestionDto> getSaveQuestionDtoList(Long saveQueBookId){
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Long> saveQueIdList = new ArrayList<>();
        List<SaveQue> saveQues = saveQueRepository.findBySaveQueBookId(saveQueBookId);
        for(SaveQue saveQue : saveQues){
            saveQueIdList.add(saveQue.getId());
        }

        for (Long saveQueId : saveQueIdList){
            Long questionId = saveQueRepository.findQueIdById(saveQueId);
            Question question = questionRepository.findOneById(questionId);
            QuestionDto questionDto
                    = new QuestionDto(question, questionImgRepository);
            questionDtoList.add(questionDto);
        }

        return questionDtoList;
    }
}

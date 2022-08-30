package com.memorybox.service;


import com.memorybox.dto.MyQueBoxDetailDto;
import com.memorybox.dto.MyQueBoxItemDto;
import com.memorybox.entity.Member;
import com.memorybox.entity.MyQueBox;
import com.memorybox.entity.MyQueBoxItem;
import com.memorybox.entity.Question;
import com.memorybox.repository.MemberRepository;
import com.memorybox.repository.MyQueBoxItemRepository;
import com.memorybox.repository.MyQueBoxRepository;
import com.memorybox.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MyQueBoxService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final MyQueBoxRepository myQueBoxRepository;
    private final MyQueBoxItemRepository myQueBoxItemRepository;

    public Long addMyQueBox(MyQueBoxItemDto myQueBoxItemDto, String email) {
        Question question = questionRepository.findById(myQueBoxItemDto.getQuestionId())
                .orElseThrow(EntityExistsException::new);
        Member member = memberRepository.findByEmail(email);

        MyQueBox myQueBox = myQueBoxRepository.findByMemberId(member.getId());
        if (myQueBox == null) {
            myQueBox = MyQueBox.createMyQueBox(member);
            myQueBoxRepository.save(myQueBox);
        }
        MyQueBoxItem savedMyQueBoxItem = myQueBoxItemRepository.findByMyQueBoxIdAndQuestionId(myQueBox.getId(), question.getId());

        if(savedMyQueBoxItem != null){
            throw new IllegalStateException("이미 저장된 문제입니다.");
        }

        MyQueBoxItem myQueBoxItem = MyQueBoxItem.createMyQueBoxItem(myQueBox, question);
        myQueBoxItemRepository.save(myQueBoxItem);
        return myQueBoxItem.getId();

    }

    @Transactional(readOnly = true)
    public List<MyQueBoxDetailDto> getMyQueBoxList(String email){
        List<MyQueBoxDetailDto> myQueBoxDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);

        MyQueBox myQueBox = myQueBoxRepository.findByMemberId(member.getId());
        if(myQueBox == null){
            return myQueBoxDetailDtoList;
        }
        myQueBoxDetailDtoList = myQueBoxItemRepository.findMyQueBoxDetailDtoList(myQueBox.getId());
        return myQueBoxDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateMyQueBoxItem(Long myQueBoxItemId, String email){
        Member curMember = memberRepository.findByEmail(email);
        MyQueBoxItem myQueBoxItem = myQueBoxItemRepository.findById(myQueBoxItemId).
                orElseThrow(EntityExistsException::new);
        Member savedMember = myQueBoxItem.getMyQueBox().getMember();

        if(!StringUtils.equals(curMember.getEmail(),savedMember.getEmail())){
            return false;
        }
        return true;
    }


    public void deleteMyQueBoxItem(Long myQueBoxItemId){
        MyQueBoxItem myQueBoxItem = myQueBoxItemRepository.findById(myQueBoxItemId).
                orElseThrow(EntityExistsException::new);
        myQueBoxItemRepository.delete(myQueBoxItem);
    }


}

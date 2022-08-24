package com.memorybox.service;

import com.memorybox.dto.QuestionFormDto;
import com.memorybox.dto.SaveQueDto;
import com.memorybox.entity.Member;
import com.memorybox.entity.Question;
import com.memorybox.entity.SaveQue;
import com.memorybox.entity.SaveQueBook;
import com.memorybox.repository.MemberRepository;
import com.memorybox.repository.QuestionRepository;
import com.memorybox.repository.SaveQueBookRepository;
import com.memorybox.repository.SaveQueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import static com.memorybox.entity.QSaveQue.saveQue;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveQueBookService {

    private final QuestionRepository questionRepository;

    private final MemberRepository memberRepository;

    private final SaveQueBookRepository saveQueBookRepository;

    private final SaveQueRepository saveQueRepository;


    public Long addSaveQueBook(SaveQueDto saveQueDto, String email){
        Question question= questionRepository.findById(saveQueDto.getQuestionId())
                .orElseThrow(EntityExistsException::new);
        Member member = memberRepository.findByEmail(email);

        SaveQueBook saveQueBook= saveQueBookRepository.findByMemberId(member.getId());
        if (saveQueBook == null){
            saveQueBook = SaveQueBook.createSaveQueBook(member);
            saveQueBookRepository.save(saveQueBook);
        }

        SaveQue savedSaveQue = saveQueRepository.findBySaveQueBookIdAndQuestionId(saveQueBook.getId(), question.getId());

        if (savedSaveQue != null){
            return savedSaveQue.getId();
        }
        else {
            SaveQue saveQue = SaveQue.createSaveQue(saveQueBook , question);
            saveQueRepository.save(saveQue);
            return saveQue.getId();
        }
    }

    public Long findSaveQueBookId(Long MemberId){
        SaveQueBook saveQueBook = saveQueBookRepository.findByMemberId(MemberId);
        return saveQueBook.getId();
    }

    public Boolean saveQueBookCheck(Long MemberId){
        if(saveQueBookRepository.findByMemberId(MemberId) != null){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public boolean validateSaveQue(Long saveQueId, String email){
        Member curMember = memberRepository.findByEmail(email);
        SaveQue saveQue = saveQueRepository.findById(saveQueId)
                .orElseThrow(EntityExistsException::new);
        Member savedMember = saveQue.getSaveQueBook().getMember();

        if (!StringUtils.equals(curMember.getEmail(),savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void deleteSaveQue(Long saveQueId) {

        SaveQue saveQue = saveQueRepository.findById(saveQueId).
                orElseThrow(EntityExistsException::new);
        saveQueRepository.delete(saveQue);
    }

}

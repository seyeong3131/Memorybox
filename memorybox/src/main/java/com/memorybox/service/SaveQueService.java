package com.memorybox.service;

import com.memorybox.dto.SaveQueAlertDto;
import com.memorybox.entity.SaveQue;
import com.memorybox.repository.SaveQueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveQueService {

    private final SaveQueRepository saveQueRepository;

    public List<SaveQueAlertDto> findMySaveQueList(Long saveQueBookId){
        List<SaveQue> saveQues = saveQueRepository.findBySaveQueBookId(saveQueBookId);
        List<SaveQueAlertDto> saveQueAlertDtoList = new ArrayList<>();

        for(SaveQue saveQue : saveQues){
            SaveQueAlertDto saveQueAlertDto = new SaveQueAlertDto(saveQue);
            saveQueAlertDtoList.add(saveQueAlertDto);
        }

        return saveQueAlertDtoList;
    }

    public Long findSaveQueId(Long saveQueBookId, Long questionId){
        SaveQue saveQue = saveQueRepository.findBySaveQueBookIdAndQuestionId(saveQueBookId, questionId);
        return saveQue.getId();
    }

    public void deleteSaveQue(Long saveQueId){
        SaveQue saveQue = saveQueRepository.findById(saveQueId)
                .orElseThrow(EntityExistsException::new);
        saveQueRepository.delete(saveQue);
    }
}

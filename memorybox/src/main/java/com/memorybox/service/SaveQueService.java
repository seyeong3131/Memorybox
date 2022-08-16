package com.memorybox.service;

import com.memorybox.dto.SaveQueAlertDto;
import com.memorybox.entity.SaveQue;
import com.memorybox.repository.SaveQueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

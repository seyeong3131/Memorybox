package com.memorybox.service;

import com.memorybox.dto.QueBundleDto;
import com.memorybox.dto.QueBundleFormDto;
import com.memorybox.entity.QueBundle;
import com.memorybox.repository.QueBundleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QueBundleService {

    private final QueBundleRepository queBundleRepository;


    public Long saveQueBundle(QueBundleFormDto queBundleFormDto){
        QueBundle queBundle = queBundleFormDto.createQueBundle();
        queBundleRepository.save(queBundle);

        return queBundle.getId();
    }

    public List<QueBundle> queBundleList(){
        return queBundleRepository.findAll();
    }
}

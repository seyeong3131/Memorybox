package com.memorybox.service;

import com.memorybox.dto.*;
import com.memorybox.entity.Member;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import com.memorybox.repository.QueBundleImgRepository;
import com.memorybox.repository.QueBundleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QueBundleService {

    private final QueBundleRepository queBundleRepository;


    private final QueBundleImgService queBundleImgService;
    private final QueBundleImgRepository queBundleImgRepository;
    public Long saveQueBundle(QueBundleFormDto queBundleFormDto) throws Exception{
        QueBundle queBundle = queBundleFormDto.createQueBundle();
        queBundleRepository.save(queBundle);

        return queBundle.getId();
    }

    @Transactional(readOnly = true)
    public QueBundleFormDto getQueBundleDto(Long QueBundleId){
        QueBundle queBundle = queBundleRepository.findById(QueBundleId).orElseThrow(EntityNotFoundException::new);
        QueBundleFormDto queBundleFormDto = QueBundleFormDto.of(queBundle);
        return queBundleFormDto;
    }


    public Long updateQueBundle(QueBundleFormDto queBundleFormDto, MultipartFile queBundleImgFile) throws Exception{
        QueBundle queBundle = queBundleRepository.findById(queBundleFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        queBundle.updateQueBundle(queBundleFormDto);
        Long queBundleImgId = queBundleFormDto.getQueBundleImgId();
        queBundleImgService.updateQueBundleImg(queBundleImgId, queBundleImgFile);


        return queBundle.getId();
    }

    @Transactional(readOnly = true)
    public Page<QueBundle> getAdminQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable){
        return queBundleRepository.getAdminQueBundlePage(queBundleSearchDto, pageable);
    }
    public void deleteQueBundle(Long queBundleId){
        QueBundle queBundle = queBundleRepository.findById(queBundleId)
                .orElseThrow(EntityExistsException::new);
        queBundleRepository.delete(queBundle);
    }


    public List<String> queBundleNmList(){
        return queBundleRepository.findAllQueBundleNm();
    }


    @Transactional(readOnly = true)
    public Page<MainQueBundleDto> getMainQueBundlePage(QueBundleSearchDto queBundleSearchDto, Pageable pageable){
        return queBundleRepository.getMainQueBundlePage(queBundleSearchDto, pageable);
    }
}

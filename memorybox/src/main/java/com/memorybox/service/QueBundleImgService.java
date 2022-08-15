package com.memorybox.service;


import com.memorybox.entity.QueBundleImg;
import com.memorybox.repository.QueBundleImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class QueBundleImgService {
    @Value("${itemImgLocation}") //application.properties에 itemImgLocation
    private String itemImgLocation;
    private final QueBundleImgRepository queBundleImgRepository;
    private final FileService fileService;

    public void saveQueBundleImg(QueBundleImg queBundleImg, MultipartFile queBundleImgFile) throws Exception {

        String oriImgName = queBundleImgFile.getOriginalFilename(); // 오리지날 이미지 경로
        String imgName = "";
        String imgUrl = "";
        System.out.println(oriImgName);
        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) { // oriImgName 문자열로 비어 있지 않으면 실행
            imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    queBundleImgFile.getBytes());
            System.out.println(imgName);
            imgUrl = "/images/item/" + imgName;
        }
        //상품 이미지 정보 저장
        // oriImgName : 상품 이미지 파일의 원래 이름
        // imgName : 실제 로컬에 저장된 상품 이미지 파일의 이름
        // imgUrl :  로컬에 저장된 상품 이미지 파일을 불러오는 경로
        queBundleImg.updateQueBundleImg(oriImgName, imgName, imgUrl);
        queBundleImgRepository.save(queBundleImg);
    }

    public void updateQueBundleImg(Long queBundleImgId, MultipartFile queBundleImgFile) throws Exception {
        if (!queBundleImgFile.isEmpty()) { // 상품의 이미지를 수정한 경우 상품 이미지 업데이트
            QueBundleImg savedQueBundleImg = queBundleImgRepository.findById(queBundleImgId).
                    orElseThrow(EntityNotFoundException::new); // 기존 엔티티 조회
            // 기존에 등록된 상품 이미지 파일이 있는경우 파일 삭제
            if (!StringUtils.isEmpty(savedQueBundleImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + savedQueBundleImg.getImgName());
            }
            String oriImgName = queBundleImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName,
                    queBundleImgFile.getBytes()); // 파일 업로드
            String imgUrl = "/images/item/" + imgName;
            //변경된 상품 이미지 정보를 세팅
            //상품 등록을 하는 경우에는 ItemImgRepository.save()로직을 호출 하지만
            //호출을 하지 않았습니다.
            //savedItemImg 엔티티는 현재 영속성 상태이다.
            // 그래서 데이터를 변경하는 것만으로 변경을 감지기능이 동작
            // 트랜잭션이 끝날때 update 쿼리가 실행 된다.
            //※ 영속성 상태여야함 사용가능
            savedQueBundleImg.updateQueBundleImg(oriImgName, imgName, imgUrl);
        }
    }
}

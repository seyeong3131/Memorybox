package com.memorybox.dto;

import com.memorybox.constant.QCategory;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QueBundleFormDto {
    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String queBundleNm;

    private QCategory qCategory;

    private QueBundleImgDto queBundleImgDto; //이미지 정보

    private Long queBundleImgId; //상품 이미지 아이디

    private static ModelMapper modelMapper = new ModelMapper();

    public QueBundle createQueBundle() {

        return modelMapper.map(this, QueBundle.class); // ItemFormDto -> Item 연결
    }

    public static QueBundleFormDto of(QueBundle queBundle) {

        return modelMapper.map(queBundle, QueBundleFormDto.class); // Item -> ItemFormDto 연결
    }

}

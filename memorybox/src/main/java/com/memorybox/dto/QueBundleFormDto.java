package com.memorybox.dto;

import com.memorybox.constant.QCategory;
import com.memorybox.entity.QueBundle;
import com.memorybox.entity.Question;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QueBundleFormDto {
    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String queBundleNm;

    private QCategory qCategory;

    private static ModelMapper modelMapper = new ModelMapper();

    public QueBundle createQueBundle() {

        return modelMapper.map(this, QueBundle.class); // ItemFormDto -> Item 연결
    }

    public static QueBundleFormDto of(QueBundle queBundle) {

        return modelMapper.map(queBundle, QueBundleFormDto.class); // Item -> ItemFormDto 연결
    }

}

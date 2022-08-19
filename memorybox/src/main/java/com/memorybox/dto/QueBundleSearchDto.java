package com.memorybox.dto;

import com.memorybox.constant.QCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueBundleSearchDto {

        private String searchBy;

        private QCategory searchCategory;

        private String searchDateType;

        private String searchQuery="";

}

package com.memorybox.dto;

import com.memorybox.constant.QCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueBundleSearchDto {

        private String searchDateType;

        private QCategory searchQCategory;

        private String searchBy;

        private String searchQuery="";

}

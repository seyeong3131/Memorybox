package com.memorybox.controller;


import com.memorybox.dto.MyQueBoxDetailDto;
import com.memorybox.dto.MyQueBoxItemDto;
import com.memorybox.service.MyQueBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyQueBoxController {

    private final MyQueBoxService myQueBoxService;

    @PostMapping(value = "/myQueBox")
    public @ResponseBody
    ResponseEntity queBundle(@RequestBody @Valid MyQueBoxItemDto myQueBoxItemDto,
                             BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
            String email = principal.getName();
            Long myQueBoxItemId;
            try {
                myQueBoxItemId = myQueBoxService.addMyQueBox(myQueBoxItemDto, email);
            } catch (Exception e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Long>(myQueBoxItemId, HttpStatus.OK);
        }

    @GetMapping(value = "/myQueBox")
    public String orderHist(Principal principal, Model model){
        List<MyQueBoxDetailDto> myQueBoxDetailDtoList = myQueBoxService.getMyQueBoxList(principal.getName());
        System.out.println(myQueBoxDetailDtoList.size());
        model.addAttribute("myQueBoxItems",myQueBoxDetailDtoList);
        return "/myQueBox/myQueBoxList";
    }

    @PatchMapping(value = "/myQueBoxItem/{myQueBoxItemId}")
    public @ResponseBody ResponseEntity updateMyQueBoxItem(@PathVariable("myQueBoxItemId") Long myQueBoxItemId, Principal principal) {
        System.out.println(myQueBoxItemId);
        if (!myQueBoxService.validateMyQueBoxItem(myQueBoxItemId, principal.getName())) {
            return new ResponseEntity<String>("수정권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Long>(myQueBoxItemId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/myQueBoxItem/{myQueBoxItemId}")
    public @ResponseBody ResponseEntity deleteMyQueBoxItem(@PathVariable("myQueBoxItemId") Long myQueBoxItemId,
                                                       Principal principal){
        if (!myQueBoxService.validateMyQueBoxItem(myQueBoxItemId, principal.getName())) {
            return new ResponseEntity<String>("수정권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        myQueBoxService.deleteMyQueBoxItem(myQueBoxItemId);
        return new ResponseEntity<Long>(myQueBoxItemId, HttpStatus.OK);
    }

}

package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.CreateGroupDto;
import com.anshuahi.splitwise.dto.CreateGroupRequest;
import com.anshuahi.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @PostMapping("/create-group")
    public ResponseEntity<CreateGroupDto> createGroup(@RequestBody CreateGroupRequest request){
        System.out.println(request);
        return ResponseEntity.ok(groupService.createGroup(request));
    }
}

package com.anshuahi.splitwise.controller;

import com.anshuahi.splitwise.dto.CreateGroupDto;
import com.anshuahi.splitwise.dto.CreateGroupRequest;
import com.anshuahi.splitwise.dto.ExpenseGroupDto;
import com.anshuahi.splitwise.dto.GroupDetailsDto;
import com.anshuahi.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @PostMapping("/create-group")
    public ResponseEntity<CreateGroupDto> createGroup(@RequestBody CreateGroupRequest request){
        System.out.println(request);
        return ResponseEntity.ok(groupService.upsertGroup(request));
    }

    @GetMapping("/my-groups")
    public ResponseEntity<List<ExpenseGroupDto>> fetchGroups(@RequestParam Long id) {
        List<ExpenseGroupDto> groups = groupService.fetchGroups(id);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseGroupDto> getGroupDetails(@PathVariable Long id){
        return ResponseEntity.ok(groupService.fetchGroupDetails(id));
    }

    @DeleteMapping("/delete-group/{id}")
    public  ResponseEntity<String> deleteGroup(@PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.ok(groupService.deleteGroupById(id));
    }

    @PutMapping("/update-group")
    public ResponseEntity<CreateGroupDto> updateGroup(@RequestBody CreateGroupRequest request){
        System.out.println(request);
        return ResponseEntity.ok(groupService.upsertGroup(request));
//        return ResponseEntity.ok("Added");
    }
}

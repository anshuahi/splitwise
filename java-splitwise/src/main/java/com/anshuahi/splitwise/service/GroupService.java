package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.dto.*;
import com.anshuahi.splitwise.model.Expense;
import com.anshuahi.splitwise.model.ExpenseGroup;
import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.repository.ExpenseRepository;
import com.anshuahi.splitwise.repository.GroupRepository;
import com.anshuahi.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public CreateGroupDto createGroup(CreateGroupRequest request){
        // 1. validate input
        if(request.getName() == null || request.getName().isEmpty()){
            throw new IllegalArgumentException("Group Name is required");
        }

        if(request.getUserIds() == null || request.getUserIds().isEmpty() || request.getUserIds().size() < 2){
            throw new IllegalArgumentException("Atleast 2 users are required");
        }

        // 2. fetch users
        List<User> users = userRepository.findAllById(request.getUserIds());
//                .stream().map(
//                UserDto::new
//        ).toList();
        User user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));


        if (users.size() != request.getUserIds().size()) {
            throw new RuntimeException("Some users not found");
        }

        // 3. create group
        ExpenseGroup group = new ExpenseGroup();
        group.setName(request.getName());
        group.setUsers(users);
        group.setCreatedBy(user);

        CreateGroupDto dto = new CreateGroupDto(groupRepository.save(group));
        dto.setCreatedBy(new UserDto(user));
//        return "Group created successfully";
        return dto;
    }

    public List<ExpenseGroupDto> fetchGroups(Long id){
        List<ExpenseGroup> groups = groupRepository.findByUsersId(id);
        return groups.stream()
                .map(ExpenseGroupDto::new).toList();
    }

    public GroupDetailsDto fetchGroupDetails(Long id) {
         ExpenseGroup group = groupRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("No group found with group-id: " + id));
         List<ExpenseResponseDto> expenses = expenseRepository.findByExpenseGroupId(id)
                 .stream().map(
                         ExpenseResponseDto::new
                 ).toList();
         return new GroupDetailsDto(new ExpenseGroupDto(group), expenses);
    }

    public String deleteGroupById(Long id){
        ExpenseGroup group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group with " + id + "not found"));
        groupRepository.delete(group);
        return "Group deleted!";
    }
}

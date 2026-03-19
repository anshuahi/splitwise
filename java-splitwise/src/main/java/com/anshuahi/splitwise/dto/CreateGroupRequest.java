package com.anshuahi.splitwise.dto;

import com.anshuahi.splitwise.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequest {
    private String name;
    private Long createdBy;
    private List<Long> userIds;
}

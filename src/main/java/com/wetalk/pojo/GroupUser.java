package com.wetalk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class GroupUser {
    private int id;
    private int userId;
    private int groupId;
    private int privilege;
}

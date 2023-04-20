package com.luban.fox.security01.service;



import com.luban.fox.security01.bean.Permission;

import java.util.List;

/**
 * @author Fox
 */
public interface PermissionService  {

    List<Permission> selectByUserId(Long userId);
}

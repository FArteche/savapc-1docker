package com.ifsul.savapc.service;

import java.util.List;

import com.ifsul.savapc.model.Role;



public interface roleServices {
    List<Role> findAll();
    Role findById(Integer id);
    Role save(Role role);
    boolean deleteById(Integer id);
    boolean existsById(Integer roleId);
    
    Role findByNameLike(String name);
}

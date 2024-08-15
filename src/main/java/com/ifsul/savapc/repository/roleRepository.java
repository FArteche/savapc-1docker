package com.ifsul.savapc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.Role;



public interface roleRepository extends JpaRepository<Role,Integer>{
    Role findByNameLike(String name);


}

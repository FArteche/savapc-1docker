package com.ifsul.savapc.service.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.Role;
import com.ifsul.savapc.repository.roleRepository;
import com.ifsul.savapc.service.roleServices;

@Service
public class roleServiceImplements implements roleServices{
        @Autowired
         roleRepository rRepository;

        @Override
        public List<Role> findAll() {
            return rRepository.findAll();
        }

        @Override
        public Role findByNameLike(String name) {
            return rRepository.findByNameLike(name);
        }

        @Override
        public Role findById(Integer id) {
            return rRepository.findById(id).get();
        }

        @Override
        public Role save(Role role) {
           return rRepository.save(role);
        }

        @Override
        public boolean deleteById(Integer id) {
            try {
                if (rRepository.existsById(id)) {
                    rRepository.deleteById(id);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public boolean existsById(Integer roleId) {
            return rRepository.existsById(roleId);
        }

        

   
    

   

    
    
}

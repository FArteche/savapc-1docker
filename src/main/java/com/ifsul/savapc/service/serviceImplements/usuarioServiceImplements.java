package com.ifsul.savapc.service.serviceImplements;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.ifsul.savapc.model.Role;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

import com.ifsul.savapc.repository.roleRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.usuarioService;
import com.ifsul.savapc.web.dto.UsuarioRegistrationDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class usuarioServiceImplements implements usuarioService{
    @Autowired
    usuarioRepository usuarioRepository;
    @Autowired
    roleRepository roleRepository;
    @Autowired
    testeRepository testeRepository;
    @Autowired
    turmaRepository turmaRepository;

    @Override
    public List<usuario> findByIdentificadorLike(String identificador) { 
        return usuarioRepository.findByIdentificadorLike(identificador); }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuario user = usuarioRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username ou Password invalido.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public List<usuario> findAll() {
        return usuarioRepository.findAll();
    }
    @Override
    public usuario findById(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public usuario save(UsuarioRegistrationDto registrationDto) {
       usuario user = new usuario(registrationDto.getNome(),
        registrationDto.getEmail(), registrationDto.getTelefone(),registrationDto.getTipo(),
        registrationDto.getIdentificador(),LocalDate.parse(registrationDto.getDataNascimento()) ,registrationDto.getImg(),
        registrationDto.getUsername(),
        new BCryptPasswordEncoder().encode(registrationDto.getPassword()), Arrays.asList(new Role( "ROLE_ALUNO")));
        if(roleRepository.findByNameLike("ROLE_ALUNO")==null || roleRepository.findByNameLike("ROLE_PROF")==null){
               if(registrationDto.getTipo().equalsIgnoreCase("aluno")){
                    user.setRoles(Arrays.asList(new Role("ROLE_ALUNO")));
                    System.out.println(registrationDto.toString());
                    return usuarioRepository.save(user);
               }else{
                    user.setRoles(Arrays.asList(new Role("ROLE_PROF")));
                    System.out.println(registrationDto.toString());
                    return usuarioRepository.save(user);
               }

        }else{
            if(registrationDto.getTipo().equalsIgnoreCase("aluno")){
                user.setRoles(Arrays.asList( roleRepository.findByNameLike("ROLE_ALUNO")));
                System.out.println(registrationDto.toString());
                return usuarioRepository.save(user);
            }
            else{
                user.setRoles(Arrays.asList( roleRepository.findByNameLike("ROLE_PROF")));
                System.out.println(registrationDto.toString());
                return usuarioRepository.save(user);
            }
        }
    }
    
    @Override
    public usuario save(usuario usuarioexistente) {
        Role Raluno = roleRepository.findByNameLike("ROLE_ALUNO");
        Role Rprof = roleRepository.findByNameLike("ROLE_PROF");
        List<Role> ListRaluno = new ArrayList<>();
        List<Role> ListRprof = new ArrayList<>();
        
    if(Raluno==null || Rprof==null){
            if(usuarioexistente.getTipo().equalsIgnoreCase("aluno")){
                Role novoRoleAluno = new Role("ROLE_ALUNO");
                roleRepository.save(novoRoleAluno);
                ListRaluno.add(roleRepository.findByNameLike("ROLE_ALUNO"));
                usuarioexistente.setRoles(ListRaluno);
                return usuarioRepository.save(usuarioexistente);
            }else{
                Role novoRoleProf = new Role("ROLE_PROF");
                roleRepository.save(novoRoleProf);
                ListRprof.add(roleRepository.findByNameLike("ROLE_PROF"));
                usuarioexistente.setRoles(ListRprof);
                return usuarioRepository.save(usuarioexistente);
            }
            
        
    }else{
        
        if(usuarioexistente.getTipo().equalsIgnoreCase("aluno")){
            ListRaluno.add(Raluno);
            usuarioexistente.setRoles(ListRaluno);
            return usuarioRepository.save(usuarioexistente);
        }else{
            ListRprof.add(Rprof);
            usuarioexistente.setRoles(ListRprof);
            return usuarioRepository.save(usuarioexistente);
        }
            
        
    } 
    }
    @Override
    public usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public List<usuario> findByNomeLike(String nome) {
        return usuarioRepository.findByNomeLike(nome);
    }
    
    @Override
    public boolean deleteById(Integer id) { 
        try {
            if(usuarioRepository.existsById(id)){
                usuarioRepository.deleteById(id);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;    
        }
        }
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(Role -> new SimpleGrantedAuthority(Role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<usuario> findByTipoLike(String tipo) {
        return usuarioRepository.findByTipoLike(tipo);
    }

    @Override
    public List<usuario> findByTurmasIn(List<turma> turmas) {
        return usuarioRepository.findByTurmasIn(turmas);
    }

    @Override
    public usuario trocarSenha(usuario usuario, String novaSenha) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(novaSenha));
        return usuarioRepository.save(usuario);
    }


    @Override
    public boolean existsById(Integer usuarioId) {
        return usuarioRepository.existsById(usuarioId);
    }
 
    
}

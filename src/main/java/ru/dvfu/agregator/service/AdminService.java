package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Admin;
import ru.dvfu.agregator.repository.AdminRepository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by anton on 20.03.17.
 */
@Service
public class AdminService {
    @Resource
    private AdminRepository adminRepository;

    public boolean authorizeAdmin(String login, String password) {
        Admin admin = adminRepository.getAdminByName(login);
        return admin != null && Objects.equals(admin.getPassword(), password);
    }

    public Admin getAdminByName(String name) {
        return adminRepository.getAdminByName(name);
    }
}

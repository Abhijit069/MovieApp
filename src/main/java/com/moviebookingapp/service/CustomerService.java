package com.moviebookingapp.service;

import com.moviebookingapp.dao.CustomerDao;
import com.moviebookingapp.dao.RoleDao;
import com.moviebookingapp.entity.Customer;
import com.moviebookingapp.entity.Role;
import com.moviebookingapp.exception.UsernameAlreadyExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    String Status = "success";
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RoleDao roleDao;

    public void initRoleandAdmin() {

        logger.info("-----Adding roles into role database----");

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role for MovieApp");
        roleDao.save(adminRole);

        logger.info("Admin role has added..");

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User Role for MovieApp");
        roleDao.save(userRole);

        logger.info("User role has added..");

        Customer adminUSer = new Customer();
        adminUSer.setEmail("admin@movieapp.com");
        adminUSer.setPassword(getEncodedPassword("pass"));
        adminUSer.setFirstName("Abhijit");
        adminUSer.setLastName("Saha");
        adminUSer.setUserName("admin");
        adminUSer.setConfirmPassword("pass");
        adminUSer.setContactNumber("100");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUSer.setRole(adminRoles);
        customerDao.save(adminUSer);

        logger.info(adminUSer.getUserName()+" has been assigned Admin role.");

    }

    public Customer saveCustomer(Customer customer) throws UsernameAlreadyExists {

        logger.info("saving customer with USER role into Customer database....");
        String custPassword = customer.getPassword();
        String custPasswordConfirmation = customer.getConfirmPassword();
        if(!custPassword.equals(custPasswordConfirmation)){
            logger.error("The password & confirm password field is not same");
            throw new UsernameAlreadyExists("The password & confirm password field is not same");
        }

        if((customerDao.findByUserName(customer.getUserName()).size()!=0)){
            logger.error("userName already exists");
            throw new UsernameAlreadyExists("userName already exists");
        }
        if((customerDao.findByEmail(customer.getEmail()).size()!=0)){
            logger.error("mailId is already registered");
            throw new UsernameAlreadyExists("mailId is already registered");
        }
        Role role = roleDao.findByRoleName("User");
        logger.info("User role is assigning to "+customer.toString());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
//        if(role.isPresent()){
//            logger.info("Role found "+role.toString());
//            userRoles.add(role.get());
//        }
        customer.setRole(userRoles);
        logger.info("User role is assigned to "+customer.toString());
        customer.setPassword(getEncodedPassword(customer.getPassword()));
        logger.info("customer data saved into database successfully....");
        logger.info("Exiting saveCustomer Method");
        return customerDao.save(customer);

    }

    public Map<String, String> forgotPassword(String username)
    {
        logger.info("Entering forgotPassword Method");
        Map<String, String> map = new HashMap<String, String>();
        Customer customer = customerDao.findByUserName(username).get(0);
        customer.setPassword(UUID.randomUUID().toString());
        customerDao.save(customer);
        map.put("newPassword", customer.getPassword());
        map.put("resetStatus",Status);
        logger.info("Exiting forgotPassword Method, passWord reset successful");
        return map;
    }

    public List<Customer> showAllCustomer(){

        logger.info("Entering showAllCustomer Method");
        System.out.println(customerDao.findAll());
        logger.info("Exiting showAllCustomer Method");
        return customerDao.findAll();

    }

    public List<Customer> findByEmail(String email) {
        return customerDao.findByEmail(email);
    }
    public String getEncodedPassword(String password) {
        logger.info("Password has been encrypted....");
        return passwordEncoder.encode(password);
    }
}

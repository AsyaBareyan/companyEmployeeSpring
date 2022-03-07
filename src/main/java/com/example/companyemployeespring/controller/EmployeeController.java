package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
@Autowired
    private EmployeeRepository employeeRepository;
@Autowired
    CompanyRepository companyRepository;

@GetMapping("/employees")
    public String employees(ModelMap map){
    List<Employee> employees = employeeRepository.findAll();
    map.addAttribute("employees",employees);
    return "employees";
}
    @GetMapping("/employees/byCompany/{id}")
    public String employeesByCompanyPage(ModelMap map, @PathVariable("id") int id) {
        Company company = companyRepository.getById(id);
        List<Employee> employees = employeeRepository.findAllByCompany(company);
        map.addAttribute("employees", employees);
        return "employees";

    }

    @GetMapping("/employees/add")
    public String addEmployeePage(ModelMap map) {
        map.addAttribute("employees", employeeRepository.findAll());
        map.addAttribute("companies", companyRepository.findAll());
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
}

package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/companies")
    public String companyPage(ModelMap map) {
        Iterable<Company> companies = companyRepository.findAll();
        map.addAttribute("companies", companies);
        return "company";

    }

    @GetMapping("/deleteCompany/{id}")
    @Transactional
    public String deleteCompany(@PathVariable("id") int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";

    }

    @GetMapping("/addCompany")
    public String addCompanyPage() {
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/companies";

    }
}

package com.elice.ai_zy.controller;

import com.elice.ai_zy.domain.Company;
import com.elice.ai_zy.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @QueryMapping
    public Flux<Company> companyList(@Argument("page") Long page) {
        return companyRepository.findAll().skip(page * 10).take(10);
    }

    @QueryMapping
    public Mono<Long> companyCount() {
        return companyRepository.count();
    }
}

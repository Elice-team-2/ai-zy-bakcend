package com.elice.ai_zy.repository;

import com.elice.ai_zy.domain.Company;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface CompanyRepository extends ReactiveNeo4jRepository<Company, Long> {

}

package com.repository;

import com.domain.Users;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lijuan on 12/10/17.
 */
@Repository
public interface ClientRepository extends PagingAndSortingRepository<Users, Integer>, QueryDslPredicateExecutor<Users>{
}

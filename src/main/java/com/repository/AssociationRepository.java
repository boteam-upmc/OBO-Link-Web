package com.repository;

import com.domain.AssociationUsersRobots;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends
        PagingAndSortingRepository<AssociationUsersRobots, Long>,
        QueryDslPredicateExecutor<AssociationUsersRobots> {
}

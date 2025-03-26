package com.dev.hobby.user.repository.entity;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserCmdRepositoryCustom implements UserDuplicationChecker{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByEmail(String email){
        Long count = entityManager.createQuery("select count(u) from UserEntity u where u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }
}

package com.coockcoock.shop.recipe.repository;

import com.coockcoock.shop.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @PersistenceContext
    private EntityManager entityManager;
}
package com.coockcoock.shop.recipe.repository;

import com.coockcoock.shop.config.JpaConfig;
import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.recipe.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CommonMemberRepository commonMemberRepository;

    @Test
    void createTest() {
        LocalDate localDate = LocalDate.now();
        Member member = Member.builder()
                .loginId("loginId")
                .password("password")
                .signUpDate(localDate)
                .grade(new Grade(1L, "default"))
                .build();

        Member asd = commonMemberRepository.save(member);

        LocalDateTime test = LocalDateTime.now();
        Recipe recipe = Recipe.builder()
                .title("title")
                .cookery("content")
                .member(asd)
                .isDelete(false)
                .build();

        Recipe result = recipeRepository.save(recipe);

        assertThat(result.getTitle()).isEqualTo(recipe.getTitle());
    }
}
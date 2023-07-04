package com.coockcoock.shop.recipe.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecipeUpdaterNotPermissionException extends RuntimeException {
    private String writer;
    private String updater;

    @Override
    public String getMessage() {
        return "해당 글의 작성자만이 수정할 수 있습니다.";
    }
}

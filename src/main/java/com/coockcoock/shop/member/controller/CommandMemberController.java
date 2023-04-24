package com.coockcoock.shop.member.controller;

import com.coockcoock.shop.member.service.CommandMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 관련 Rest Controller
 *
 * @since 23-04-24
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/member")
public class CommandMemberController {
    private final CommandMemberService commandMemberService;


}

package com.qinC.mall.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.qinC.common.constant.AuthServerConstant;
import com.qinC.common.utils.R;
import com.qinC.common.vo.MemberRespVo;
import com.qinC.mall.auth.service.LoginService;
import com.qinC.mall.auth.vo.UserLoginVo;
import com.qinC.mall.auth.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @GetMapping("/msm/sendCode")
    public R sendCode(@RequestParam("phone") String phone) {
        R r = loginService.sendCode(phone);

        return r;
    }

    //TODO
    @PostMapping("/register")
    public String register(@Valid UserRegisterVo vo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap((fieldError) -> {
                return fieldError.getField();
            }, (fieldError) -> {
                return fieldError.getDefaultMessage();
            }));
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.qin.com/reg.html";
        }

        R r = loginService.register(vo);
        if (r.getCode() != 500) {
            if (r.getCode() == 0) {
                return "redirect:http://auth.qin.com/login.html";
            } else {
                Map<String, String> errors = new HashMap<String, String>();
                errors.put("msg", r.getData("msg", new TypeReference<String>(){}));
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.qin.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("code", "验证码错误");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.qin.com/reg.html";
        }
    }

    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes, HttpSession session) {
        R r = loginService.login(vo);
        if (r.getCode() == 0) {
            MemberRespVo data = r.getData("data", new TypeReference<MemberRespVo>() {
            });
            session.setAttribute(AuthServerConstant.LOGIN_USER, data);

            return "redirect:http://qin.com";
        } else {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("msg", r.getData("msg", new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.qin.com/login.html";
        }
    }

    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute == null) {
            return "login";
        } else {
            return "redirect:http://qin.com";
        }
    }

}

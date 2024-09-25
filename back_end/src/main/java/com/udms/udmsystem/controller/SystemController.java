package com.udms.udmsystem.controller;

import com.udms.udmsystem.pojo.LoginForm;
import com.udms.udmsystem.pojo.RegisterForm;
import com.udms.udmsystem.pojo.User;
import com.udms.udmsystem.service.UserService;
import com.udms.udmsystem.util.CreateVerifiCodeImage;
import com.udms.udmsystem.util.JwtHelper;
import com.udms.udmsystem.util.Result;
import com.udms.udmsystem.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ObjectUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/system")
public class SystemController {
    @Autowired
    private UserService userService;

    /*
    * 登录
    * */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String)session.getAttribute("verifiCode");
        String userVerifiCode = loginForm.getVerifiCode();
        if("".equals(sessionVerifiCode) || null == sessionVerifiCode){
            return Result.fail().message("验证码失效，请刷新后重试。");
        }
        if(!sessionVerifiCode.equalsIgnoreCase(userVerifiCode)){
            return Result.fail().message("验证码错误，请重新输入。");
        }

        session.removeAttribute("verifiCode");

        Map<String, String> map = new LinkedHashMap<>();
        try{
            User user = userService.login(loginForm);
            if(null != user){
                String token = JwtHelper.createToken(user.getId().longValue(), user.getName());
                map.put("token", token);
            }else{
                throw new RuntimeException("账号或密码错误。");
            }
            return Result.ok(map);
        } catch (RuntimeException e){
            e.printStackTrace();
            return Result.fail().message(e.getMessage());
        }
    }

    /*
    * 获取验证码
    * */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();

        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());

        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);

        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    * 获取用户信息
    * */
    @GetMapping("/getInfo")
    public Result getInfoByToken(@RequestHeader("Authorization") String token){
        boolean expiration = JwtHelper.isExpiration(token);
        if(expiration){
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }

        Long userId = JwtHelper.getUserId(token);

        Map<String, Object> map = new LinkedHashMap<>();
        User user = userService.getUserById(userId);

        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("type", user.getType());
        map.put("gid", user.getGid());
        map.put("avatar", user.getAvatar());
        map.put("phone", user.getPhone());
        map.put("email", user.getEmail());

        return Result.ok(map);
    }

    /*
     * 注册用户
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterForm registerForm){
        User user = userService.register(registerForm);
        if(null != user){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", user.getId());
            return Result.ok(map);
        }
        return Result.fail().message("注册失败");
    }

    /*
     * 检查用户名是否存在
     */
    @PostMapping("/checkUserName")
    public Result checkUserName(@RequestBody RegisterForm registerForm){
        User user = userService.checkUserName(registerForm);
        if(null != user){
            return Result.fail().message("该用户名已被使用！");
        }
        return Result.ok();
    }
}

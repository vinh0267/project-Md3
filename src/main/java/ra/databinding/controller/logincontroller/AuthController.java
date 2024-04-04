package ra.databinding.controller.logincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ra.databinding.model.dto.request.FormLogin;
import ra.databinding.model.dto.request.FormRegister;
import ra.databinding.model.entity.Role;
import ra.databinding.model.entity.RoleName;
import ra.databinding.model.entity.User;
import ra.databinding.service.LoginService;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = {"/", "/auth"})
public class AuthController {
    @Autowired
    private LoginService authService;

    @RequestMapping(value = {"/", "/register"})
    public ModelAndView formRegister() {
        return new ModelAndView("register", "user", new FormRegister());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute FormRegister user) {
        authService.register(user);
        return "redirect:/auth/login";
    }


    @RequestMapping("/login")
    public ModelAndView formLogin() {
        return new ModelAndView("login", "formLogin", new FormLogin());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute @Valid  FormLogin formLogin, BindingResult bindingResult,Model model) throws Exception {
        if (bindingResult.hasErrors()){
            model.addAttribute("formLogin", formLogin);
            return "login";
        }
        User user = authService.login(formLogin);
        if (user==null) {
            model.addAttribute("err", "username or pass incorrect");
            model.addAttribute("formLogin", formLogin);
            return "login";
        }
        if (user != null) { // Kiểm tra xem user có null không trước khi truy cập thuộc tính hoặc phương thức của nó
            Set<Role> roleSet = user.getRoleSet();
            boolean flag = false;
            for (Role role : roleSet) {
                if (role.getRoleName().equals(RoleName.ROLE_ADMIN)) {
                    flag = true;
                    break;
                }
            }// Lấy roleSet từ user, để sử dụng sau
            if (roleSet != null && flag) { // Kiểm tra roleSet có null không và có chứa ROLE_ADMIN không
                return "redirect:admin/dashboard"; // Nếu ROLE_ADMIN được tìm thấy, chuyển hướng đến trang dashboard của admin
            } else {
                return "user-home"; // Nếu không phải là ROLE_ADMIN, trả về trang chính của người dùng
            }
        }  else {
            model.addAttribute("err", "username or pass incorrect");
            model.addAttribute("formLogin", formLogin);
            return "login";
        }
    }
}

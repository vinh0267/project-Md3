package ra.databinding.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ra.databinding.model.entity.RoleName;
import ra.databinding.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//
public class AuthInterceptor implements HandlerInterceptor {
    /**
     * xử lí request trước khi được gọi vào controller
     * kiểm tra "userlogin " bên trong session
     * TH1:         không tìm thấy key userlogin => điều hướng đên login
     * TH2:         thấy => kiểm tra có phải admin không
     * không "
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // lay session
//        HttpSession session = request.getSession();
//        User userLogin = (User) session.getAttribute("userLogin");
//        if (userLogin==null){
//            response.sendRedirect("/auth/login");
//            return false;
//        }else{
//            if (userLogin.getRoleSet().stream().anyMatch(role->role.getRoleName().equals(RoleName.ROLE_ADMIN))){
//                return true;
//            }else {
//                response.sendRedirect("/403");
//                return false;
//            }


return true;    }

}

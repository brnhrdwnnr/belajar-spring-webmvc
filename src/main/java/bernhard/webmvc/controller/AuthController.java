package bernhard.webmvc.controller;

import bernhard.webmvc.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

//    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public ResponseEntity<String> login(
//            @RequestParam(name = "username") String username,
//            @RequestParam(name = "password") String password
//    ) {
//        if ("Bernhard".equals(username) && "ganteng".equals(password)) {
//            return new ResponseEntity<>("OK", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        if ("Bernhard".equals(username) && "ganteng".equals(password)) {
            HttpSession session = servletRequest.getSession(true);
            session.setAttribute("user", new User(username));


            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getUser(@CookieValue("username") String username) {
        return new ResponseEntity<>("Hello " + username, HttpStatus.OK);
    }
}

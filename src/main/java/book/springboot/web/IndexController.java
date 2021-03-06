package book.springboot.web;

import book.springboot.config.auth.LoginUser;
import book.springboot.config.auth.dto.SessionUser;
import book.springboot.service.posts.PostsService;
import book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
//    public String index(Model model, @LoginUser SessionUser user){
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");  //CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}

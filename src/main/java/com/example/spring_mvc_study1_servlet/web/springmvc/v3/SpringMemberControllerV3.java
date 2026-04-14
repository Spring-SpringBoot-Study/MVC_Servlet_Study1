package com.example.spring_mvc_study1_servlet.web.springmvc.v3;

import com.example.spring_mvc_study1_servlet.domain.Member.Member;
import com.example.spring_mvc_study1_servlet.domain.Member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    // method를 지정해주면서, 이 함수는 GET 요청인 경우에만 호출
    // 이전에는 value만 맞으면 GET, POST 등 어떤 요청이어서 호출되었음
    @GetMapping("/new-form") // 위의 코드와 완전 동일한 기능
    public String newForm() {
        return "new-form";
    }

    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save") // 위의 코드와 완전 동일한 기능
    // requset, response 없이 "username" 자체를 Param으로 String 변수로 받을 수 있음
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        // String username = request.getParameter("username");
        // int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        // ModelAndView mav = new ModelAndView("save-result");
        // mav.addObject("member", member);

        return "save-result";
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping // 위의 코드와 완전 동일한 기능
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        // ModelAndView mav = new ModelAndView("members");
        // mav.addObject("members", members);

        model.addAttribute("members", members);

        return "members";
    }

}

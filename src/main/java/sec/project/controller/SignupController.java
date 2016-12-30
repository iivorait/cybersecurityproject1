package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam String creditcard, @RequestParam String redirect) {
        signupRepository.save(new Signup(name, address, creditcard));
        return "redirect:" + redirect;
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String done() {
        return "done";
    }

    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public String showParticipants() {
        return "participants";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchParticipants(Model model, @RequestParam String name) {
        model.addAttribute("signups", signupRepository.findParticipants(name));
        return "search";
    }
}

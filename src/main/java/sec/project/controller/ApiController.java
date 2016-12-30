package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@RestController
@RequestMapping("rest")
public class ApiController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(method = RequestMethod.POST)
    public List<Signup> getParticipantsJSON() {
        return signupRepository.findAll();
    }
}

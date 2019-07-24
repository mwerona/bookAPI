package searchAPI.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import searchAPI.book.entity.User;

@Controller
public class LoginController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/logIn")
	public String Login() {
		return "login.html";
	}
	

	@RequestMapping("/signIn")
	public String signin(@ModelAttribute User user, BindingResult bindingResult) {
		User userExists = service.findByUserId(user.getUserId());
		if(userExists!=null && service.matchPassword(user.getPassword(), userExists.getPassword())) {
			return "redirect:/";
		}
		bindingResult.rejectValue("userId", "error.user", "Failed log in. Please check your Id and Password.");
		return "redirect:/logIn";
	}
	
	@RequestMapping("/register")
	public String regitster(@ModelAttribute User user, BindingResult bindingResult) {
		User userExists = service.findByUserId(user.getUserId());
        if (userExists != null) {
            bindingResult.rejectValue("userId", "error.user",
                            "There is already a user registered with this ID");
    		return "redirect:/logIn";
        }else {
    		service.save(user);
    		return "redirect:/";
        }
	}
}

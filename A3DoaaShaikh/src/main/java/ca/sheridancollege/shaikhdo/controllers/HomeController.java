

package ca.sheridancollege.shaikhdo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.shaikhdo.beans.User;
import ca.sheridancollege.shaikhdo.database.DatabaseAccess;

@Controller

public class HomeController {

	@Autowired
	@Lazy
	private DatabaseAccess da;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/secure")
	public String secureIndex(Authentication authentication, Model model) {
		String email = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("email", email);
		model.addAttribute("roleList", roleList);
		return "secure/index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);

		Long userId = da.findUserAccount(username).getUserId();
		da.addRole(userId, Long.valueOf(2));

		return "redirect:/";
	}

	@GetMapping("/secure/ui-discussion")
	public String secureUIDiscussion(Model model) {
		model.addAttribute("userComment", new User());

		model.addAttribute("getUserCommentList", da.getUserCommentList());
		return "secure/ui-discussion";
	}

	@PostMapping("/insertPost")
	public String insertPost(Model model, @ModelAttribute User userComment) {
		da.insertPost(userComment);
		model.addAttribute("userComment", new User());
		model.addAttribute("getUserCommentList", da.getUserCommentList());
		return "secure/ui-discussion";
	}

}

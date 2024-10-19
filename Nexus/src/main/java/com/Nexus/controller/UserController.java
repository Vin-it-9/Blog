package com.Nexus.controller;

import java.security.Principal;
import java.util.Base64;

import com.Nexus.service.BlogServiceImpl;
import com.Nexus.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Nexus.entity.User;
import com.Nexus.repository.UserRepo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

	@Autowired
	private BlogServiceImpl blogServiceImpl;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

	@GetMapping("/profile")
	public String profile(Principal principal, HttpSession session, Model model) {

		String email = principal.getName();

		long blogCount = blogServiceImpl.countBlogsByAuthorEmail(email);
		session.setAttribute("blogCount", blogCount);
		model.addAttribute("blogCount", blogCount);

		return "profile";
	}

	@GetMapping("/change-password")
	public String showChangePasswordForm() {
		return "change-password";
	}

	@PostMapping("/change-password")
	public String changePassword(@AuthenticationPrincipal UserDetails userDetails,
								 @RequestParam String currentPassword,
								 @RequestParam String newPassword,
								 @RequestParam String confirmPassword,
								 Model model,
								 RedirectAttributes redirectAttributes) {

		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("error", "New passwords do not match.");
			return "change-password";
		}

		boolean isChanged = userServiceImpl.changePasswordByEmail(userDetails.getUsername(), currentPassword, newPassword);

		if (isChanged) {
			redirectAttributes.addFlashAttribute("message", "Password changed successfully!");
			return "redirect:/user/change-password";
		} else {
			model.addAttribute("error", "Current password is incorrect.");
			return "change-password";
		}
	}

}

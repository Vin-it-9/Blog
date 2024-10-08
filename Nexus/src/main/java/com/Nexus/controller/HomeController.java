package com.Nexus.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.Nexus.entity.Blog;
import com.Nexus.entity.Image;
import com.Nexus.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import com.Nexus.entity.User;
import com.Nexus.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private ImageServiceImpl imageServiceImpl;

	@Autowired
	private BlogService blogService;

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

//	@GetMapping("/")
//	public String index(Principal principal, HttpSession session, Model model) throws IOException, SQLException {
//
//		if (principal != null && session.getAttribute("userImage") == null) {
//
//
//			String email = principal.getName();
//
//			Image userImage = imageServiceImpl.findByUserEmail(email);
//			if (userImage != null && userImage.getImage() != null) {
//				byte[] imageBytes = userImage.getImage().getBytes(1, (int) userImage.getImage().length());
//				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//				session.setAttribute("userImage", base64Image);
//			}
//
//			long blogCount = blogServiceImpl.countBlogsByAuthorEmail(email);
//			session.setAttribute("blogCount", blogCount);
//
//		}
//
//		List<Blog> blogs = blogService.getAllBlogs();
//		Collections.reverse(blogs);
//		model.addAttribute("blogs", blogs);
//
//		return "index";
//	}

	@GetMapping("/")
	public String index(Principal principal, HttpSession session, Model model) throws IOException, SQLException {

		if (principal != null && session.getAttribute("userImage") == null) {

			String email = principal.getName();

			Image userImage = imageServiceImpl.findByUserEmail(email);
			if (userImage != null && userImage.getImage() != null) {
				byte[] imageBytes = userImage.getImage().getBytes(1, (int) userImage.getImage().length());
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				session.setAttribute("userImage", base64Image);
			}

		}

		List<Blog> blogs = blogService.getAllBlogs();
		Collections.reverse(blogs);
		model.addAttribute("blogs", blogs);

		return "index";
	}


	@GetMapping("/myBlogs")
	public String listUserBlogs(Model model, Principal principal) {
		String email = principal.getName();
		List<Blog> userBlogs = blogServiceImpl.getBlogsByEmail(email);
		Collections.reverse(userBlogs);
		model.addAttribute("blogs", userBlogs);
		return "myBlogs";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {

		String url = request.getRequestURL().toString();

		url = url.replace(request.getServletPath(), "");

		User savedUser = userService.saveUser(user, url);

		if (savedUser == null) {
			session.setAttribute("msg", "Email already exists. Please use a different email or login.");
			return "redirect:/register";
		}
		session.setAttribute("msg", "Registered successfully! Please check your email to verify your account.");
		return "redirect:/signin";
	}


	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model m) {

		boolean f = userService.verifyAccount(code);

		if (f) {
			m.addAttribute("msg", "Sucessfully your account is verified");
		} else {
			m.addAttribute("msg", "may be your vefication code is incorrect or already veified ");
		}

		return "message";
	}

	@GetMapping("/editProfile")
	public String showEditProfilePage(Model model, Principal principal) {

		String email = principal.getName();
		User user = userRepo.getUserByEmail(email);

		model.addAttribute("user", user);

		return "editProfile";

	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user, Principal principal, HttpSession session) {

		String email = principal.getName();

		boolean isUpdated = userServiceImpl.updateUserProfile(user, email);

		if (isUpdated) {
			session.setAttribute("msg", "Profile updated successfully.");
		} else {
			session.setAttribute("msg", "Error updating profile.");
		}

		return "redirect:/editProfile";
	}

	@PostMapping("/delete/{id}")
	public String deleteBlog(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {

		String userEmail = principal.getName();

		boolean isDeleted = blogServiceImpl.deleteBlog(id, userEmail);

		if (isDeleted) {
			redirectAttributes.addFlashAttribute("message", "Blog deleted successfully.");
		} else {
			redirectAttributes.addFlashAttribute("error", "You are not authorized to delete this blog.");
		}

		return "redirect:/myBlogs";
	}

}

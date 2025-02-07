package com.Nexus.controller;

import com.Nexus.entity.*;
import com.Nexus.service.*;
import com.Nexus.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BlogServiceImpl blogServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentServiceImpl commentServiceImpl;


    @GetMapping("/add")
    public String addBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "addBlog";
    }

    @PostMapping("/add")
    public String addBlog(@RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("image") MultipartFile file,
                          Principal principal) throws IOException, SQLException {

        String email = principal.getName();

        byte[] imageBytes = null;

        if (!file.isEmpty()) {
            imageBytes = file.getBytes();
        }

        Blog newBlog = blogService.createBlogWithImage(title, content, imageBytes, email);

        return "redirect:/" ;

    }

//    @GetMapping("/list")
//    public String listAllBlogs(Model model) {
//
//        List<Blog> blogs = blogService.getAllBlogs();
//        Collections.reverse(blogs);
//        model.addAttribute("blogs", blogs);
//
//        return "index";
//
//    }

    @GetMapping("/list")
    public String listAllBlogs(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        // Collections.reverse(blogs);
        List<BlogDTO> blogDTOs = new ArrayList<>();
        for (Blog blog : blogs) {
            List<CommentDTO> comments = commentService.getCommentsByBlogId(blog.getId());
            blogDTOs.add(new BlogDTO(blog, comments));
        }

        model.addAttribute("blogs", blogDTOs);
        return "index";
    }


    @GetMapping("/displayImage/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable("id") long id) throws IOException, SQLException {
        Blog blog = blogService.getBlogById(id);
        Blob imageBlob = blog.getImage();
        byte[] imageBytes = null;

        if (imageBlob != null) {
            imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }


    @GetMapping("/profileImage/{email}")
    public ResponseEntity<byte[]> displayProfileImage(@PathVariable("email") String email) throws IOException, SQLException {

        User user = userRepo.getUserByEmail(email);

        if (user == null || user.getImage() == null) {
            byte[] defaultImage = getDefaultImage();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(defaultImage);
        }

        Blob imageBlob = user.getImage().getImage();
        byte[] imageBytes = null;

        if (imageBlob != null) {
            imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
        } else {
            imageBytes = getDefaultImage();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    private byte[] getDefaultImage() throws IOException {

        InputStream defaultImageStream = getClass().getResourceAsStream("/static/default-profile.png");

        if (defaultImageStream != null) {
            return defaultImageStream.readAllBytes();
        } else {
            return new byte[0];
        }
    }

    @PostMapping("/comments/add/{blogId}")
    public String addComment(@PathVariable("blogId") Long blogId,
                                             @RequestParam("content") String content,
                                             Authentication authentication) {
        Blog blog = blogServiceImpl.getBlogById(blogId);
        String currentUserEmail = authentication.getName();

        User user = userServiceImpl.getUserByEmail(currentUserEmail);
        Comment comment = new Comment();

        comment.setBlog(blog);
        comment.setUser(user);
        comment.setContent(content);

        commentService.addCommentToBlog(blogId, currentUserEmail, content);

        return "redirect:/" ;

    }










}

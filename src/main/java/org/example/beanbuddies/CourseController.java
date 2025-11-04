package org.example.beanbuddies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String price,
            Model model) {

        List<Course> courses = courseService.filterCourses(search, category, level, price);

        model.addAttribute("courses", courses);
        model.addAttribute("courseCount", courses.size());
        model.addAttribute("searchQuery", search != null ? search : "");
        model.addAttribute("selectedCategory", category != null ? category : "All Categories");
        model.addAttribute("selectedLevel", level != null ? level : "All Levels");
        model.addAttribute("selectedPrice", price != null ? price : "All Prices");

        return "index";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("inWishlist", courseService.isCourseInWishlist(id));
            return "course-detail";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String rememberMe,
            Model model) {

        // Enhanced validation
        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("error", "Email is required");
            return "login";
        }

        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Password is required");
            return "login";
        }

        // Basic email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            model.addAttribute("error", "Please enter a valid email address");
            return "login";
        }

        // Simple authentication (you would normally use Spring Security with database)
        // For demo purposes, accepting any valid email/password combination
        if (password.length() >= 6) {
            // Simulate successful login
            String userName = email.substring(0, email.indexOf("@"));

            // Handle remember me functionality (in real app, you'd set cookies/session)
            if (rememberMe != null) {
                model.addAttribute("message", "Welcome back, " + userName + "! (You will be remembered)");
            } else {
                model.addAttribute("message", "Welcome back, " + userName + "!");
            }

            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password. Password must be at least 6 characters.");
            return "login";
        }
    }

    @PostMapping("/signup")
    public String processSignup(
            @RequestParam String fullName,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false) String[] institution,
            Model model) {

        // Check if institution is selected (mandatory)
        if (institution == null || institution.length == 0) {
            model.addAttribute("error", "Please select at least one educational institution.");
            return "signup";
        }

        if (password.equals(confirmPassword)) {
            // Process institution selection
            String selectedInstitutions = String.join(", ", institution);

            model.addAttribute("message", "Welcome to BeanBuddies, " + fullName + "! Username: " + username +
                    ", Institutions: " + selectedInstitutions);
            return "redirect:/";
        }
        model.addAttribute("error", "Passwords do not match");
        return "signup";
    }
}

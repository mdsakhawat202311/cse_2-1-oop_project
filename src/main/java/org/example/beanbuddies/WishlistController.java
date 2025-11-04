package org.example.beanbuddies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WishlistController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        model.addAttribute("wishlist", courseService.getWishlist());
        return "wishlist";
    }

    @PostMapping("/wishlist/add")
    public String addToWishlist(@RequestParam Long courseId) {
        courseService.addToWishlist(courseId);
        return "redirect:/course/" + courseId;
    }

    @PostMapping("/wishlist/remove")
    public String removeFromWishlist(@RequestParam Long courseId) {
        courseService.removeFromWishlist(courseId);
        return "redirect:/wishlist";
    }
}


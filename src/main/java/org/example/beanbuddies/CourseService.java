package org.example.beanbuddies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    private final List<Course> wishlist = new ArrayList<>();

    public CourseService() {
        initializeCourses();
    }

    private void initializeCourses() {
        courses.add(new Course(1L, "Java Programming Masterclass", "Tim Buchalka", "80h 30m", "Beginner",
                "Learn Java programming from scratch. Master Java 17, object-oriented programming, data structures, and more.",
                "https://img-c.udemycdn.com/course/750x422/533682_c10c_4.jpg",
                "https://www.youtube.com/embed/grEKMHGYyns", 89.99, 4.6, 256430, "App Development", "https://i.pravatar.cc/150?u=TimBuchalka"));

        courses.add(new Course(2L, "The Complete Web Development Bootcamp", "Angela Yu", "60h 15m", "Beginner",
                "Become a full-stack web developer with just one course. HTML, CSS, JavaScript, Node, React, MongoDB, and more!",
                "https://img-c.udemycdn.com/course/750x422/1565838_e54e_18.jpg",
                "https://www.youtube.com/embed/q0_g2-A2qgE", 94.99, 4.7, 182340, "Web Development", "https://i.pravatar.cc/150?u=AngelaYu"));

        courses.add(new Course(3L, "Advanced JavaFX UI Development", "Jane Smith", "35h 20m", "Intermediate",
                "Build modern desktop applications with JavaFX, FXML, CSS styling, and popular UI libraries.",
                "https://img-c.udemycdn.com/course/750x422/2654286_8217.jpg",
                "https://www.youtube.com/embed/d22-5d2q_sM", 79.99, 4.5, 45230, "App Development", "https://i.pravatar.cc/150?u=JaneSmith"));

        courses.add(new Course(4L, "Spring Boot Microservices", "John Thompson", "45h 0m", "Advanced",
                "Master Spring Boot and microservices architecture. Build scalable REST APIs with Spring Cloud.",
                "https://img-c.udemycdn.com/course/750x422/2132538_a331_5.jpg",
                "https://www.youtube.com/embed/MSAaIpr22MA", 99.99, 4.8, 89450, "Web Development", "https://i.pravatar.cc/150?u=JohnThompson"));

        courses.add(new Course(5L, "Python for Data Science", "Jose Portilla", "52h 30m", "Intermediate",
                "Learn Python for data analysis, visualization, and machine learning with NumPy, Pandas, and Matplotlib.",
                "https://img-c.udemycdn.com/course/750x422/903744_8eb2.jpg",
                "https://www.youtube.com/embed/woVJ4N54vB8", 84.99, 4.6, 123890, "Data Science", "https://i.pravatar.cc/150?u=JosePortilla"));

        courses.add(new Course(6L, "React - The Complete Guide", "Maximilian Schwarzmüller", "48h 15m", "Intermediate",
                "Master React 18+ with hooks, Redux, React Router, Next.js, and build amazing modern web apps.",
                "https://img-c.udemycdn.com/course/750x422/1362070_b9a1_2.jpg",
                "https://www.youtube.com/embed/SqcY0GlETPk", 89.99, 4.7, 198760, "Web Development", "https://i.pravatar.cc/150?u=MaximilianSchwarzmuller"));

        courses.add(new Course(7L, "Machine Learning A-Z", "Kirill Eremenko", "44h 0m", "Advanced",
                "Learn to create Machine Learning Algorithms in Python and R from industry experts.",
                "https://img-c.udemycdn.com/course/750x422/950390_270f_3.jpg",
                "https://www.youtube.com/embed/1-b_GGQ-08I", 94.99, 4.5, 167230, "Data Science", "https://i.pravatar.cc/150?u=KirillEremenko"));

        courses.add(new Course(8L, "AWS Certified Solutions Architect", "Stephane Maarek", "27h 45m", "Intermediate",
                "Pass the AWS Solutions Architect Associate exam. Learn AWS cloud computing and architecture.",
                "https://img-c.udemycdn.com/course/750x422/3623292_2c9a_8.jpg",
                "https://www.youtube.com/embed/Ia-UEYYR44s", 79.99, 4.7, 145670, "Cloud Computing", "https://i.pravatar.cc/150?u=StephaneMaarek"));

        courses.add(new Course(9L, "The Complete SQL Bootcamp", "Jose Portilla", "9h 0m", "Beginner",
                "Master SQL database queries with PostgreSQL. Perfect for data analysis and backend development.",
                "https://img-c.udemycdn.com/course/750x422/762616_4621_5.jpg",
                "https://www.youtube.com/embed/w-n_y6KAl1w", 69.99, 4.6, 89340, "Web Development", "https://i.pravatar.cc/150?u=JosePortillaSQL"));

        courses.add(new Course(10L, "Docker and Kubernetes", "Stephen Grider", "22h 30m", "Advanced",
                "Build, test, and deploy Docker applications with Kubernetes while learning production-best practices.",
                "https://img-c.udemycdn.com/course/750x422/1793828_7995_3.jpg",
                "https://www.youtube.com/embed/3c-iBn73dDE", 84.99, 4.8, 76540, "Cloud Computing", "https://i.pravatar.cc/150?u=StephenGrider"));

        courses.add(new Course(11L, "iOS App Development Bootcamp", "Angela Yu", "55h 30m", "Beginner",
                "Learn iOS app development with Swift 5 and build real apps including a Pokemon clone.",
                "https://img-c.udemycdn.com/course/750x422/1778502_f436_11.jpg",
                "https://www.youtube.com/embed/APqI6J8BB6o", 94.99, 4.7, 134560, "App Development", "https://i.pravatar.cc/150?u=AngelaYuiOS"));

        courses.add(new Course(12L, "Ethical Hacking from Scratch", "Zaid Sabih", "15h 30m", "Intermediate",
                "Learn penetration testing, ethical hacking, and cybersecurity from scratch with practical examples.",
                "https://img-c.udemycdn.com/course/750x422/857010_8239_2.jpg",
                "https://www.youtube.com/embed/2a2o-V2df4E", 89.99, 4.5, 98760, "Cyber Security", "https://i.pravatar.cc/150?u=ZaidSabih"));

        courses.add(new Course(13L, "Competitive Programming Masterclass", "William Fiset", "35h 0m", "Advanced",
                "Master algorithms and data structures for competitive programming. Prepare for ICPC, Google Code Jam, and more.",
                "https://img-c.udemycdn.com/course/750x422/2132538_a331_5.jpg",
                "https://www.youtube.com/embed/u40g9p52s-E", 79.99, 4.8, 45670, "Competitive Programming", "https://i.pravatar.cc/150?u=WilliamFiset"));
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> filterCourses(String search, String category, String level, String priceRange) {
        return courses.stream()
                .filter(course -> {
                    boolean matchesSearch = search == null || search.isEmpty() ||
                            course.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                            course.getInstructor().toLowerCase().contains(search.toLowerCase()) ||
                            course.getDescription().toLowerCase().contains(search.toLowerCase());

                    boolean matchesCategory = category == null || category.isEmpty() ||
                            category.equals("All Categories") || course.getCategory().equals(category);

                    boolean matchesLevel = level == null || level.isEmpty() ||
                            level.equals("All Levels") || course.getLevel().equals(level);

                    boolean matchesPrice = true;
                    if (priceRange != null && !priceRange.isEmpty() && !priceRange.equals("All Prices")) {
                        double coursePrice = course.getPrice();
                        matchesPrice = switch (priceRange) {
                            case "Under $75" -> coursePrice < 75;
                            case "$75 - $90" -> coursePrice >= 75 && coursePrice <= 90;
                            case "Over $90" -> coursePrice > 90;
                            case "Free" -> coursePrice == 0;
                            default -> true;
                        };
                    }

                    return matchesSearch && matchesCategory && matchesLevel && matchesPrice;
                })
                .collect(Collectors.toList());
    }

    public Course getCourseById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Course> getWishlist() {
        return new ArrayList<>(wishlist);
    }

    public void addToWishlist(Long courseId) {
        if (wishlist.stream().noneMatch(c -> c.getId().equals(courseId))) {
            Course course = getCourseById(courseId);
            if (course != null) {
                wishlist.add(course);
            }
        }
    }

    public void removeFromWishlist(Long courseId) {
        wishlist.removeIf(course -> course.getId().equals(courseId));
    }

    public boolean isCourseInWishlist(Long courseId) {
        return wishlist.stream().anyMatch(c -> c.getId().equals(courseId));
    }
}

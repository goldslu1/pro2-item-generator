package com.ItemGenerator.ItemGenerator.controller;

import com.ItemGenerator.ItemGenerator.model.*;
import com.ItemGenerator.ItemGenerator.repository.ItemRepository;
import com.ItemGenerator.ItemGenerator.repository.MaterialRepository;
import com.ItemGenerator.ItemGenerator.repository.TypeRepository;
import com.ItemGenerator.ItemGenerator.repository.UserRepository;
import com.ItemGenerator.ItemGenerator.service.ItemService;
import com.ItemGenerator.ItemGenerator.service.TypeService;
import com.ItemGenerator.ItemGenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping({"", "/", "/home"})
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> listUsers = userRepository.findAll();
        List<Item> listItems = itemRepository.findAll();

        model.addAttribute("listUsers", listUsers);
        model.addAttribute("materials", new Material());
        model.addAttribute("types", new Type());
        model.addAttribute("listItems", listItems);

        return "admin";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("USER");
        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/generator")
    public String generator(Model model) {

        return "generator";
    }

    @PostMapping("/generate_item")
    public String generateItem(Item item) {
        Material randomMaterial = itemService.getRandomMaterial();
        Type randomType = typeService.getRandomType();

        item.setName(randomMaterial.getName() + " " + randomType.getName());
        item.setType(randomType.getName());
        item.setMaterial(randomMaterial.getName());
        item.setDurability(randomMaterial.getDurability());

        itemRepository.save(item);

        return "redirect:/generator";
    }

    @PostMapping("/add_material") //funguje
    public String addMaterial(Material material) {
        materialRepository.save(material);
        return "redirect:/admin";
    }

    @PostMapping("/add_type")
    public String addType(Type type) {
        if (typeService.ifExists(type.getName())) {
            return "redirect:/error";
        } else {
            typeRepository.save(type);
        }

        return "redirect:/admin";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
            if (userRepository.existsById(id)){
                userRepository.deleteById(id);
                return "redirect:/admin";
            }
            else {
                return "redirect:/register";
            }
    }

    @PostMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        if (itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return "redirect:/admin";
        } else {
            return "redirect:/generate";
        }

    }

    @PostMapping("/newWeapon")
    public String newWeapon(Weapon weapon) {

        return "redirect:/generate";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }
}

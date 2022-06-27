package com.example.finalexam.web;

import com.example.finalexam.model.entity.enums.StyleEnum;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.service.SongService;
import com.example.finalexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HttpSession httpSession;
    private final SongService songService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeController(HttpSession httpSession,
                          SongService songService,
                          UserService userService, ModelMapper modelMapper) {
        this.httpSession = httpSession;
        this.songService = songService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);

        model.addAttribute("currentUser", userService.findByUsername(currentUser.getUsername()));
        model.addAttribute("totalDurationOfPlaylist", userService.getTotalDuration(currentUser));
        model.addAttribute("userSongs", userService.currentPlaylist(currentUser));
        model.addAttribute("popSongs", songService.findAllByStyleName(StyleEnum.POP));
        model.addAttribute("rockSong", songService.findAllByStyleName(StyleEnum.ROCK));
        model.addAttribute("jazzSong", songService.findAllByStyleName(StyleEnum.JAZZ));

        return "home";
    }
}

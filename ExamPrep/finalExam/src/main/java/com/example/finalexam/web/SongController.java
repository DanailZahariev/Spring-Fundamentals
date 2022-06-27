package com.example.finalexam.web;

import com.example.finalexam.model.binding.AddSongBindingModel;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {


    private final HttpSession httpSession;
    private final ModelMapper modelMapper;
    private final SongService songService;


    public SongController(HttpSession httpSession,
                          ModelMapper modelMapper,
                          SongService songService) {
        this.httpSession = httpSession;
        this.modelMapper = modelMapper;
        this.songService = songService;

    }

    @GetMapping("/add")
    public String add() {
        if (httpSession.getAttribute("user") != null) {
            return "song-add";
        }
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addSong(@Valid AddSongBindingModel addSongBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addSongBindingModel", addSongBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongBindingModel", bindingResult);
            return "redirect:add";
        }

        SongServiceModel songServiceModel = modelMapper.map(addSongBindingModel, SongServiceModel.class);
        songService.addSong(songServiceModel);

        return "redirect:/";
    }

    @GetMapping("/add/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {
        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        songService.saveSongToPlaylist(id, currentUser);
        return "redirect:/";
    }

    @GetMapping("/remove-all")
    public String removeAllSongs() {
        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        songService.removeAllFromPlaylist(currentUser);
        return "redirect:/";
    }

    @ModelAttribute
    public AddSongBindingModel addSongBindingModel() {
        return new AddSongBindingModel();
    }
}

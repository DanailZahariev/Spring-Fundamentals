package com.example.musicdb.web;

import com.example.musicdb.model.binding.AlbumAddBidingModel;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.model.service.UserServiceModel;
import com.example.musicdb.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public AlbumController(AlbumService albumService,
                           ModelMapper modelMapper,
                           HttpSession httpSession) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String add() {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "add-album";
    }


    @PostMapping("/add")
    public String addAlbum(@Valid AlbumAddBidingModel albumAddBidingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBidingModel", albumAddBidingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBidingModel", bindingResult);
            return "redirect:add";
        }

        AlbumServiceModel albumServiceModel = modelMapper.map(albumAddBidingModel, AlbumServiceModel.class);
        UserServiceModel userServiceModel = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        albumService.addAlbum(albumServiceModel, userServiceModel);

        return "redirect:/";
    }

    @ModelAttribute
    public AlbumAddBidingModel albumAddBidingModel() {
        return new AlbumAddBidingModel();
    }
}

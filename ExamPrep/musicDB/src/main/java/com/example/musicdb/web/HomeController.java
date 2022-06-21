package com.example.musicdb.web;

import com.example.musicdb.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HttpSession httpSession;
    private final AlbumService albumService;

    public HomeController(HttpSession httpSession,
                          AlbumService albumService) {
        this.httpSession = httpSession;
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalCopies", albumService.getTotalCopies());
        model.addAttribute("allAlbums", albumService.findAllAlbums());

        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return "redirect:/";
    }
}

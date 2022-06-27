package com.example.finalexam.service;

import com.example.finalexam.model.entity.Song;
import com.example.finalexam.model.entity.Style;
import com.example.finalexam.model.entity.User;
import com.example.finalexam.model.entity.enums.StyleEnum;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.model.view.SongViewModel;
import com.example.finalexam.repository.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper modelMapper;

    public SongService(SongRepository songRepository,
                       UserService userService, StyleService styleService,
                       ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.userService = userService;
        this.styleService = styleService;
        this.modelMapper = modelMapper;
    }

    public void addSong(SongServiceModel songServiceModel) {

        Song song = modelMapper.map(songServiceModel, Song.class);
        Style styleName = styleService.findByStyleName(songServiceModel.getStyle());
        song.setStyle(styleName);

        songRepository.save(song);
    }

    public List<SongViewModel> findAllByStyleName(StyleEnum styleEnum) {
        return songRepository.findAllByStyleStyleName(styleEnum).stream()
                .map(style -> modelMapper.map(style, SongViewModel.class))
                .collect(Collectors.toList());
    }

    public void saveSongToPlaylist(Long id, UserServiceModel currentUser) {
        Song song = songRepository.findById(id).orElse(null);
        User user = userService.findById(currentUser);

        if (user.getSongs().contains(song)) {
            return;
        }

        user.getSongs().add(song);
        userService.saveUserSong(user);
    }

    public void removeAllFromPlaylist(UserServiceModel currentUser) {
        User user = userService.findById(currentUser);
        user.setSongs(new ArrayList<>());
        userService.saveUserSong(user);
    }
}

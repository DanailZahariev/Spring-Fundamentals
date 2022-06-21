package com.example.musicdb.service;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.model.service.UserServiceModel;
import com.example.musicdb.model.view.AlbumViewModel;
import com.example.musicdb.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AlbumService(AlbumRepository albumRepository,
                        ArtistService artistService,
                        UserService userService,
                        ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void addAlbum(AlbumServiceModel albumServiceModel, UserServiceModel userServiceModel) {

        User user = userService.findByUsername(userServiceModel.getUsername());
        Artist artist = artistService.findByArtistName(albumServiceModel.getArtist());
        Album album = modelMapper.map(albumServiceModel, Album.class);

        album.setAddedFrom(user).setArtist(artist);

        albumRepository.save(album);

    }

    public Integer getTotalCopies() {
        return albumRepository.findAll().stream()
                .mapToInt(Album::getCopies).sum();
    }

    public List<AlbumViewModel> findAllAlbums() {
        return albumRepository.findAll().stream().map(album -> {
            AlbumViewModel albums = modelMapper.map(album, AlbumViewModel.class);
            albums.setArtist(album.getArtist().getName().name());
            albums.setGenre(albums.getGenre());
            return albums;
        }).collect(Collectors.toList());
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}

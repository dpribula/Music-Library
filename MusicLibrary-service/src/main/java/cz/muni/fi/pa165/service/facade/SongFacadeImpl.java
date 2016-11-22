/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.api.SongFacade;
import cz.muni.fi.pa165.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.api.dto.SongDTO;
import cz.muni.fi.pa165.entity.Song;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.SongService;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Kulisek
 */
@Service
@Transactional
public class SongFacadeImpl implements SongFacade {

    final static Logger LOG = LoggerFactory.getLogger(SongFacadeImpl.class);

    @Inject
    private SongService songService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createSong(SongCreateDTO s) {
        Song mappedSong = beanMappingService.mapTo(s, Song.class);
        //mappedSong.setAlbum(s.getAlbumId());
        mappedSong.setBitrate(s.getBitrate());
        mappedSong.setCommentary(s.getCommentary());
        //mappedSong.setGenre(s.getGenreId());
        //mappedSong.setMusician(s.getMusicianId());
        mappedSong.setPosition(s.getPosition());
        mappedSong.setTitle(s.getTitle());
        Song newSong = songService.create(mappedSong);
		return newSong.getId();
    }

    @Override
    public void deleteSong(Long id) {
        songService.delete(songService.findById(id));
    }

    @Override
    public List<SongDTO> getAllSongs() {
        return beanMappingService.mapTo(songService.findAll(), SongDTO.class);
    }

    @Override
    public List<SongDTO> getSongsByMusicianName(String musicianName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SongDTO getSongById(Long id) {
        Song song = songService.findById(id);
        return (song == null) ? null : beanMappingService.mapTo(song, SongDTO.class);
    }

}
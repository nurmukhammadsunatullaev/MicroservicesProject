package com.epam.microservice.resourceprocessor.service;

import com.epam.microservice.resourceprocessor.model.ResourceModel;
import com.epam.microservice.resourceprocessor.model.SongModel;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessorService {

    private final SongService songService;

    public Optional<SongModel> extractFile(ResourceModel resource){
        try(InputStream input = new FileInputStream(resource.getPath())) {
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            parser.parse(input, new DefaultHandler(), metadata, new ParseContext());

            SongModel songModel = SongModel.builder()
                    .artist(metadata.get("xmpDM:artist"))
                    .album(metadata.get("xmpDM:album"))
                    .name(metadata.get("dc:title"))
                    .year(metadata.get("xmpDM:releaseDate"))
                    .length(metadata.get("xmpDM:duration"))
                    .resourceId(resource.getId())
                    .build();

            return songService.postSong(songModel);

        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

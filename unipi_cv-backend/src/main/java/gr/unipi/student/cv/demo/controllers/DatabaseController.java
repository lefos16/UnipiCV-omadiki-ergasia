package gr.unipi.student.cv.demo.controllers;

import gr.unipi.student.cv.demo.model.LanguageEntity;
import gr.unipi.student.cv.demo.model.PlatformEntity;
import gr.unipi.student.cv.demo.model.KeywordEntity;
import gr.unipi.student.cv.demo.repository.LanguageRepository;
import gr.unipi.student.cv.demo.repository.PlatformKeywordRepository;
import gr.unipi.student.cv.demo.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseController {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    PlatformRepository platformRepository;
    @Autowired
    PlatformKeywordRepository platformKeywordRepository;

    @RequestMapping(value = "/languages",method = RequestMethod.GET)
    public ResponseEntity<?> getLanguages(){
        List<LanguageEntity> languageEntities = languageRepository.findAll();
        return new ResponseEntity<List<LanguageEntity>>(languageEntities, HttpStatus.OK);
    }
    @RequestMapping(value = "/platforms",method = RequestMethod.GET)
    public ResponseEntity<?> getPlatforms(){
        List<PlatformEntity> platformEntities = platformRepository.findAll();
        return new ResponseEntity<List<PlatformEntity>>(platformEntities, HttpStatus.OK);
    }
    @RequestMapping(value = "/keywords",method = RequestMethod.GET)
    public ResponseEntity<?> getKeyword(){
        List<KeywordEntity> platformKeywordEntities = platformKeywordRepository.findAll();
        return new ResponseEntity<List<KeywordEntity>>(platformKeywordEntities, HttpStatus.OK);
    }
}

package io.slaweksApp.KorVocab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    @Autowired
    VocabService vocabService;

    public DataLoader() {

    }


//    public Vocab findVocab(long id) throws ChangeSetPersister.NotFoundException {
//        return vocabService.getVocab(id);
//    }
//
//    public long randomNbr( ) {
//        int size = vocabService.getAllVocabs().size();
//        Random random = new Random();
//        long randomNbr =  random.nextInt(1, size ) + 1;
//        System.out.println(randomNbr);
//        return randomNbr;
//    }
//












}

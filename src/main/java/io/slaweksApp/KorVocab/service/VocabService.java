package io.slaweksApp.KorVocab.service;

import io.slaweksApp.KorVocab.model.Vocab;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface VocabService {

    Vocab getVocab(Long id) throws ChangeSetPersister.NotFoundException;
    Vocab selectVocab(Long id) throws ChangeSetPersister.NotFoundException;
    Vocab createVocab (Vocab newVocab);
    ArrayList<Vocab> getAllVocabs();
    Vocab updateVocab (Vocab updateVocab) throws ChangeSetPersister.NotFoundException;
    void deleteVocab (Long id);






}

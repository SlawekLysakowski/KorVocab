package io.slaweksApp.KorVocab.service.impl;


import io.slaweksApp.KorVocab.dao.VocabDao;
import io.slaweksApp.KorVocab.model.Vocab;
import io.slaweksApp.KorVocab.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class VocabServiceImpl implements VocabService {
    @Autowired
    private VocabDao dao;

    @Override
    public Vocab getVocab(Long id) throws ChangeSetPersister.NotFoundException {
        return dao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Vocab selectVocab(Long id) throws ChangeSetPersister.NotFoundException {
        return dao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Vocab createVocab(Vocab newVocab) {
        return dao.save(newVocab);
    }

    @Override
    public ArrayList<Vocab> getAllVocabs() {
        ArrayList<Vocab> vocabArrayList = new ArrayList<>(dao.findAll());
        return vocabArrayList;
    }

    @Override
    public Vocab updateVocab(Vocab updateVocab) throws ChangeSetPersister.NotFoundException {
        Vocab existing = getVocab(updateVocab.getId());
        return dao.save(updateVocab);
    }

    @Override
    public void deleteVocab(Long id) {
        dao.deleteById(id);
    }
}

package io.slaweksApp.KorVocab.dao;

import io.slaweksApp.KorVocab.model.Vocab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabDao extends JpaRepository<Vocab, Long> {


}

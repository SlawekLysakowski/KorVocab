package io.slaweksApp.KorVocab.controller;


import io.slaweksApp.KorVocab.model.Vocab;
import io.slaweksApp.KorVocab.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequestMapping("")
public class VocabController {

    @Autowired
    VocabService vocabService;

    public Vocab findVocab(long id) throws ChangeSetPersister.NotFoundException {
        return vocabService.getVocab(id);
    }

    @GetMapping("/get")
    public String getVocabView(@RequestParam Long id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("vocab", vocabService.getVocab(id));
        return "get-vocab";
    }

    @GetMapping("/select")
    public String selectVocab(@RequestParam Long id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("select", vocabService.getVocab(id));
        return "redirect:/vocab/list";
    }

    @GetMapping("/about")
    public String getAboutView() {
        return "about-vocab";
    }

    @GetMapping("/list")
    public String getAllWordsView(Model model, Vocab vocab) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("vocabs", vocabService.getAllVocabs());
        return "list-vocab";
    }

    @GetMapping("/test")
    public String testView(Model model, Vocab vocab, VocabController controller) throws ChangeSetPersister.NotFoundException {
        ArrayList<Vocab> allVocabs = vocabService.getAllVocabs();
        int size = vocabService.getAllVocabs().size();

        ArrayList<String> japaneseWords = new ArrayList<String>();
        ArrayList<String> koreanWords = new ArrayList<String>();
        ArrayList<String> englishWords = new ArrayList<String>();
        ArrayList<String> polishWords = new ArrayList<String>();

        model.addAttribute("vocabs", vocabService.getAllVocabs());
        model.addAttribute("size", size);

        for (Vocab v : allVocabs) {
            japaneseWords.add(v.getJapanese());
            koreanWords.add(v.getKorean());
            englishWords.add(v.getEnglish());
            polishWords.add(v.getPolish());
        }

        model.addAttribute("japaneseWords", japaneseWords);
        model.addAttribute("koreanWords", koreanWords);
        model.addAttribute("englishWords", englishWords);
        model.addAttribute("polishWords", polishWords);
        return "test-vocab";
    }

        @GetMapping("/new")
        public String createView(Model model) {
            model.addAttribute("newVocab", new Vocab());
            return "create-vocab";
        }

        @PostMapping("/new")
        public String create(Vocab newVocab, Model model) throws ChangeSetPersister.NotFoundException {
            newVocab = vocabService.createVocab(newVocab);
            return "redirect:/list";
        }

        @GetMapping("/update")
        public String updateVocabView(@RequestParam Long id, Vocab vocab, Model model) throws ChangeSetPersister.NotFoundException {

            model.addAttribute("updateVocab", vocabService.getVocab(id));
            return "update-vocab";
        }

        @PostMapping("/update")
        public String updateVocab(Vocab vocab, Model model) throws ChangeSetPersister.NotFoundException {
            vocabService.updateVocab(vocab);
            return "redirect:/list";
        }

        @GetMapping("/delete")
        public String deleteVocabView(@RequestParam Long id, Model model) throws ChangeSetPersister.NotFoundException {
            model.addAttribute("vocab", vocabService.getVocab(id));
            return "delete-vocab";
        }
        @PostMapping("/delete")
        public String deleteVocab(Vocab vocab,  Model model) {
            vocabService.deleteVocab(vocab.getId());
            return "redirect:/list";
        }


}

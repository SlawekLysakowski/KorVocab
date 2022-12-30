package io.slaweksApp.KorVocab.controller;

import io.slaweksApp.KorVocab.model.Vocab;
import io.slaweksApp.KorVocab.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
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
        try {
            model.addAttribute("vocab", vocabService.getVocab(id));
        } catch  (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
            return "404-vocab";
        }
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
        public String create(@Valid Vocab newVocab, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
            if(bindingResult.hasErrors()) {
                model.addAttribute(newVocab);
                model.addAttribute("org.springframework.validation.BindingResult.newVocab", bindingResult);
                return "create-vocab";
            }
            newVocab = vocabService.createVocab(newVocab);
            return "redirect:/list";
        }

        @GetMapping("/update")
        public String updateVocabView(@RequestParam Long id, Vocab vocab, Model model) throws ChangeSetPersister.NotFoundException {
            try {
                model.addAttribute("updateVocab", vocabService.getVocab(id));
            } catch  (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
                return "404-vocab";
            }
            return "update-vocab";
        }

        @PostMapping("/update")
        public String updateVocab(@Valid Vocab updateVocab, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
            if(bindingResult.hasErrors()) {
                model.addAttribute(updateVocab);
                model.addAttribute("org.springframework.validation.BindingResult.updateVocab", bindingResult);
                return "redirect:/update?id=" + updateVocab.getId();
            }
            try {
                updateVocab = vocabService.updateVocab(updateVocab);
            } catch (ChangeSetPersister.NotFoundException e) {
                e.printStackTrace();
                return "redirect:/update?id=" + updateVocab.getId() ;
            }
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
        @GetMapping("/error")
        public String errorView(Model model) {
        return "404-vocab";
    }
}

package com.springbootexmaple.readerlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {
    
    private ReadingListRepository repository;

    @Autowired
    public ReadingListController(ReadingListRepository repo)
    {
        this.repository = repo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{title}")
    public String getTitle(@PathVariable("title") String title, Model model)
    {
        List<Book> titles = repository.findByTitle(title);
        if(titles != null)
        {
            model.addAttribute("books", titles);
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST, value= "/{title}")
    public String addTitle(@PathVariable("title") String title, Book book)
    {
        book.setTitle(title);
        repository.save(book);
        return "redirect:/{title}";
    }
}

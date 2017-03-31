package news.controllers;

import news.Category;
import news.News;
import news.services.NewsService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

@Controller
public class NewsController {
    private int onPage;

    @Resource
    private NewsService newsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false) Integer page, Model model) {
        model.addAttribute("news", newsService.getNewsFromPage( page != null ? page : 1));
        model.addAttribute("pageCount", newsService.getSize());
        model.addAttribute("pageNum", page != null ? page : 1);
        model.addAttribute("category", Category.values());
        model.addAttribute("filtered", false);
        return "news";
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String fullArticle(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("article", newsService.getNewsById(id));
        return "article";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(@RequestParam(value="category", required = false) String category,
                         @RequestParam(value="title", required = false) String title,
                         @RequestParam(value="content", required = false) String content, Model model) {
        if (category == null && title == null && content == null) return "redirect:/";
        model.addAttribute("news", newsService.getFilteredList(category, title, content));
        model.addAttribute("category", Arrays.asList(Category.values()));
        model.addAttribute("filtered", true);
        return "news";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeArticle(@PathVariable("id")int id, Model model) {
        try {
            newsService.removeNews(id);
        } catch (ObjectNotFoundException ignored) {}
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("id")int id, Model model) {
        News article = newsService.getNewsById(id);
        model.addAttribute("article", article);
        model.addAttribute("category", Arrays.asList(Category.values()));
        model.addAttribute("title", "Редактирование новости");
        model.addAttribute("url", "/edit");
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("article") News article) {
        article.setDatetime(new Date());
        newsService.updateNews(article);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addArticle(Model model) {
        News article = new News();
        model.addAttribute("article", article);
        model.addAttribute("category", Arrays.asList(Category.values()));
        model.addAttribute("title", "Добавление новости");
        model.addAttribute("url", "/add");
        return "edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("article") News article) {
        newsService.addNews(article);
        return "redirect:/";
    }

    public news.services.NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(news.services.NewsService newsService) {
        newsService = newsService;
    }

    public int getOnPage() {
        return onPage;
    }

    public void setOnPage(int onPage) {
        this.onPage = onPage;
    }
}

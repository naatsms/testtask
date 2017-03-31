package news.services;

import news.Category;
import news.News;

import java.util.List;

public interface NewsService {
    int getSize();
    void addNews(News news);
    void removeNews(int id);
    News getNewsById(int id);
    void updateNews(News news);
    List<News> getAllNews();
    List<News> getNewsFromPage(int page);
    List<News> getFilteredList(String category, String title, String content);
}

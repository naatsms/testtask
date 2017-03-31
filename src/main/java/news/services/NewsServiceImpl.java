package news.services;

import news.Category;
import news.News;
import news.dao.NewsDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class NewsServiceImpl implements NewsService {
    private NewsDao dao;

    @Override
    public void addNews(News news) {
        dao.addNews(news);
    }

    @Override
    public void removeNews(int id) {
        dao.removeNews(id);
    }

    @Override
    public News getNewsById(int id) {
        return dao.getNewsById(id);
    }

    @Override
    public void updateNews(News news) {
        dao.updateNews(news);
    }

    @Override
    public List<News> getAllNews() {
        return dao.getAllNews();
    }

    @Override
    public int getSize() {
        return dao.getSize();
    }

    @Override
    public List<News> getNewsFromPage(int page) {
        return dao.getNewsFromPage(page);
    }

    @Override
    public List<News> getFilteredList(String category, String title, String content) {
        return dao.getFilteredList(category, title, content);
    }

    public NewsDao getDao() {
        return dao;
    }

    public void setDao(NewsDao dao) {
        this.dao = dao;
    }
}

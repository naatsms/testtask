package news.dao;

import news.Category;
import news.News;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {
    private final Logger logger = LoggerFactory.getLogger(NewsDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int getSize() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select COUNT(n.id) from News n");
        Number count = (Number) q.uniqueResult();
        return count.intValue();
    }

    @Override
    public void addNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(news);
        logger.info("News added:" + news);
    }

    @Override
    public void removeNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        News news = (News) session.load(News.class, id);
        if (news != null) session.delete(news);
        logger.info("News removed:" + news);
    }

    @Override
    public News getNewsById(int id) {
        Session session = sessionFactory.openSession();
        return (News) session.load(News.class, id);
    }

    @Override
    public void updateNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        session.update(news);
        logger.info("News updated" + news);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getAllNews() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from News n order by n.datetime desc").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getNewsFromPage(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM News n order by n.datetime desc")
                .setFirstResult((page-1) * News.NEWS_ON_PAGE)
                .setMaxResults(News.NEWS_ON_PAGE)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getFilteredList(String category, String title, String content) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(News.class);
        if (category != null) criteria.add(Restrictions.eq("category", Category.valueOf(category)));
        if (title != null) criteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
        if (content != null) criteria.add(Restrictions.like("content", content, MatchMode.ANYWHERE));
        return criteria.list();
    }

}

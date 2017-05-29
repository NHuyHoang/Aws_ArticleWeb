package articleweb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import articleweb.dao.NewsRepository;
import articleweb.models.News;

@Service
@Transactional
public class NewsService {
	private final NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}
	
	public List<News> findAll(){
		List<News> news = new ArrayList<News>();
		for(News ne:newsRepository.findAll()){
			news.add(ne);
		}
		return news;
	}
	
	public void save(News news){
		newsRepository.save(news);
	}
	
	public void delete(int id){
		newsRepository.delete(id);
	}
	
	public News findNews(int id){
		return newsRepository.findOne(id);
	}
}

package articleweb.dao;

import org.springframework.data.repository.CrudRepository;

import articleweb.models.News;

public interface NewsRepository extends CrudRepository<News,Integer>{
	
}

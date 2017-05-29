package articleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import articleweb.models.News;
import articleweb.service.NewsService;

@RestController
public class SampleRestController {
	
	@Autowired
	private NewsService newsService;
	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@GetMapping("/allnews")
	public String allNews(){
		return newsService.findAll().toString();
	}
	
	@GetMapping("/savenews")
	public String SaveNews(@RequestParam int id,@RequestParam String title,
						   @RequestParam String content,@RequestParam String link){
		News news = new News(id,title,content,link);
		newsService.save(news);
		return "Saved";
	}
	
	@GetMapping("/delnews")
	public String delNews(@RequestParam int id){
		newsService.delete(id);
		return "deleted";
	}
}

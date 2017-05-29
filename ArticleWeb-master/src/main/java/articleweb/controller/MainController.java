package articleweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import articleweb.AwsUpload;
import articleweb.UploadFile;
import articleweb.models.News;
import articleweb.service.NewsService;

@Controller
public class MainController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request){
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}
	
	@GetMapping("/all-news")
	public String AllNews(HttpServletRequest request){
		request.setAttribute("newslist", newsService.findAll());
		request.setAttribute("mode", "MODE_NEWS");
		return "index";
	}
	
	@GetMapping("/save-news")
	public String SaveNews(@ModelAttribute News singlenews,@ModelAttribute("filename") String filename, @ModelAttribute("filepath") String filepath ,BindingResult bindingResult, HttpServletRequest request){
		
		if(filepath!=null){
			/*UploadFile file = new UploadFile();
			String ggDriveLink = file.GetLink(filename, filepath);
			singlenews.setLink(ggDriveLink);*/
			AwsUpload file = new AwsUpload();
			try {
				String link = file.GetLink(filename, filepath);
				singlenews.setLink(link);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				singlenews.setLink("undefined");
			}
			
		}
		newsService.save(singlenews);
		request.setAttribute("newslist", newsService.findAll());
		request.setAttribute("mode", "MODE_NEWS");
		return "index";
	}
	
	@GetMapping("/new-news")
	public String NewNews(HttpServletRequest request){
		request.setAttribute("mode", "MODE_NEW");
		return "index";
	}
	
	@GetMapping("/update-news")
	public String UpdateNews(@RequestParam int id, HttpServletRequest request){	
		request.setAttribute("singlenews", newsService.findNews(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "index";
	}
	
	@GetMapping("/view-news")
	public String ViewNews(@RequestParam int id, HttpServletRequest request){	
		request.setAttribute("singlenews", newsService.findNews(id));
		request.setAttribute("mode", "MODE_VIEW");
		return "index";
	}
	
	@GetMapping("/del-news")
	public String DelNews(@RequestParam int id, HttpServletRequest request){
		newsService.delete(id);
		request.setAttribute("newslist", newsService.findAll());
		request.setAttribute("mode", "MODE_NEWS");
		return "index";
	}
}

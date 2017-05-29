package articleweb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="news")
public class News implements Serializable {
	@Id
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String link;
	
	public News(){
		
	}
	
	public News(int id, String title, String content, String link) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.link = link;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content + ", link=" + link + "]";
	}
	
	
}

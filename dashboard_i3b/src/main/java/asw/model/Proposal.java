package asw.model;

import asw.util.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Document(collection = "proposal")
public class Proposal extends AbstractVotable {

	@Id
	private String id;

	private User author;
	private HashMap<String, Comment> comments = new HashMap<String, Comment>();
	private boolean isAccepted;
	private String category;
	private String title;
	private String content;
	private Date expirationDate;

	public void setId(String id) {
		this.id = id;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setComments(HashMap<String, Comment> comments) {
		this.comments = comments;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Proposal() {
		setExpirationDate();
	}

	public Proposal(User author, String category, String title,
			String content) {
		setExpirationDate();
		this.author = author;
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public User getAuthor() {
		return author;
	}

	public HashMap<String, Comment> getComments() {
		return comments;
	}

	public List<Comment> getCommentsList() {

		return new ArrayList<Comment>(comments.values());

	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	private void setExpirationDate() {
		this.expirationDate = DateUtils.addDays(new Date(),
				Configuration.getInstance().getExpirationDays());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposal other = (Proposal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposal{" + "id='" + id + '\'' + ", author=" + author
				+ ", comments=" + comments + ", isAccepted=" + isAccepted
				+ ", category='" + category + '\'' + ", title='" + title + '\''
				+ ", content='" + content + '\'' + ", expirationDate="
				+ expirationDate + '}';
	}
}

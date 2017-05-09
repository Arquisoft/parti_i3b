package hello.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import hello.util.DateUtils;

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
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isAccepted ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAccepted != other.isAccepted)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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

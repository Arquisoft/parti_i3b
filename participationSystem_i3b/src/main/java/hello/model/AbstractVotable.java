package hello.model;

import java.util.HashMap;
import java.util.Map;

public class AbstractVotable implements Votable {

	protected Map<String, VoteType> votes;
	protected int positiveVotes;
	protected int negativeVotes;

	AbstractVotable() {
		this.votes = new HashMap<>();
		this.positiveVotes = 0;
		this.negativeVotes = 0;
	}

	@Override
	public int getVoteBalance() {
		return positiveVotes - negativeVotes;
	}

	@Override
	public void upvote(String userId) {

		VoteType vote = votes.get(userId);

		if (vote != null) {
			if (vote.equals(VoteType.NEGATIVE)) {
				negativeVotes--;
				positiveVotes++;
				votes.put(userId, VoteType.POSITIVE);
			}

		} else {
			votes.put(userId, VoteType.POSITIVE);
			positiveVotes++;
		}

	}

	@Override
	public void downvote(String userId) {
		VoteType vote = votes.get(userId);

		if (vote != null) {
			if (vote.equals(VoteType.POSITIVE)) {
				negativeVotes++;
				positiveVotes--;
				votes.put(userId, VoteType.NEGATIVE);
			}

		} else {
			votes.put(userId, VoteType.NEGATIVE);
			negativeVotes++;
		}
	}

	@Override
	public int getUpvotes() {
		return positiveVotes;
	}

	@Override
	public int getDownvotes() {
		return negativeVotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + negativeVotes;
		result = prime * result + positiveVotes;
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractVotable other = (AbstractVotable) obj;
		if (negativeVotes != other.negativeVotes)
			return false;
		if (positiveVotes != other.positiveVotes)
			return false;
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}

}

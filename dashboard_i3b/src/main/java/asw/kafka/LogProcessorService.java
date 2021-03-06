package asw.kafka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import asw.repository.DBService;

/**
 * Created by guille on 4/3/17.
 */
@Service
public class LogProcessorService {
    private static final Logger logger = Logger
	    .getLogger(LogProcessorService.class);
    private final DBService service;
    private Pattern newProposalPattern, upvotedProposalPattern,
	    downvotedProposalPattern, newCommentPattern, upvotedCommentPattern,
	    downvotedCommentPattern;

    @Autowired
    LogProcessorService(DBService service) {
	this.service = service;

	newProposalPattern = Pattern.compile("New proposal");
	upvotedProposalPattern = Pattern.compile("Upvoted proposal \\[(.+)\\]");
	downvotedProposalPattern = Pattern
		.compile("Downvoted proposal \\[(.+)\\]");
	newCommentPattern = Pattern.compile("New comment \\[(.+)\\]");
	upvotedCommentPattern = Pattern.compile("Upvoted comment \\[(.+)\\]");
	downvotedCommentPattern = Pattern
		.compile("Downvoted comment \\[(.+)\\]");
    }

    public void processKafkaLog(@Payload String data) {
	Matcher newProposalMatcher = newProposalPattern.matcher(data);
	Matcher upvotedProposalMatcher = upvotedProposalPattern.matcher(data);
	Matcher downvotedProposalMatcher = downvotedProposalPattern
		.matcher(data);
	Matcher newCommentMatcher = newCommentPattern.matcher(data);
	Matcher upvotedCommentMatcher = upvotedCommentPattern.matcher(data);
	Matcher downvotedCommentMatcher = downvotedCommentPattern.matcher(data);
	if (newProposalMatcher.matches()) { // New Proposal
	    logger.info("Added proposal");
	} else if (upvotedProposalMatcher.matches()) { // Upvote proposal
	    String ID = upvotedProposalMatcher.group(1);
	    logger.info("Upvoted proposal [" + ID + "]");
	} else if (downvotedProposalMatcher.matches()) { // Downvote proposal
	    String ID = downvotedProposalMatcher.group(1);
	    logger.info("Downvoted proposal [" + ID + "]");
	} else if (newCommentMatcher.matches()) { // New Proposal
	    String ID = newProposalMatcher.group(1);
	    logger.info("Added comment with proposal ID [" + ID + "]");
	} else if (upvotedCommentMatcher.matches()) { // Upvote proposal
	    String ID = upvotedCommentMatcher.group(1);
	    logger.info("Upvoted comment with proposal ID [" + ID + "]");
	} else if (downvotedCommentMatcher.matches()) { // Downvote proposal
	    String ID = downvotedCommentMatcher.group(1);
	    logger.info("Downvoted comment with proposal ID [" + ID + "]");
	}

	else {
	    logger.info("String [" + data + "] not recognized");
	}

    }
}

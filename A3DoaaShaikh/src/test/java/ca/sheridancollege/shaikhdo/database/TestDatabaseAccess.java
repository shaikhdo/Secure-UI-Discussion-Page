package ca.sheridancollege.shaikhdo.database;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.sheridancollege.shaikhdo.beans.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase

public class TestDatabaseAccess {
	
	@Autowired
	private DatabaseAccess da;
	
	@Test
	public void whenInsertPost_getPosts() {
		//setup
		User userComment = new User();
		userComment.setUserPost("Test");
		//when
		da.insertPost(userComment);
		//test
		Assert.assertTrue(da.getUserCommentList().size()>0);
	}

}

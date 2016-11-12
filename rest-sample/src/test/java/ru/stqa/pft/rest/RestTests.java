package ru.stqa.pft.rest;

import com.solidfire.gson.Gson;
import com.solidfire.gson.JsonElement;
import com.solidfire.gson.JsonParser;
import com.solidfire.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created by owlowl on 11.11.16.
 */
public class RestTests extends RestHelper{
	private Executor executor;
	
	@Test
	public void testCreateIssue() throws IOException {
		skipIfNotFixed(4);
		Set<Issue> oldIssues = getIssues();
		Issue toAdd = new Issue().withDescription("Possibly Unnadded Test").withSubject("test Subject");
		int newId = createIssue(toAdd);
		Set<Issue> newIssues = getIssues();
		oldIssues.add(toAdd.withId(newId));
		Assert.assertEquals(newIssues, oldIssues);
	}
	
	
	
}

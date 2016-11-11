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
public class RestTests {
	private Executor executor;
	
	@Test
	public void testCreateIssue() throws IOException {
		Set<Issue> oldIssues = getIssues();
		Issue toAdd = new Issue().withDescription("test descr").withSubject("test Subject");
		int newId = createIssue(toAdd);
		Set<Issue> newIssues = getIssues();
		oldIssues.add(toAdd.withId(newId));
		Assert.assertEquals(newIssues, oldIssues);
	}
	
	private Set<Issue> getIssues() throws IOException {
		String json = getExecutor().execute(
				Request.Get("http://localhost/bugify/public/api/issues.json")).returnContent().asString();
		System.out.println(json);
		JsonElement issues = new JsonParser().parse(json).getAsJsonObject().get("issues");
		return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
		}.getType());
	}
	
	private Executor getExecutor() {
		return Executor.newInstance().auth("c5dcdb813cdd801ebda8c31a523af087", "");
	}
	
	private int createIssue(Issue toAdd) throws IOException {
		String json = getExecutor().execute(Request.Post("http://localhost/bugify/public/api/issues.json")
				.bodyForm(new BasicNameValuePair("subject", toAdd.getSubject())
						, new BasicNameValuePair("description", toAdd.getDescription()))).returnContent().asString();
		return Integer.parseInt(new JsonParser().parse(json).getAsJsonObject().get("issue_id").getAsString());
	}
	
}

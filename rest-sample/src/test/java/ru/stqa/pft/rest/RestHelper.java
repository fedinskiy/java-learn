package ru.stqa.pft.rest;

import com.solidfire.gson.Gson;
import com.solidfire.gson.JsonElement;
import com.solidfire.gson.JsonParser;
import com.solidfire.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

/**
 * Created by owlowl on 12.11.16.
 */
public class RestHelper {
	private final String API_BASE="http://localhost/bugify/public/api";
	
	public void skipIfNotFixed(int issueId) {
		if (isIssueOpen(issueId)) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}
	
	private boolean isIssueOpen(int issueId) {
		Issue issue = null;
		try {
			issue = getIssue(issueId);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if (null==issue) return false;
		return (issue.isOpen());
	}
	
	public Set<Issue> getIssues() throws IOException {
		String json = getExecutor().execute(
				Request.Get(API_BASE+"/issues.json")).returnContent().asString();
		JsonElement issues = new JsonParser().parse(json).getAsJsonObject().get("issues");
		return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
		}.getType());
	}
	
	public Issue getIssue(int issueId)throws IOException {
		String json = getExecutor().execute(
				Request.Get(API_BASE+"/issues/"+issueId+".json")).returnContent().asString();
		JsonElement issue = new JsonParser().parse(json).getAsJsonObject().get("issues").getAsJsonArray().get(0);
		return new Gson().fromJson(issue, Issue.class);
	}
	
	private Executor getExecutor() {
		return Executor.newInstance().auth("c5dcdb813cdd801ebda8c31a523af087", "");
	}
	
	public int createIssue(Issue toAdd) throws IOException {
		String json = getExecutor().execute(Request.Post(API_BASE+"/issues.json")
				.bodyForm(new BasicNameValuePair("subject", toAdd.getSubject())
						, new BasicNameValuePair("description", toAdd.getDescription()))).returnContent().asString();
		return Integer.parseInt(new JsonParser().parse(json).getAsJsonObject().get("issue_id").getAsString());
	}
}

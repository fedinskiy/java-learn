package ru.stqa.pft.mantis.tests.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owlowl on 23.09.16.
 */
public class Session {
	private CloseableHttpClient httpClient;
	private ApplicationManager app;

	
	public Session(ApplicationManager app) {
		this.app = app;
		httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
	}
	
	public boolean login(String username, String password) throws IOException {
		HttpPost post = new HttpPost(app.configuration().getEntryPoint());
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("username",username));
		params.add(new BasicNameValuePair("password",password));
		params.add(new BasicNameValuePair("secure_session","on"));
		params.add(new BasicNameValuePair("return","index.php"));
		post.setEntity(new UrlEncodedFormEntity(params));
		CloseableHttpResponse resp=httpClient.execute(post);
		String Body = getTextFrom(resp);
		return Body.contains("<span id=\"logged-in-user\">"+username+"</span");
		
		
	}
	
	private String getTextFrom(CloseableHttpResponse resp) throws IOException {
		try {
			return EntityUtils.toString(resp.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			resp.close();
		}
		return null;
	}
	 public boolean isLoggedAs(String username) throws IOException {
		 HttpGet get = new HttpGet(app.configuration().getMainPage());
		 CloseableHttpResponse resp  =httpClient.execute(get);
		 String Body = getTextFrom(resp);
		 return Body.contains("<span id=\"logged-in-user\">"+username+"</span");
		 
	 }
}

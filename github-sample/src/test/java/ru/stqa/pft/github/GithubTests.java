package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by owlowl on 12.11.16.
 */
public class GithubTests {
	@Test
	public void testCommits() throws IOException {
		Github github = new RtGithub("a4c0a59c787d2d55ecb13acb4c9ee974de335a74");
		RepoCommits commits = github.repos().get(new Coordinates.Simple("jaowl", "java-learn")).commits();
		for (RepoCommit commit:commits.iterate(new ImmutableMap.Builder<String,String >().build())){
		
		}
	}
}

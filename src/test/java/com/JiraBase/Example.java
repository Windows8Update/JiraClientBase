package com.JiraBase;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;

public class Example {

    public static void main(String[] args) {

        BasicCredentials creds = new BasicCredentials("g.gaurav2812@gmail.com", "2DOmgO677Tyu2T6zvqGz0BB9");
        JiraClient jira = new JiraClient("https://gauravgarje.atlassian.net/", creds);

        try {
			
        	/* Retrieve issue TEST-123 from JIRA. We'll get an exception if this fails. */
            Issue issue = jira.getIssue("TES-15");

            /* Print the issue key. */
            System.out.println(issue);

            /* You can also do it like this: */
            System.out.println(issue.getKey());

            /* Vote for the issue. */
            issue.vote();


            /* Add two comments, with one limited to the developer role. */
            issue.addComment("No problem. We'll get right on it!");
            issue.addComment("He tried to send a whole Internet!", "role", "Developers");

            /* Print the reporter's username and then the display name */
            System.out.println("Reporter: " + issue.getReporter());
            System.out.println("Reporter's Name: " + issue.getReporter().getDisplayName()); 

         
            /* Create a new issue. */
            Issue newIssue = jira.createIssue("TEST", "Bug")
                .field(Field.SUMMARY, "Bat signal is broken")
                .field(Field.DESCRIPTION, "Commissioner Gordon reports the Bat signal is broken.")
                .field(Field.REPORTER, "Gaurav Garje")
                .field(Field.ASSIGNEE, "Gaurav Garje")
                .execute();
            System.out.println(newIssue);

            /* Link to the old issue */
            newIssue.link("TES-15", "Dependency");

            /* Create sub-task */
            Issue subtask = newIssue.createSubtask()
                .field(Field.SUMMARY, "replace lightbulb")
                .execute();

        	
		} catch (Exception e) {
			// TODO: handle exception
		}
              
        
    }
}


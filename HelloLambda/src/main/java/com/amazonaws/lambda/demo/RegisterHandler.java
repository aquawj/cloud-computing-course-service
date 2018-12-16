package com.amazonaws.lambda.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RegisterHandler implements RequestHandler<CourseEvent, Integer> {
	
    @Override
    public Integer handleRequest(CourseEvent course, Context context) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("offeringId", course.getCourseId());
        jsonObj.put("offeringType", "Course");
        jsonObj.put("department", course.getDepartment());
        String url = "http://neucourseservice.us-west-2.elasticbeanstalk.com/webapi/registerOffering";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(json.toString()));
        HttpResponse response = null;
        response = httpClient.execute(request);
        return response == null ? 0 : 1;
    }

}

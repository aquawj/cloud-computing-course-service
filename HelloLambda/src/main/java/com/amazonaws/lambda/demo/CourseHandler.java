package com.amazonaws.lambda.demo;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;

 public class CourseHandler implements RequestHandler<DynamodbEvent, Integer> {

	private AWSStepFunctions client = AWSStepFunctionsClientBuilder
			.standard()
			.withRegion(Regions.US_WEST_2).build();
	
    @Override
    public Integer handleRequest(DynamodbEvent event, Context context) {
        int success = 0;
        for(DynamodbStreamRecord record : event.getRecords()) {
        	if(record == null) break;
			String curEvent = record.getEventName();
			if(!curEvent.equals("INSERT"))
				continue;
			success = 1;
			Map<String, AttributeValue> map = record.getDynamodb().getNewImage();
			String courseId = map.get("courseId").getS();
            String department = map.get("department").getS();
            //List<String> roster = map.get("roster").getL();
            String boardId = "";
            if (map.get("BoardId") != null){
            	boardId = map.get("BoardId").getS();
            }
            CourseEvent state = new CourseEvent(courseId, boardId, department);
            StartExecutionRequest request = new StartExecutionRequest();
            request.setInput(state.toString());
            request.setStateMachineArn("arn:aws:states:us-west-2:821043062437:stateMachine:CourseService");
            client.startExecution(request);	
		}
        return success;
    }
 }

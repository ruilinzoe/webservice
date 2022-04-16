package com.example.webservice;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {


//        ynamoDbClient ddb = DynamoDbClient.create();
//        PutItemRequest request = PutItemRequest.builder()
//                .tableName("TOKENS");
//        ddb.putItem(request);
        DynamoDbClient client = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("AKIAYLSEFMVHOG2IVWVU", "U570/5HkKV4mqrIvyzq8lfEXQ6uYi/ULhDqKOKil")))
                .build();
        HashMap<String,AttributeValue> itemValues = new HashMap<>();
        // Add all content to the table
        itemValues.put("email", AttributeValue.builder().s("email").build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName("TOKENS")
                .item(itemValues)
                .build();
        client.putItem(request);

//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable("ProductCatalog");

    }
}

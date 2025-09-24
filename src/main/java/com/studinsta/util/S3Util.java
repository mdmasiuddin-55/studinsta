package com.studinsta.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.UUID;

public class S3Util {
    private static final String BUCKET_NAME = "your-s3-bucket";
    private static final String CLOUDFRONT_URL = "https://your-distribution-id.cloudfront.net/";

    private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .withRegion(Regions.AP_SOUTH_1) // change to your AWS region
            .build();

    public static String uploadFile(File file) {
        try {
            String key = "posts/" + UUID.randomUUID() + "-" + file.getName();
            s3Client.putObject(new PutObjectRequest(BUCKET_NAME, key, file));
            return CLOUDFRONT_URL + key;
        } catch (AmazonServiceException e) {
            System.err.println("AmazonServiceException: " + e.getMessage());
        } catch (SdkClientException e) {
            System.err.println("SdkClientException: " + e.getMessage());
        }
        return null;
    }
}

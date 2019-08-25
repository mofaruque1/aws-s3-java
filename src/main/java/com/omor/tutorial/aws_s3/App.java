package com.omor.tutorial.aws_s3;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

public class App {
	public static void main(String[] args)

	{
		Regions region = Regions.CA_CENTRAL_1;
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		createS3Bucket(s3Client);
		listingS3Buckets(region, s3Client);

	}

	public static void createS3Bucket(AmazonS3 s3Client) {
		String bucketName = "tutorial-bucket-2019";
		
		if (s3Client.doesBucketExist(bucketName)) {
			System.out.println("Bucket name is not available. Try again with a different Bucket name.");
			return;
		}
		s3Client.createBucket(bucketName);
	}

	public static void listingS3Buckets(Regions region, AmazonS3 s3Client) {
		List<Bucket> buckets = s3Client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
	}
}

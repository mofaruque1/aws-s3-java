package com.omor.tutorial.aws_s3;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args)

	{
		Regions region = Regions.CA_CENTRAL_1;
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		listingS3Buckets(region, s3Client);
		
		

	}
	
	public static void listingS3Buckets(Regions region, AmazonS3 s3Client) {
		List<Bucket> buckets = s3Client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
	}
}

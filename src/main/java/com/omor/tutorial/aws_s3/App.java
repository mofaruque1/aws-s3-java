package com.omor.tutorial.aws_s3;

import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

public class App {
	public static void main(String[] args)

	{
		Regions region = Regions.CA_CENTRAL_1;
		String bucketName = "tutorial-bucket-2019";
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).build();
		
		System.out.println("|---------listing buckets---------|");
		listingS3Buckets(region, s3Client);
		
		System.out.println("|---------creating bucket---------|");
		createS3Bucket(s3Client,bucketName);
		
		System.out.println("|---------listing buckets---------|");
		listingS3Buckets(region, s3Client);
		
		System.out.println("|---------delete bucket---------|");
		deleteS3Bucket(s3Client,bucketName);
		
		System.out.println("|---------listing buckets---------|");
		listingS3Buckets(region, s3Client);
		

	}

	/**
	 * @param s3Client
	 *            Amazons3 this function creates a s3 bucket
	 */
	public static void createS3Bucket(AmazonS3 s3Client,String bucketName) {
		

		if (s3Client.doesBucketExist(bucketName)) {
			System.out.println("Bucket name is not available. Try again with a different Bucket name.");
			return;
		}
		s3Client.createBucket(bucketName);
	}

	/**
	 * @param bucketname
	 *            Deletes a s3 bucket
	 */
	public static void deleteS3Bucket(AmazonS3 s3Client,String bucketname) {
		try {
			s3Client.deleteBucket(bucketname);
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			return;
		}
	}

	/**
	 * @param region
	 *            the region on your console
	 * @param s3Client
	 *            Amazons3 list all the bucket with this account
	 */
	public static void listingS3Buckets(Regions region, AmazonS3 s3Client) {
		List<Bucket> buckets = s3Client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
	}
}

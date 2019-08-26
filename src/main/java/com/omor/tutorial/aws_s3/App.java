package com.omor.tutorial.aws_s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class App {
	public static void main(String[] args) throws IOException

	{
		Regions region = Regions.CA_CENTRAL_1;
		String bucketName = "tutorial-bucket-2019";
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();
		
		Bucket bucket= createS3Bucket(s3, bucketName);
		
		listObjectsInBucket(s3, bucket);

		
		
		
	

		
		
		
		
		

	}

	public static void listObjectsInBucket(AmazonS3 s3, Bucket b) {
		ObjectListing objectListing = s3.listObjects(b.getName());
		for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
			System.out.println(os.getKey());
		}
	}
	
	public static void uploadObjectToS3(AmazonS3 s3, Bucket b) {
		s3.putObject(b.getName(), "Files/1.txt", new File("/Users/mdomor.faruque/Desktop/files/file1.txt"));
	}
	
	
	/**
	 * @param s3Client
	 *            Amazons3 this function creates a s3 bucket
	 */
	public static Bucket createS3Bucket(AmazonS3 s3Client,String bucketName) {
		Bucket b = null;

		if (s3Client.doesBucketExist(bucketName)) {
			System.out.println("Bucket name is not available or already exist");
			b = getBucket(s3Client, bucketName);
			
		}
		else {
			try {
				b = s3Client.createBucket(bucketName);
			} catch (AmazonS3Exception e) {
				System.err.println(e.getErrorMessage());
			}
		}
		
		return b;
	}
	
	
	public static Bucket getBucket(AmazonS3 s3, String name) {
		List<Bucket> buckets = s3.listBuckets();
		Bucket temp = null;
		for (Bucket bucket : buckets) {
			if (bucket.getName().equals(name)) {
				temp = bucket;
				break;
			}
		}
		return temp;
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
	
	
	public static void readFile(String filePath) throws IOException {
		String url = "/Users/mdomor.faruque/Desktop/API_list.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str=reader.readLine()) != null) {
				System.out.println(str);
				
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}
}

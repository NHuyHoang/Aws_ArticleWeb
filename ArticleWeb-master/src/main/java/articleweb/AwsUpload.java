package articleweb;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;


public class AwsUpload {
	public AwsUpload(){
			
		}

	public String GetLink(String FileName, String FilePath) throws InterruptedException {
		String existingBucketName = "uploadg4";
        String keyName            = FileName;
        String filePath           = FilePath;  
        
        TransferManager tm = new TransferManager(new ProfileCredentialsProvider());        
        System.out.println("Creating TransferManager");
        // TransferManager processes all transfers asynchronously, 
        // so this call will return immediately.
        Upload upload = tm.upload(existingBucketName, keyName, new File(filePath));
        System.out.println("Going to upload file.....");

        try {
        	// Or you can block and wait for the upload to finish
        	upload.waitForCompletion();
        	System.out.println("Upload complete.");
        	return "https://"+existingBucketName+".s3.amazonaws.com/"+keyName;
        } catch (AmazonClientException amazonClientException) {
        	System.out.println("Unable to upload file, upload was aborted.");
        	amazonClientException.printStackTrace();
        	return "undefined";
        }

	}
}

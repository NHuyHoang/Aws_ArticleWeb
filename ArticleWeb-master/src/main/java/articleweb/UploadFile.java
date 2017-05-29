package articleweb;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;


import com.google.api.services.drive.Drive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class UploadFile {

	/** Application name. */
	private static final String APPLICATION_NAME = "Drive API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/drive-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.  *  * If
	 * modifying these scopes, delete your previously saved credentials  * at
	 * ~/.credentials/drive-java-quickstart  
	 */
	private static final List<String> SCOPES =   Arrays.asList(DriveScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 *  * Creates an authorized Credential object.  * @return an authorized
	 * Credential object.  * @throws IOException  
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		FileInputStream fis = null;
		 fis = new FileInputStream("src/main/resources/static/client_secret.json");
		//InputStream in = UploadFile.class.getResourceAsStream("client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(fis));

		// Build flow and trigger user authorization request.
	//	GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	//			 HTTP_TRANSPORT, JSON_FACTORY, "396412209856-72aa2th1jklnf9rq9dhuk6sn0no9c543.apps.googleusercontent.com","SDDvQtvc4iiDQE22bIUJ4kqZ",SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).build();
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 *  * Build and return an authorized Drive client service.  * @return an
	 * authorized Drive client service  * @throws IOException  
	 */
	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}
	
	public void Search(){
		String pageToken = null;
		Drive service;
		try {
			service = getDriveService();
			do {
			    FileList result = service.files().list()
			            .setQ("mimeType='image/jpeg'")
			            .setSpaces("drive")
			            .setFields("nextPageToken, files(id, name)")
			            .setPageToken(pageToken)
			            .execute();
			    for(File file: result.getFiles()) {
			        System.out.printf("Found file: %s (%s)\n",
			                file.getName(), file.getId());
			    }
			    pageToken = result.getNextPageToken();
			} while (pageToken != null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public UploadFile(){
		
	}

	public String GetLink(String FileName, String FilePath) {
		// Build a new authorized API client service.
		Drive service;
		try {
			service = getDriveService();
			File fileMetadata = new File();
			fileMetadata.setName(FileName);
			FilePath = FilePath.replace("\\", "/");
			java.io.File filePath = new java.io.File(FilePath);
			FileContent mediaContent = new FileContent("application/msword", filePath);
			File file = service.files().create(fileMetadata, mediaContent).setFields("id").execute();
			//System.out.println("File ID: " + file.getId());
			return "https://drive.google.com/open?id="+file.getId();
		} catch (IOException e) {
			e.printStackTrace();
			return "Undefined";
		}

	}
}

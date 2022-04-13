package com.asia.leadsgen.test.drivecloud;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.asia.leadsgen.test.util.GoogleDriveUtils;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class CreateGoogleFile {

	// PRIVATE!
	private static File _createGoogleFile(String googleFolderIdParent, String contentType, //
			String customFileName, AbstractInputStreamContent uploadStreamContent) throws IOException {

		File fileMetadata = new File();
		fileMetadata.setName(customFileName);

		List<String> parents = Arrays.asList(googleFolderIdParent);
		fileMetadata.setParents(parents);
		//
		Drive driveService = GoogleDriveUtils.getDriveService();

		File file = driveService.files().create(fileMetadata, uploadStreamContent)
				.setFields("id, webContentLink, webViewLink, parents").execute();

		return file;
	}

	// Create Google File from byte[]
	public static File createGoogleFile(String googleFolderIdParent, String contentType, //
			String customFileName, byte[] uploadData) throws IOException {
		//
		AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, uploadData);
		//
		return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	}

	// Create Google File from java.io.File
	public static File createGoogleFile(String googleFolderIdParent, String contentType, //
			String customFileName, java.io.File uploadFile) throws IOException {

		//
		AbstractInputStreamContent uploadStreamContent = new FileContent(contentType, uploadFile);
		//
		return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	}

	// Create Google File from InputStream
	public static File createGoogleFile(String googleFolderIdParent, String contentType, //
			String customFileName, InputStream inputStream) throws IOException {

		//
		AbstractInputStreamContent uploadStreamContent = new InputStreamContent(contentType, inputStream);
		//
		return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	}

	public static String uploadGoogleDrive(String filePath) throws IOException {
//	public static void main(String[] args) throws IOException {

//		String filePath = "C:/Users/M710q/Desktop/CreateGoogleFile.txt";

		java.io.File uploadFile = new java.io.File(filePath);

		String strPath = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());

		// Create Google File:

		File googleFile = createGoogleFile("1MCcpry7abv4GpFcKjVuiNm7zskZbkZ55", "text/plain", strPath, uploadFile);
//        File googleFile = createGoogleFile("1MCcpry7abv4GpFcKjVuiNm7zskZbkZ55", "text/plain", "newfile.csv", uploadFile);
		System.out.println("Created Google file!");
		System.out.println("WebContentLink: " + googleFile.getWebContentLink());
		System.out.println("getWebViewLink: " + googleFile.getWebViewLink());

		return googleFile.getWebViewLink();
	}
	
	public static String uploadMockupGoogleDrive(String filePath) throws IOException {

			java.io.File uploadFile = new java.io.File(filePath);

			String strPath = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());

			// Create Google File:

			File googleFile = createGoogleFile("16FmGDEvFK9DensCf6CYpLt09X1SFo87D", "image/jpeg", strPath, uploadFile);
			System.out.println("Created Google file!");
			System.out.println("WebContentLink: " + googleFile.getWebContentLink());
			System.out.println("getWebViewLink: " + googleFile.getWebViewLink());

			return googleFile.getWebViewLink();
		}

}
package ezvcard.property;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ezvcard.parameter.ImageType;

/*
 Copyright (c) 2013, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies, 
 either expressed or implied, of the FreeBSD Project.
 */

/**
 * A photo attached to the vCard (such as a portrait of the person).
 * 
 * <p>
 * <b>Adding a photo</b>
 * </p>
 * 
 * <pre class="brush:java">
 * VCard vcard = new VCard();
 * 
 * //URL
 * Photo photo = new Photo("http://www.mywebsite.com/mugshot.jpg", ImageType.JPEG);
 * vcard.addPhoto(photo);
 * 
 * //binary data
 * byte data[] = ...
 * photo = new Photo(data, ImageType.JPEG);
 * vcard.addPhoto(photo);
 * 
 * //if "ImageType" does not have the pre-defined constant that you need, then create a new instance
 * //arg 1: the value of the 2.1/3.0 TYPE parameter
 * //arg 2: the value to use for the 4.0 MEDIATYPE parameter and for 4.0 data URIs
 * //arg 3: the file extension of the data type (optional)
 * ImageKeyTypeParameter param = new ImageType("bmp", "image/x-ms-bmp", "bmp");
 * photo = new Photo("http://www.mywebsite.com/mugshot.bmp", param);
 * vcard.addPhoto(photo);
 * </pre>
 * 
 * <p>
 * <b>Getting the photos</b>
 * </p>
 * 
 * <pre class="brush:java">
 * VCard vcard = ...
 * 
 * int fileCount = 0;
 * for (Photo photo : vcard.getPhotos()){
 *   //the photo will have either a URL or a binary data
 *   if (photo.getData() == null){
 *     System.out.println("Photo URL: " + photo.getUrl());
 *   } else {
 *     ImageType type = photo.getContentType();
 *     
 *     if (type == null) {
 *       //the vCard may not have any content type data associated with the photo
 *       System.out.println("Saving a photo file...");
 *     } else {
 *       System.out.println("Saving a \"" + type.getMediaType() + "\" file...");
 *     }
 *     
 *     String folder;
 *     if (type == ImageType.JPEG){ //it is safe to use "==" instead of "equals()"
 *       folder = "photos";
 *     } else {
 *       folder = "images";
 *     }
 *     
 *     byte data[] = photo.getData();
 *     String filename = "photo" + fileCount;
 *     if (type != null && type.getExtension() != null){
 *     	filename += "." + type.getExtension();
 *     }
 *     OutputStream out = new FileOutputStream(new File(folder, filename));
 *     out.write(data);
 *     out.close();
 *     fileCount++;
 *   }
 * }
 * </pre>
 * 
 * <p>
 * <b>Property name:</b> {@code PHOTO}
 * </p>
 * <p>
 * <b>Supported versions:</b> {@code 2.1, 3.0, 4.0}
 * </p>
 * @author Michael Angstadt
 */
public class Photo extends ImageProperty {
	/**
	 * Creates a photo property.
	 * @param url the URL to the photo
	 * @param type the content type (e.g. JPEG)
	 */
	public Photo(String url, ImageType type) {
		super(url, type);
	}

	/**
	 * Creates a photo property.
	 * @param data the binary data of the photo
	 * @param type the content type (e.g. JPEG)
	 */
	public Photo(byte[] data, ImageType type) {
		super(data, type);
	}

	/**
	 * Creates a photo property.
	 * @param in an input stream to the binary data (will be closed)
	 * @param type the content type (e.g. JPEG)
	 * @throws IOException if there's a problem reading from the input stream
	 */
	public Photo(InputStream in, ImageType type) throws IOException {
		super(in, type);
	}

	/**
	 * Creates a photo property.
	 * @param file the image file
	 * @param type the content type (e.g. JPEG)
	 * @throws IOException if there's a problem reading from the file
	 */
	public Photo(File file, ImageType type) throws IOException {
		super(file, type);
	}
}
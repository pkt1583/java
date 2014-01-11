package com.kang.ebook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.kang.util.FileUtil;

/*
 * This is to extract a chaptered book quickly. 2013-02-22
 *  
 * In this site, chapter links start with a common prefix then appended with the chapter sequence
 * then appended with a common suffix.
 * 
 * The links array can be generated by viewing source of the first page, the 11th page .....
 * 
 * Finally write the pages in sequence to a file of html. 
 * 
 * ***********************************************************************************
 * Depends on how many links there are, the same amount of files will be generated.  * 
 * Then u need to create a index file, some thing like default.html 				 *
 * refer to the kindle books that i made, create the file structure and generate     * 
 * 																					 *
 * 					Kindle book														 *
 * ***********************************************************************************
 * 
 */
public class EbookOld {
	
	//this is the html file going to be generated.
	private String bookFileName = "shibaiebook%s.html";
	
	private String headerMetaInfo = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
	private String[] links = {
			"http://www.woelife.com/html/bottom4/book_10_01.html",
			"http://www.woelife.com/html/bottom4/book_10_02.html",
			"http://www.woelife.com/html/bottom4/book_10_03.html",
			"http://www.woelife.com/html/bottom4/book_10_04.html",
			"http://www.woelife.com/html/bottom4/book_10_05.html",
			"http://www.woelife.com/html/bottom4/book_10_06.html",
			"http://www.woelife.com/html/bottom4/book_10_07.html",
			"http://www.woelife.com/html/bottom4/book_10_08.html",
			"http://www.woelife.com/html/bottom4/book_10_09.html",
		
	};
	
	

	private String prefix = "<body bgcolor=\"#FFFFFF\" text=\"#000000\" link=\"#000080\" vlink=\"#008080\" alink=\"#800000\" topmargin=\"0\" leftmargin=\"0\">";
	private String suffix = "</body>";
	
	private int chapterIntervals = 1000;
	
	public static void main(String[] args) {
		Ebook ebook = new Ebook();
		System.out.println("------Done-----");
	}

	public EbookOld() {
		for (int i=1; i<= links.length; i++) {
			StringBuffer finalResult = new StringBuffer();

			finalResult.append(String.format("<html><head>%s</head><body>", headerMetaInfo));
			
			String link = (String) links[i-1];

			try {
				String pageContent = getPageDetails(link);
				finalResult.append("<p>");
				finalResult.append(pageContent);
				finalResult.append("<p>");

				Thread.sleep(chapterIntervals); // sleep for  mil-second before retrieving the page
									

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			finalResult.append("</body></html>");

			FileUtil.writeFile(finalResult.toString(), String.format(bookFileName, i));
			System.out.println("Done with " + link);
		}

		
		
	}

	

	/*
	 * Has the http link of a page, get the content as a formatted html
	 * string
	 */
	private String getPageDetails(String link) {
		String pageFullContent = getWebPageInFullHttpResponse(link);
		
		String pageContent = "";
		
		try {
			pageContent = getWebPart(pageFullContent, prefix, suffix);
		} catch(Exception e){
			
		}
		return pageContent;
	}

	private void writeFile(String content, String filename) {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));

			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}

	private String getWebPageInFullHttpResponse(String sitelink) {
		StringBuffer webPageBuffer = new StringBuffer();

		try {
			URL url = new URL(sitelink);

			URLConnection uc = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(uc
					.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				webPageBuffer.append("<BR>" + inputLine);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error with link: " + sitelink);
		}

		return webPageBuffer.toString();
	}

	private String getWebPart(String input, String prefix, String suffix) {
		String part;

		int index = 0;

		index = input.indexOf(prefix) ;

		input = input.substring(index);

		index = input.indexOf(suffix);

		part = input.substring(0, index);

		return part;
	}

}
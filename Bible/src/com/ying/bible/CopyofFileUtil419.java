package com.ying.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class CopyofFileUtil419 {
	/*
	 * Write something to a file
	 */
	public static void writeFile(String content, String filename){
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));
	
			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
	
	/*
	 * Get each line from the text file and add it to the arraylist passed in. 
	 */
	public static String getContents(File aFile) {
		StringBuffer contents = new StringBuffer ();
		contents.append(getBookLinks());
		int chapterCounter = 0;
		int verseCounter = 1;
		
		String bookLink = "";

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <99999) {
					if (line.indexOf("<BOOK NAME=\"") >=0){
						verseCounter = 1;
						chapterCounter = 1;
						
						line = line.substring(line.indexOf("<BOOK NAME=\"") + "<BOOK NAME=\"".length());
						String bookName = line.substring(0, line.indexOf("\""));
						
						bookLink = getBookLink(bookName);
//						System.out.println("\n<br><a name='" + bookLink +  "'/>" + bookName + "\n");
//						System.out.println("\n<br><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						contents.append("\n<p>&nbsp;<p><a name='" + bookLink +  "'/><a href='#bible'>" + bookName + "</a>\n");
						
						//leave the space for chapter links
						contents.append("\n<br>&nbsp;<br>" + getBookChapterLinks(bookLink));
						contents.append("\n<p>&nbsp;<br><a name='" + bookLink +  chapterCounter + "'/><a href='#" + bookLink + "'>第" + chapterCounter + "章" + "</a>\n");
						
						
					} else if (line.indexOf("<CHAPTER>") >=0){
						chapterCounter ++;
//						System.out.println("\n第" + chapterCounter + "章" + "\n");
//						System.out.println("\n<br><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						contents.append("\n<p>&nbsp;<br><a name='" + bookLink +  chapterCounter + "'/><a href='#" + bookLink + "'>第" + chapterCounter + "章" + "</a>\n");
						 verseCounter = 1;
					} else if (line.indexOf("		<VERSE>")>=0) {
//						System.out.println("<br>" + chapterCounter + ":" + verseCounter + " " +  getVerseContent(line));
						contents.append("\n<br>" + chapterCounter + ":" + verseCounter + " " +  getVerseContent(line) );
						verseCounter++;
					}
					
//					contents.append(line);
					i++;
					
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	private static String getBookLink(String bookName) {
		if (bookName.equals("創世記")){
			return "Genesis";
		} else if (bookName.equals("出埃及記")){
			return "Exodus";
		} else if (bookName.equals("利未記")){
			return "Leviticus";
		} else if (bookName.equals("民數記")){
			return "Numbers";
		} else if (bookName.equals("申命記")){
			return "Deuteronomy";
		} else if (bookName.equals("約書亞記")){
			return "Joshua";
		} else if (bookName.equals("士師記")){
			return "Judges";
		} else if (bookName.equals("路得記")){
			return "Ruth";
		} else if (bookName.equals("撒母耳記上")){
			return "Samuel";
		} else if (bookName.equals("撒母耳記下")){
			return "Samuel2-";
		} else if (bookName.equals("列王紀上")){
			return "Kings";
		} else if (bookName.equals("列王紀下")){
			return "Kings2-";
		} else if (bookName.equals("歷代志上")){
			return "Chronicles";
		} else if (bookName.equals("歷代志下")){
			return "Chronicles2-";
		} else if (bookName.equals("以斯拉記")){
			return "Ezra";
		} else if (bookName.equals("尼希米記")){
			return "Nehemiah";
		} else if (bookName.equals("以斯帖記")){
			return "Esther";
		} else if (bookName.equals("約伯記")){
			return "Job";
		} else if (bookName.equals("詩篇")){
			return "Psalms";
		} else if (bookName.equals("箴言")){
			return "Proverbs";
		} else if (bookName.equals("傳道書")){
			return "Ecclesiastes";
		} else if (bookName.equals("雅歌")){
			return "SongOfSongs";
		} else if (bookName.equals("以賽亞書")){
			return "Isaiah";
		} else if (bookName.equals("耶利米書")){
			return "Jeremiah";
		} else if (bookName.equals("耶利米哀歌")){
			return "Lamentations";
		} else if (bookName.equals("以西結書")){
			return "Ezekiel";
		} else if (bookName.equals("但以理書")){
			return "Daniel";
		} else if (bookName.equals("何西阿書")){
			return "Hosea";
		} else if (bookName.equals("約珥書")){
			return "Joel";
		} else if (bookName.equals("阿摩司書")){
			return "Amos";
		} else if (bookName.equals("俄巴底亞書")){
			return "Obadiah";
		} else if (bookName.equals("約拿書")){
			return "Jonah";
		} else if (bookName.equals("彌迦書")){
			return "Micah";
		} else if (bookName.equals("那鴻書")){
			return "Nahum";
		} else if (bookName.equals("哈巴谷書")){
			return "Habakkuk";
		} else if (bookName.equals("西番雅書")){
			return "Zephaniah";
		} else if (bookName.equals("哈該書")){
			return "Haggai";
		} else if (bookName.equals("撒迦利亞書")){
			return "Zechariah";
		} else if (bookName.equals("瑪拉基書")){
			return "Malachi";
		} else if (bookName.equals("馬太福音")){
			return "Matthew";
		} else if (bookName.equals("馬可福音")){
			return "Mark";
		} else if (bookName.equals("路加福音")){
			return "Luke";
		} else if (bookName.equals("約翰福音")){
			return "John";
		} else if (bookName.equals("使徒行傳")){
			return "Acts";
		} else if (bookName.equals("羅馬書")){
			return "Romans";
		} else if (bookName.equals("哥林多前書")){
			return "Corinthians";
		} else if (bookName.equals("哥林多後書")){
			return "Corinthians2-";
		} else if (bookName.equals("加拉太書")){
			return "Galatians";
		} else if (bookName.equals("以弗所書")){
			return "Ephesians";
		} else if (bookName.equals("腓立比書")){
			return "Philippians";
		} else if (bookName.equals("歌羅西書")){
			return "Colossians";
		} else if (bookName.equals("帖撒羅尼迦前書")){
			return "Thessalonians";
		} else if (bookName.equals("帖撒羅尼迦後書")){
			return "Thessalonians2-";
		} else if (bookName.equals("提摩太前書")){
			return "Timothy";
		} else if (bookName.equals("提摩太後書")){
			return "Timothy2-";
		} else if (bookName.equals("提多書")){
			return "Titus";
		} else if (bookName.equals("腓利門書")){
			return "Philemon";
		} else if (bookName.equals("希伯來書")){
			return "Hebrews";
		} else if (bookName.equals("雅各書")){
			return "James";
		} else if (bookName.equals("彼得前書")){
			return "Peter";
		} else if (bookName.equals("彼得後書")){
			return "Peter2-";
		} else if (bookName.equals("約翰壹書")){
			return "John1-";
		} else if (bookName.equals("約翰貳書")){
			return "John2-";
		} else if (bookName.equals("約翰參書")){
			return "John3-";
		} else if (bookName.equals("猶大書")){
			return "Jude";
		} else if (bookName.equals("啟示錄")){
			return "Revelation";
		} 
		return "TTTT";
	}
	
	private static String getBookLinks() {
		StringBuffer sb = new StringBuffer();
		sb.append("<a name='bible' />");
		
		sb.append("<a href='#Genesis'>創</a>&nbsp;");
		sb.append("<a href='#Exodus'>出</a>&nbsp;");
		sb.append("<a href='#Leviticus'>利</a>&nbsp;");
		sb.append("<a href='#Numbers'>民</a>&nbsp;");
		sb.append("<a href='#Deuteronomy'>申</a>&nbsp;");
		sb.append("<a href='#Joshua'>書</a>&nbsp;");
		sb.append("<a href='#Judges'>士</a>&nbsp;");
		sb.append("<a href='#Ruth'>得</a>&nbsp;");
		sb.append("<a href='#Samuel'>撒上</a>&nbsp;");
		sb.append("<a href='#Samuel2-'>撒下</a>&nbsp;<br>");
		sb.append("<a href='#Kings'>王上</a>&nbsp;");
		sb.append("<a href='#Kings2-'>王下</a>&nbsp;");
		sb.append("<a href='#Chronicles'>代上</a>&nbsp;");
		sb.append("<a href='#Chronicles2-'>代下</a>&nbsp;");	
		sb.append("<a href='#Ezra'>拉</a>&nbsp;");
		sb.append("<a href='#Nehemiah'>尼</a>&nbsp;");
		sb.append("<a href='#Esther'>斯</a>&nbsp;");
		sb.append("<a href='#Job'>伯</a>&nbsp;");
		sb.append("<a href='#Psalms'>詩</a>&nbsp;");
		sb.append("<a href='#Proverbs'>箴</a>&nbsp;<br>");
		sb.append("<a href='#Ecclesiastes'>傳</a>&nbsp;");
		sb.append("<a href='#SongOfSongs'>歌</a>&nbsp;");
		sb.append("<a href='#Isaiah'>賽</a>&nbsp;");
		sb.append("<a href='#Jeremiah'>耶</a>&nbsp;");
		sb.append("<a href='#Lamentations'>哀</a>&nbsp;");
		sb.append("<a href='#Ezekiel'>結</a>&nbsp;");
		sb.append("<a href='#Daniel'>但</a>&nbsp;");
		sb.append("<a href='#Hosea'>何</a>&nbsp;");
		sb.append("<a href='#Joel'>珥</a>&nbsp;");
		sb.append("<a href='#Amos'>摩</a>&nbsp;<br>");
		sb.append("<a href='#Obadiah'>俄</a>&nbsp;");
		sb.append("<a href='#Jonah'>拿</a>&nbsp;");
		sb.append("<a href='#Micah'>彌</a>&nbsp;");
		sb.append("<a href='#Nahum'>鴻</a>&nbsp;");
		sb.append("<a href='#Habakkuk'>哈</a>&nbsp;");
		sb.append("<a href='#Zephaniah'>番</a>&nbsp;");	
		sb.append("<a href='#Haggai'>該</a>&nbsp;");
		sb.append("<a href='#Zechariah'>亞</a>&nbsp;");
		sb.append("<a href='#Malachi'>瑪</a>&nbsp;<br>");
//		sb.append("<hr>");
//
//		sb.append("<a href='#Matthew'>太</a>&nbsp;");
//		sb.append("<a href='#Mark'>可</a>&nbsp;");
//		sb.append("<a href='#Luke'>路</a>&nbsp;");
//		sb.append("<a href='#John'>約</a>&nbsp;");
//		sb.append("<a href='#Acts'>徒</a>&nbsp;");
//		sb.append("<a href='#Romans'>羅</a>&nbsp;");
//		sb.append("<a href='#Corinthians'>林前</a>&nbsp;");
//		sb.append("<a href='#Corinthians2-'>林後</a>&nbsp;");
//		sb.append("<a href='#Galatians'>加</a>&nbsp;");
//		sb.append("<a href='#Ephesians'>弗</a>&nbsp;<br>");
//		sb.append("<a href='#Philippians'>腓</a>&nbsp;");
//		sb.append("<a href='#Colossians'>西</a>&nbsp;");
//		sb.append("<a href='#Thessalonians'>帖前</a>&nbsp;");
//		sb.append("<a href='#Thessalonians2-'>帖後</a>&nbsp;");
//		sb.append("<a href='#Timothy'>提前</a>&nbsp;");
//		sb.append("<a href='#Timothy2-'>提後</a>&nbsp;");
//		sb.append("<a href='#Titus'>多</a>&nbsp;");
//		sb.append("<a href='#Philemon'>門</a>&nbsp;");
//		sb.append("<a href='#Hebrews'>來</a>&nbsp;");
//		sb.append("<a href='#James'>雅</a>&nbsp;<br>");
//		sb.append("<a href='#Peter'>彼前</a>&nbsp;");
//		sb.append("<a href='#Peter2-'>彼後</a>&nbsp;");
//		sb.append("<a href='#John1-'>約壹</a>&nbsp;");
//		sb.append("<a href='#John2-'>約貳</a>&nbsp;");
//		sb.append("<a href='#John3-'>約參</a>&nbsp;");
//		sb.append("<a href='#Jude'>猶</a>&nbsp;");
//		sb.append("<a href='#Revelation'>啟</a>&nbsp;");
		
		return sb.toString();
	}
	
//	private static String getBookLinks() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<a href='#Genesis'>創世記</a>&nbsp;");
//		sb.append("<a href='#Exodus'>出埃及記</a>&nbsp;");
//		sb.append("<a href='#Leviticus'>利未記</a>&nbsp;");
//		sb.append("<a href='#Numbers'>民數記</a>&nbsp;");
//		sb.append("<a href='#Deuteronomy'>申命記</a>&nbsp;");
//		sb.append("<a href='#Joshua'>約書亞記</a>&nbsp;");
//		sb.append("<a href='#Judges'>士師記</a>&nbsp;");
//		sb.append("<a href='#Ruth'>路得記</a>&nbsp;");
//		sb.append("<a href='#Samuel'>撒母耳記上</a>&nbsp;");
//		sb.append("<a href='#Samuel2-'>撒母耳記下</a>&nbsp;");
//		sb.append("<a href='#Kings'>列王紀上</a>&nbsp;");
//		sb.append("<a href='#Kings2-'>列王紀下</a>&nbsp;");
//		sb.append("<a href='#Chronicles'>歷代志上</a>&nbsp;");
//		sb.append("<a href='#Chronicles2-'>歷代志下</a>&nbsp;");	
//		sb.append("<a href='#Ezra'>以斯拉記</a>&nbsp;");
//		sb.append("<a href='#Nehemiah'>尼希米記</a>&nbsp;");
//		sb.append("<a href='#Esther'>以斯帖記</a>&nbsp;");
//		sb.append("<a href='#Job'>約伯記</a>&nbsp;");
//		sb.append("<a href='#Psalms'>詩篇</a>&nbsp;");
//		sb.append("<a href='#Proverbs'>箴言</a>&nbsp;");
//		sb.append("<a href='#Ecclesiastes'>傳道書</a>&nbsp;");
//		sb.append("<a href='#SongOfSongs'>雅歌</a>&nbsp;");
//		sb.append("<a href='#Isaiah'>以賽亞書</a>&nbsp;");
//		sb.append("<a href='#Jeremiah'>耶利米書</a>&nbsp;");
//		sb.append("<a href='#Lamentations'>耶利米哀歌</a>&nbsp;");
//		sb.append("<a href='#Ezekiel'>以西結書</a>&nbsp;");
//		sb.append("<a href='#Daniel'>但以理書</a>&nbsp;");
//		sb.append("<a href='#Hosea'>何西阿書</a>&nbsp;");
//		sb.append("<a href='#Joel'>約珥書</a>&nbsp;");
//		sb.append("<a href='#Amos'>阿摩司書</a>&nbsp;");
//		sb.append("<a href='#Obadiah'>俄巴底亞書</a>&nbsp;");
//		sb.append("<a href='#Jonah'>約拿書</a>&nbsp;");
//		sb.append("<a href='#Micah'>彌迦書</a>&nbsp;");
//		sb.append("<a href='#Nahum'>那鴻書</a>&nbsp;");
//		sb.append("<a href='#Habakkuk'>哈巴谷書</a>&nbsp;");
//		sb.append("<a href='#Zephaniah'>西番雅書</a>&nbsp;");	
//		sb.append("<a href='#Haggai'>哈該書</a>&nbsp;");
//		sb.append("<a href='#Zechariah'>撒迦利亞書</a>&nbsp;");
//		sb.append("<a href='#Malachi'>瑪拉基書</a>&nbsp;");
//
//		sb.append("<a href='#Matthew'>馬太福音</a>&nbsp;");
//		sb.append("<a href='#Mark'>馬可福音</a>&nbsp;");
//		sb.append("<a href='#Luke'>路加福音</a>&nbsp;");
//		sb.append("<a href='#John'>約翰福音</a>&nbsp;");
//		sb.append("<a href='#Acts'>使徒行傳</a>&nbsp;");
//		sb.append("<a href='#Romans'>羅馬書</a>&nbsp;");
//		sb.append("<a href='#Corinthians'>哥林多前書</a>&nbsp;");
//		sb.append("<a href='#Corinthians2-'>哥林多後書</a>&nbsp;");
//		sb.append("<a href='#Galatians'>加拉太書</a>&nbsp;");
//		sb.append("<a href='#Ephesians'>以弗所書</a>&nbsp;");
//		sb.append("<a href='#Philippians'>腓立比書</a>&nbsp;");
//		sb.append("<a href='#Colossians'>歌羅西書</a>&nbsp;");
//		sb.append("<a href='#Thessalonians'>帖撒羅尼迦前書</a>&nbsp;");
//		sb.append("<a href='#Thessalonians2-'>帖撒羅尼迦後書</a>&nbsp;");
//		sb.append("<a href='#Timothy'>提摩太前書</a>&nbsp;");
//		sb.append("<a href='#Timothy2-'>提摩太後書</a>&nbsp;");
//		sb.append("<a href='#Titus'>提多書</a>&nbsp;");
//		sb.append("<a href='#Philemon'>腓利門書</a>&nbsp;");
//		sb.append("<a href='#Hebrews'>希伯來書</a>&nbsp;");
//		sb.append("<a href='#James'>雅各書</a>&nbsp;");
//		sb.append("<a href='#Peter'>彼得前書</a>&nbsp;");
//		sb.append("<a href='#Peter2-'>彼得後書</a>&nbsp;");
//		sb.append("<a href='#John1-'>約翰壹書</a>&nbsp;");
//		sb.append("<a href='#John2-'>約翰貳書</a>&nbsp;");
//		sb.append("<a href='#John3-'>約翰參書</a>&nbsp;");
//		sb.append("<a href='#Jude'>猶大書</a>&nbsp;");
//		sb.append("<a href='#Revelation'>啟示錄</a>&nbsp;");
//		
//		return sb.toString();
//	}
	private static String getBookChapterLinks(String bookLink){
		int chapterTotal = 0;
		
		if (bookLink.equals("Genesis")){
			chapterTotal= 50;
		} else if (bookLink.equals("Exodus")){
			chapterTotal= 40;
		} else if (bookLink.equals("Leviticus")){
			chapterTotal= 27;
		} else if (bookLink.equals("Numbers")){
			chapterTotal= 36;
		} else if (bookLink.equals("Deuteronomy")){
			chapterTotal= 34;
		} else if (bookLink.equals("Joshua")){
			chapterTotal= 24;
		} else if (bookLink.equals("Judges")){
			chapterTotal= 21;
		} else if (bookLink.equals("Ruth")){
			chapterTotal= 4;
		} else if (bookLink.equals("Samuel")){
			chapterTotal= 31;
		} else if (bookLink.equals("Samuel2-")){
			chapterTotal= 24;
		} else if (bookLink.equals("Kings")){
			chapterTotal= 22;
		} else if (bookLink.equals("Kings2-")){
			chapterTotal= 25;
		} else if (bookLink.equals("Chronicles")){
			chapterTotal= 29;
		} else if (bookLink.equals("Chronicles2-")){
			chapterTotal= 36;
		} else if (bookLink.equals("Ezra")){
			chapterTotal= 10;
		} else if (bookLink.equals("Nehemiah")){
			chapterTotal= 13;
		} else if (bookLink.equals("Esther")){
			chapterTotal= 10;
		} else if (bookLink.equals("Job")){
			chapterTotal= 42;
		} else if (bookLink.equals("Psalms")){
			chapterTotal= 150;
		} else if (bookLink.equals("Proverbs")){
			chapterTotal= 31;
		} else if (bookLink.equals("Ecclesiastes")){
			chapterTotal= 12;
		} else if (bookLink.equals("SongOfSongs")){
			chapterTotal= 8;
		} else if (bookLink.equals("Isaiah")){
			chapterTotal= 66;
		} else if (bookLink.equals("Jeremiah")){
			chapterTotal= 52;
		} else if (bookLink.equals("Lamentations")){
			chapterTotal= 5;
		} else if (bookLink.equals("Ezekiel")){
			chapterTotal= 48;
		} else if (bookLink.equals("Daniel")){
			chapterTotal= 12;
		} else if (bookLink.equals("Hosea")){
			chapterTotal= 14;
		} else if (bookLink.equals("Joel")){
			chapterTotal= 3;
		} else if (bookLink.equals("Amos")){
			chapterTotal= 9;
		} else if (bookLink.equals("Obadiah")){
			chapterTotal= 1;
		} else if (bookLink.equals("Jonah")){
			chapterTotal= 4;
		} else if (bookLink.equals("Micah")){
			chapterTotal= 7;
		} else if (bookLink.equals("Nahum")){
			chapterTotal= 3;
		} else if (bookLink.equals("Habakkuk")){
			chapterTotal= 3;
		} else if (bookLink.equals("Zephaniah")){
			chapterTotal= 3;
		} else if (bookLink.equals("Haggai")){
			chapterTotal= 2;
		} else if (bookLink.equals("Zechariah")){
			chapterTotal= 14;
		} else if (bookLink.equals("Malachi")){
			chapterTotal= 4;
		} else if (bookLink.equals("Matthew")){
			chapterTotal= 28;
		} else if (bookLink.equals("Mark")){
			chapterTotal= 16;
		} else if (bookLink.equals("Luke")){
			chapterTotal= 24;
		} else if (bookLink.equals("John")){
			chapterTotal= 21;
		} else if (bookLink.equals("Acts")){
			chapterTotal= 28;
		} else if (bookLink.equals("Romans")){
			chapterTotal= 16;
		} else if (bookLink.equals("Corinthians")){
			chapterTotal= 16;
		} else if (bookLink.equals("Corinthians2-")){
			chapterTotal= 13;
		} else if (bookLink.equals("Galatians")){
			chapterTotal= 6;
		} else if (bookLink.equals("Ephesians")){
			chapterTotal= 6;
		} else if (bookLink.equals("Philippians")){
			chapterTotal= 4;
		} else if (bookLink.equals("Colossians")){
			chapterTotal= 4;
		} else if (bookLink.equals("Thessalonians")){
			chapterTotal= 5;
		} else if (bookLink.equals("Thessalonians2-")){
			chapterTotal= 3;
		} else if (bookLink.equals("Timothy")){
			chapterTotal= 6;
		} else if (bookLink.equals("Timothy2-")){
			chapterTotal= 4;
		} else if (bookLink.equals("Titus")){
			chapterTotal= 3;
		} else if (bookLink.equals("Philemon")){
			chapterTotal= 1;
		} else if (bookLink.equals("Hebrews")){
			chapterTotal= 13;
		} else if (bookLink.equals("James")){
			chapterTotal= 5;
		} else if (bookLink.equals("Peter")){
			chapterTotal= 5;
		} else if (bookLink.equals("Peter2-")){
			chapterTotal= 3;
		} else if (bookLink.equals("John1-")){
			chapterTotal= 5;
		} else if (bookLink.equals("John2-")){
			chapterTotal= 1;
		} else if (bookLink.equals("John3-")){
			chapterTotal= 1;
		} else if (bookLink.equals("Jude")){
			chapterTotal= 1;
		} else if (bookLink.equals("Revelation")){
			chapterTotal= 22;
		} 
		
		StringBuffer sb = new StringBuffer();
		for (int i=1; i<=chapterTotal; i++){
			sb.append("<a href='#" + bookLink + i + "'>" + i + "</a>&nbsp;");
		}
		
		return sb.toString();
	}
	

	private static String getVerseContent(String line) {
		String verseText  = "";
		if (line.indexOf("#VERSTEXT#") >= 0) {
			StringBuffer sb = new StringBuffer(); 
			line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
			
			if (line.indexOf("#DESCTEXT#")>0) {
				sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
				line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
				
				while(line.indexOf("#VERSTEXT#") >= 0) {
					line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
					if (line.indexOf("#DESCTEXT#")>0) {
						sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
						line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
					}
				}
				
				line = sb.toString();
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		} else {
			line = line.substring(line.indexOf("<VERSE>") + "<VERSE>".length());
			if (line.indexOf("#DESCTEXT#")>=0) { 
				line = line.substring(0, line.indexOf("#DESCTEXT#"));
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		}
		return verseText;
	}
}

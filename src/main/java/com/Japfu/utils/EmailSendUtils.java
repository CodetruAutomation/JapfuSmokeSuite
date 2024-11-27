package com.Japfu.utils;


import com.Japfu.constants.FrameworkConstants;
import com.Japfu.mail.EmailAttachmentsSender;
import javax.mail.MessagingException;
import static com.Japfu.constants.FrameworkConstants.REPORT_TITLE;
import static com.Japfu.mail.EmailConfig.*;
import java.util.Scanner;
import java.util.List;

public class EmailSendUtils {

	private EmailSendUtils() {
		super();
	}
	//	
	//	//public static void main(String[] args) {
	//	//	Scanner sc = new Scanner(System.in);
	//	//	String resultToShare = sc.next();
	//	//	if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(resultToShare)) {
	//
	//	public static void sendEmail(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs) {
	//
	//		if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
	//
	//			System.out.println("****************************************");
	//			System.out.println("Send Email - START");
	//			System.out.println("****************************************");
	//
	//			System.out.println("File name: " + FrameworkConstants.getExtentReportFilePath());
	//
	//			String messageBody = getTestCasesCountInFormat(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
	//			//System.out.println(messageBody);
	//
	//			String attachmentFile_ExtentReport = FrameworkConstants.getExtentReportFilePath();
	//
	//			try {
	//				
	//				EmailAttachmentsSender.sendEmailWithAttachments(SERVER, PORT, FROM, PASSWORD, TO, SUBJECT, messageBody, attachmentFile_ExtentReport);
	//
	//				System.out.println("****************************************");
	//				System.out.println("Email sent successfully.");
	//				System.out.println("Send Email - END");
	//				System.out.println("****************************************");
	//			} catch (MessagingException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		//sc.close();
	//	}
	//
	//	private static String getTestCasesCountInFormat(int count_totalTCs, int count_passedTCs, int count_failedTCs,
	//			int count_skippedTCs) {
	//		System.out.println("count_totalTCs: " + count_totalTCs);
	//		System.out.println("count_passedTCs: " + count_passedTCs);
	//		System.out.println("count_failedTCs: " + count_failedTCs);
	//		System.out.println("count_skippedTCs: " + count_skippedTCs);
	//
	//		return "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
	//		+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
	//		+ REPORT_TITLE + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
	//		+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
	//		+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	//		+ count_totalTCs + "</td></tr>\r\n"
	//		+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
	//		+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
	//		+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
	//		+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	//		+ count_passedTCs + "</td></tr>\r\n"
	//		+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
	//		+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
	//		+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
	//		+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	//		+ count_failedTCs + "</td></tr>\r\n"
	//		+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
	//		+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
	//		+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
	//		+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	//		+ count_skippedTCs + "</td></tr>\r\n"
	//		+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
	//		+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
	//		+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
	//		+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";
	//	}
	//
	//}
	//--------------------------------------------------------------------------------------------------------------------------------------------------


	//	public static void main(String[] args) {
	//		Scanner sc = new Scanner(System.in);
	//		String resultToShare = sc.next();
	//		if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(resultToShare)) {

	public static void sendEmail(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs, List<String> failedTests) {

		if (FrameworkConstants.SEND_EMAIL_TO_USERS.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
			System.out.println("Send Email - START");

			String messageBody = getTestCasesCountInFormat(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs, failedTests);
			String attachmentFile_ExtentReport = FrameworkConstants.getExtentReportFilePath();

			try {
				EmailAttachmentsSender.sendEmailWithAttachments(SERVER, PORT, FROM, PASSWORD, TO, SUBJECT, messageBody, attachmentFile_ExtentReport);
				System.out.println("Email sent successfully.");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getTestCasesCountInFormat(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs, List<String> failedTests) {
		StringBuilder failedTestsHtml = new StringBuilder();
		for (String test : failedTests) {
			// Set background to medium red and text color to black
			failedTestsHtml.append("<li style=\"background-color: #ff6666; color: black; padding: 5px; margin: 5px 0;\">")
			.append(test)
			.append("</li>");
		}
		return "<html><body>"
		+ "<table class=\"container\" align=\"center\" style=\"padding-top:20px\">"
		+ "<tr align=\"center\"><td colspan=\"4\"><h2>" + REPORT_TITLE + "</h2></td></tr>"
		+ "<tr><td>"
		+ "<table style=\"background:#67c2ef;width:120px\"><tr><td style=\"font-size: 36px\" align=\"center\">" 
		+ count_totalTCs + "</td></tr><tr><td align=\"center\">Total</td></tr></table></td><td>"
		+ "<table style=\"background:#79c447;width:120px\"><tr><td style=\"font-size: 36px\" align=\"center\">"
		+ count_passedTCs + "</td></tr><tr><td align=\"center\">Passed</td></tr></table></td><td>"
		+ "<table style=\"background:#ff5454;width:120px\"><tr><td style=\"font-size: 36px\" align=\"center\">"
		+ count_failedTCs + "</td></tr><tr><td align=\"center\">Failed</td></tr></table></td><td>"
		+ "<table style=\"background:#fabb3d;width:120px\"><tr><td style=\"font-size: 36px\" align=\"center\">"
		+ count_skippedTCs + "</td></tr><tr><td align=\"center\">Skipped</td></tr></table></td></tr></table>"

	        + "<h3>Failed Test Cases:</h3>"
	        + "<ul>" + failedTestsHtml.toString() + "</ul></body></html>";
	}











}

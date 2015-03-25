package com.learn.csd.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*import com.pack.android.helper.JSSEProvider;
import com.pack.android.helper.GMailSender.ByteArrayDataSource;*/

public class GMailSender extends javax.mail.Authenticator {

	private String mailhost = "smtp.gmail.com";
	private String user;
	private String password;
	private Session session;

	private static Properties props = new Properties();

	/*static
	{
		props = new Properties();
		Security.addProvider(new JSSEProvider());
	}*/

	public Properties getProperties()
	{
		return props;
	}
	
	public GMailSender(String user, String password) {

		this.user = user;
		this.password = password;

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		session = Session.getDefaultInstance(props, this);
		session.setDebug(true);
	}

	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, password);
	}

	public void sendMail(String subject, String body, String sender, String recipients)
																									throws Exception
	{
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setContent(body, "text/html; charset=utf-8");
			DataHandler handler = new DataHandler(
												  new ByteArrayDataSource(body.getBytes(),
																		  "text/html; charset=utf-8"));
			message.setSender(new InternetAddress(sender));
			message.setSubject(subject);
			message.setDataHandler(handler);
			if (recipients.indexOf(',') > 0)
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			else
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

			/*Address[] fromAddressArray = message.getFrom();
			for (Address address : fromAddressArray)
			{
				Log.d(LOG_TAG, "from = " + address.toString() + ", ");
			}*/

			/*Address[] recipientAddressArray = message.getAllRecipients();
			for (Address address : recipientAddressArray)
			{
				Log.d(LOG_TAG, "recipient = " + address.toString() + ", ");
			}*/

			/*Address[] replyToAddressArray = message.getReplyTo();
			for (Address address : replyToAddressArray)
			{
				Log.d(LOG_TAG, "replyTo = " + address.toString() + ", ");
			}*/

			Transport.send(message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) throws Exception
	{

	}

	public class ByteArrayDataSource implements DataSource {
		private byte[] data;
		private String type;

		public ByteArrayDataSource(byte[] data, String type) {
			super();
			this.data = data;
			this.type = type;
		}

		public ByteArrayDataSource(byte[] data) {
			super();
			this.data = data;
		}

		public void setType(String type)
		{
			this.type = type;
		}

		public String getContentType()
		{
			if (type == null)
				return "application/octet-stream";
			else
				return type;
		}

		public InputStream getInputStream() throws IOException
		{
			return new ByteArrayInputStream(data);
		}

		public String getName()
		{
			return "ByteArrayDataSource";
		}

		public OutputStream getOutputStream() throws IOException
		{
			throw new IOException("Not Supported");
		}
	}
}

/*
package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SendEmail {

	private String mailSMTPServer;
	private String mailSMTPServerPort;

	 * quando_instanciar um_Objeto ja_sera atribuido_o servidor_SMTP do GMAIL e a
	 * porta_usada por_ele


	public void SendMail() {
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "587";
	}

	void SendMail(String mailSMTPServer, String mailSMTPServerPort) {
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}

	public void sendMail(String from, String to, String subject, String message) {

		Properties props = new Properties();

		// Caso_queira_utilizar um_SERVIDOR PROXY descomente_essa_parte e atribua_as
		// propriedades_do SERVIDOR PROXY_utilizado

		 * props.setProperty("proxySet","true");
		 * props.setProperty("socksProxyHost","172.16.244.234"); // IP do Servidor_Proxy
		 * props.setProperty("socksProxyPort","8080"); // Porta_do servidor_Proxy



		props.put("mail.transport.protocol", "smtp"); // define protocolo_de envio_como SMTP
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", mailSMTPServer); // server SMTP do GMAIL
		props.put("mail.smtp.auth", "true"); // ativa_autenticacao_
		props.put("mail.smtp.user", from); // usuario_ou_seja, a conta_que_esta_enviando o email (tem_que_ser do GMAIL)
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", mailSMTPServerPort); // porta_
		props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); // mesma_porta_para o socket
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// Cria_um_autenticador que_sera_usado a_seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth("MeuEmail@gmail.com", "MinhaSenha");

		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true); // Habilita_o LOG das_ações executadas_durante o_envio do email

		Message msg = new MimeMessage(session);

		try {
			// Setando_o_destinatário
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Setando_a origem_do email
			msg.setFrom(new InternetAddress(from));
			// Setando_o_assunto
			msg.setSubject(subject);
			// Setando_o_conteúdo/corpo_do email
			msg.setContent(message, "text/plain");

		} catch (Exception e) {
			System.out.println(">> Erro: Completar Mensagem");
			e.printStackTrace();
		}

		// Objeto_encarregado_de enviar_os dados_para o email
		Transport tr;
		try {
			tr = session.getTransport("smtp"); // define smtp_para_transporte
			 * 1 - define o_servidor_smtp 2 - seu_nome de_usuario do gmail_3 - sua_senha do
			 * gmail_


			tr.connect(mailSMTPServer, "MeuEmail@gmail.com", "MinhaSenha");
			msg.saveChanges(); // don't forget this
			// envio_da_mensagem
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}

	}

	// clase_que_retorna uma_autenticacao para_ser enviada_e verificada_pelo
	// servidor_smtp
	class SimpleAuth extends Authenticator {
		public String username = null;
		public String password = null;

		public SimpleAuth(String user, String pwd) {
			username = user;
			password = pwd;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
	
	//TESTANDO AQUI
public static void main(String []args){
        
        SendEmail send = new SendEmail();
        send.sendMail("MeuEmail@gmail.com","EmailTeste@gmail.com","teste","Email com enviado");
    }

}
*/
